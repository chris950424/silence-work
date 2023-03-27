package com.silence.stock.infrastructure.util.uid.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.silence.stock.infrastructure.util.uid.entity.WorkerNodePo;


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
