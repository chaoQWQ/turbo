package com.didiglobal.turbo.engine.validator;

import com.didiglobal.turbo.engine.exception.DefinitionException;
import com.didiglobal.turbo.engine.model.FlowElement;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;


public class UserTaskValidatorTest extends BaseTest {
    @Autowired
    UserTaskValidator userTaskValidator;

    /**
     * Check userTask's incoming, whlile normal.
     *
     */
    @Test
    public void checkIncomingAccess() {
        FlowElement userTask = EntityBuilder.buildUserTask();
        Map<String, FlowElement> map = new HashMap<>();
        map.put(userTask.getKey(), userTask);
        boolean access = false;
        try {
            userTaskValidator.checkIncoming(map, userTask);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }
    }
    /**
     * Check userTask's incoming, while incoming is lack.
     *
     */
    @Test
    public void checkWithoutIncoming() {
        FlowElement userTask = EntityBuilder.buildUserTask();
        userTask.setIncoming(null);
        Map<String, FlowElement> map = new HashMap<>();
        map.put(userTask.getKey(), userTask);
        boolean access = false;
        try {
            userTaskValidator.checkIncoming(map, userTask);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }
    }


    /**
     * Check userTask's outgoing, whlile normal.
     *
     */
    @Test
    public void checkOutgoingAccess() {
        FlowElement userTask = EntityBuilder.buildUserTask();
        Map<String, FlowElement> map = new HashMap<>();
        map.put(userTask.getKey(), userTask);
        boolean access = false;
        try {
            userTaskValidator.checkOutgoing(map, userTask);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }
    }

    /**
     * Check userTask's outgoing, while outgoing is lack.
     *
     */
    @Test
    public void checkWithoutOutgoing() {
        FlowElement userTask = EntityBuilder.buildUserTask();
        userTask.setOutgoing(null);
        Map<String, FlowElement> map = new HashMap<>();
        map.put(userTask.getKey(), userTask);
        boolean access = false;
        try {
            userTaskValidator.checkOutgoing(map, userTask);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }
    }
}