/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.silence.elasticsearch.infrastructure.util.uid.impl;

import com.silence.elasticsearch.infrastructure.util.uid.BitsAllocator;
import com.silence.elasticsearch.infrastructure.util.uid.DateUtils;
import com.silence.elasticsearch.infrastructure.util.uid.UidGenerator;
import com.silence.elasticsearch.infrastructure.util.uid.WorkerIdAssigner;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class DefaultUidGenerator implements UidGenerator, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUidGenerator.class);

    protected int timeBits = 28;
    protected int workerBits = 22;
    protected int seqBits = 13;

    protected String epochStr = "2016-05-20";
    protected long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(1463673600000L);

    protected BitsAllocator bitsAllocator;
    protected long workerId;

    protected long sequence = 0L;
    protected long lastSecond = -1L;

    @Resource
    protected WorkerIdAssigner workerIdAssigner;

    @Override
    public void afterPropertiesSet() throws Exception {
        // initialize bits allocator
        bitsAllocator = new BitsAllocator(timeBits, workerBits, seqBits);

        // initialize worker id
        workerId = workerIdAssigner.assignWorkerId();
        if (workerId > bitsAllocator.getMaxWorkerId()) {
            throw new RuntimeException("Worker id " + workerId + " exceeds the max " + bitsAllocator.getMaxWorkerId());
        }

        LOGGER.info("Initialized bits(1, {}, {}, {}) for workerID:{}", timeBits, workerBits, seqBits, workerId);
    }

    @Override
    public long getUID()  {
        try {
            return nextId();
        } catch (Exception e) {
            LOGGER.error("Generate unique id exception. ", e);
        }
        return 0;
    }

    @Override
    public String parseUID(long uid) {
        long totalBits = BitsAllocator.TOTAL_BITS;
        long signBits = bitsAllocator.getSignBits();
        long timestampBits = bitsAllocator.getTimestampBits();
        long workerIdBits = bitsAllocator.getWorkerIdBits();
        long sequenceBits = bitsAllocator.getSequenceBits();

        // parse UID
        long sequence = (uid << (totalBits - sequenceBits)) >>> (totalBits - sequenceBits);
        long workerId = (uid << (timestampBits + signBits)) >>> (totalBits - workerIdBits);
        long deltaSeconds = uid >>> (workerIdBits + sequenceBits);

        Date thatTime = new Date(TimeUnit.SECONDS.toMillis(epochSeconds + deltaSeconds));
        String thatTimeStr = DateUtils.formatByDateTimePattern(thatTime);

        // format as string
        return String.format("{\"UID\":\"%d\",\"timestamp\":\"%s\",\"workerId\":\"%d\",\"sequence\":\"%d\"}",
                uid, thatTimeStr, workerId, sequence);
    }


    protected synchronized long nextId() {
        long currentSecond = getCurrentSecond();

        // Clock moved backwards, refuse to generate uid
        if (currentSecond < lastSecond) {
            long refusedSeconds = lastSecond - currentSecond;
        }

        // At the same second, increase sequence
        if (currentSecond == lastSecond) {
            sequence = (sequence + 1) & bitsAllocator.getMaxSequence();
            // Exceed the max sequence, we wait the next second to generate uid
            if (sequence == 0) {
                currentSecond = getNextSecond(lastSecond);
            }

        // At the different second, sequence restart from zero
        } else {
            sequence = 0L;
        }

        lastSecond = currentSecond;

        // Allocate bits for UID
        return bitsAllocator.allocate(currentSecond - epochSeconds, workerId, sequence);
    }


    private long getNextSecond(long lastTimestamp) {
        long timestamp = getCurrentSecond();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentSecond();
        }

        return timestamp;
    }


    private long getCurrentSecond() {
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (currentSecond - epochSeconds > bitsAllocator.getMaxDeltaSeconds()) {
        }

        return currentSecond;
    }

    public void setWorkerIdAssigner(WorkerIdAssigner workerIdAssigner) {
        this.workerIdAssigner = workerIdAssigner;
    }

    public void setTimeBits(int timeBits) {
        if (timeBits > 0) {
            this.timeBits = timeBits;
        }
    }

    public void setWorkerBits(int workerBits) {
        if (workerBits > 0) {
            this.workerBits = workerBits;
        }
    }

    public void setSeqBits(int seqBits) {
        if (seqBits > 0) {
            this.seqBits = seqBits;
        }
    }

    public void setEpochStr(String epochStr) {
        if (StringUtils.isNotBlank(epochStr)) {
            this.epochStr = epochStr;
            this.epochSeconds = TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseByDayPattern(epochStr).getTime());
        }
    }
}
