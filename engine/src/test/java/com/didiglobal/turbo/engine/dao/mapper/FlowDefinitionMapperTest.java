package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowDefinitionPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FlowDefinitionMapperTest extends BaseTest {

    @Autowired
    private FlowDefinitionMapper flowDefinitionMapper;

    @Test
    public void insert() {
        FlowDefinitionPO flowDefinitionPO = EntityBuilder.buildFlowDefinitionPO();
        flowDefinitionPO.setFlowModuleId("testFlowModuleId_" + System.currentTimeMillis());
        int result = flowDefinitionMapper.insert(flowDefinitionPO);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void update() {
        FlowDefinitionPO flowDefinitionPO = EntityBuilder.buildFlowDefinitionPO();
        flowDefinitionPO.setFlowModuleId("testFlowModuleId_" + System.currentTimeMillis());
        flowDefinitionMapper.insert(flowDefinitionPO);
        flowDefinitionPO = flowDefinitionMapper.selectByFlowModuleId(flowDefinitionPO.getFlowModuleId());
        flowDefinitionPO.setStatus(3);
        int result = flowDefinitionMapper.updateById(flowDefinitionPO);
        Assertions.assertTrue(result == 1);
        flowDefinitionPO = flowDefinitionMapper.selectByFlowModuleId(flowDefinitionPO.getFlowModuleId());
        Assertions.assertTrue(flowDefinitionPO.getStatus().equals(3));
    }

    @Test
    public void selectByFlowModuleId() {
        FlowDefinitionPO flowDefinitionPO = EntityBuilder.buildFlowDefinitionPO();
        flowDefinitionPO.setFlowModuleId("testFlowModuleId_" + System.currentTimeMillis());
        int result = flowDefinitionMapper.insert(flowDefinitionPO);
        Assertions.assertTrue(result == 1);
        String flowModuleId = flowDefinitionPO.getFlowModuleId();
        FlowDefinitionPO queryFlowDefinitionPO = flowDefinitionMapper.selectByFlowModuleId(flowModuleId);
        LOGGER.info("selectByModuleIdTest.||flowDefinitionPO={}", flowDefinitionPO);
        Assertions.assertTrue(flowModuleId.equals(queryFlowDefinitionPO.getFlowModuleId()));

    }
}
