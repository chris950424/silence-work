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
package com.silence.product.infrastructure.util.uid.impl;


import com.silence.product.infrastructure.util.uid.DockerUtils;
import com.silence.product.infrastructure.util.uid.NetUtils;
import com.silence.product.infrastructure.util.uid.WorkerIdAssigner;
import com.silence.product.infrastructure.util.uid.WorkerNodeType;
import com.silence.product.infrastructure.util.uid.dao.UidMapper;
import com.silence.product.infrastructure.util.uid.entity.WorkerNodePo;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


@Component
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    @Resource
    private UidMapper workerNodeDAO;


    @Override
    public long assignWorkerId() {
        // build worker node entity
        WorkerNodePo workerNodeEntity = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeDAO.addWorkerNode(workerNodeEntity);
        LOGGER.info("Add worker node:" + workerNodeEntity);
        return workerNodeEntity.getId();
    }


    private WorkerNodePo buildWorkerNode() {
        WorkerNodePo workerNodeEntity = new WorkerNodePo();
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(DockerUtils.getDockerPort());

        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(NetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(100000));
        }
        workerNodeEntity.setCreated(new Date());
        workerNodeEntity.setModified(new Date());
        return workerNodeEntity;
    }

}
