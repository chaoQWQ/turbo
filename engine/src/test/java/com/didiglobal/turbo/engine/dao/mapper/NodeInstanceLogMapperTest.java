package com.didiglobal.turbo.engine.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didiglobal.turbo.engine.entity.NodeInstanceLogPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NodeInstanceLogMapperTest extends BaseTest {

    @Autowired
    private NodeInstanceLogMapper nodeInstanceLogMapper;

    @Test
    public void batchInsert() {
        String flowInstanceId = "flowInstanceId_" + UUID.randomUUID().toString();
        NodeInstanceLogPO nodeInstanceLogPO = EntityBuilder.buildNodeInstanceLogPO(flowInstanceId);
        List<NodeInstanceLogPO> nodeInstanceLogPOList = new ArrayList<>();
        nodeInstanceLogPOList.add(nodeInstanceLogPO);
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO(flowInstanceId));
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO(flowInstanceId));
        nodeInstanceLogMapper.batchInsert(nodeInstanceLogPO.getFlowInstanceId(), nodeInstanceLogPOList);

        QueryWrapper<NodeInstanceLogPO> entityWrapper = new QueryWrapper<>();
        entityWrapper.in("flow_instance_id", nodeInstanceLogPO.getFlowInstanceId());
        nodeInstanceLogPOList = nodeInstanceLogMapper.selectList(entityWrapper);
        Assertions.assertTrue(nodeInstanceLogPOList.size() == 3);
    }
}
