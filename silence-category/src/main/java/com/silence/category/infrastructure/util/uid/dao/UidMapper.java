package com.silence.category.infrastructure.util.uid.dao;


import com.silence.category.infrastructure.util.uid.entity.WorkerNodePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author Administrator
 */
@Repository
public interface UidMapper {

    /**
     * Get {@link WorkerNodePo} by node host
     *
     * @param host
     * @param port
     * @return
     */
    WorkerNodePo getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * Add {@link WorkerNodePo}
     *
     * @param workerNodeEntity
     */
    void addWorkerNode(WorkerNodePo workerNodeEntity);
}
