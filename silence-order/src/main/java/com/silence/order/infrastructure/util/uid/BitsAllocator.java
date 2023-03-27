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
package com.silence.order.infrastructure.util.uid;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.Assert;


public class BitsAllocator {

    public static final int TOTAL_BITS = 1 << 6;


    private int signBits = 1;
    private final int timestampBits;
    private final int workerIdBits;
    private final int sequenceBits;


    private final long maxDeltaSeconds;
    private final long maxWorkerId;
    private final long maxSequence;


    private final int timestampShift;
    private final int workerIdShift;


    public BitsAllocator(int timestampBits, int workerIdBits, int sequenceBits) {
        // make sure allocated 64 bits
        int allocateTotalBits = signBits + timestampBits + workerIdBits + sequenceBits;
        Assert.isTrue(allocateTotalBits == TOTAL_BITS, "allocate not enough 64 bits");

        // initialize bits
        this.timestampBits = timestampBits;
        this.workerIdBits = workerIdBits;
        this.sequenceBits = sequenceBits;

        // initialize max value
        this.maxDeltaSeconds = ~(-1L << timestampBits);
        this.maxWorkerId = ~(-1L << workerIdBits);
        this.maxSequence = ~(-1L << sequenceBits);

        // initialize shift
        this.timestampShift = workerIdBits + sequenceBits;
        this.workerIdShift = sequenceBits;
    }


    public long allocate(long deltaSeconds, long workerId, long sequence) {
        return (deltaSeconds << timestampShift) | (workerId << workerIdShift) | sequence;
    }
    

    public int getSignBits() {
        return signBits;
    }

    public int getTimestampBits() {
        return timestampBits;
    }

    public int getWorkerIdBits() {
        return workerIdBits;
    }

    public int getSequenceBits() {
        return sequenceBits;
    }

    public long getMaxDeltaSeconds() {
        return maxDeltaSeconds;
    }

    public long getMaxWorkerId() {
        return maxWorkerId;
    }

    public long getMaxSequence() {
        return maxSequence;
    }

    public int getTimestampShift() {
        return timestampShift;
    }

    public int getWorkerIdShift() {
        return workerIdShift;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}