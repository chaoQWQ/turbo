package com.didiglobal.turbo.engine.validator;

import com.didiglobal.turbo.engine.common.Constants;
import com.didiglobal.turbo.engine.common.ErrorEnum;
import com.didiglobal.turbo.engine.common.FlowElementType;
import com.didiglobal.turbo.engine.exception.ProcessException;
import com.didiglobal.turbo.engine.model.FlowElement;
import com.didiglobal.turbo.engine.util.FlowModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import java.text.MessageFormat;

@Component
public class ElementValidatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementValidatorFactory.class);

    @Autowired
    private StartEventValidator startEventValidator;

    @Autowired
    private EndEventValidator endEventValidator;

    @Autowired
    private SequenceFlowValidator sequenceFlowValidator;

    @Autowired
    private UserTaskValidator userTaskValidator;

    @Autowired
    private ExclusiveGatewayValidator exclusiveGatewayValidator;

    @Autowired
    private CallActivityValidator callActivityValidator;

    public ElementValidator getElementValidator(FlowElement flowElement) throws ProcessException {
        int elementType = flowElement.getType();
        ElementValidator elementValidator = getElementValidator(elementType);

        if (elementValidator == null) {
            LOGGER.warn("getElementValidator failed: unsupported elementType.||elementType={}", elementType);
            throw new ProcessException(ErrorEnum.UNSUPPORTED_ELEMENT_TYPE,
                MessageFormat.format(Constants.NODE_INFO_FORMAT, flowElement.getKey(),
                    FlowModelUtil.getElementName(flowElement), elementType));
        }
        return elementValidator;
    }

    private ElementValidator getElementValidator(int elementType) {
        switch (elementType) {
            case FlowElementType.START_EVENT:
                return startEventValidator;
            case FlowElementType.END_EVENT:
                return endEventValidator;
            case FlowElementType.SEQUENCE_FLOW:
                return sequenceFlowValidator;
            case FlowElementType.USER_TASK:
                return userTaskValidator;
            case FlowElementType.EXCLUSIVE_GATEWAY:
                return exclusiveGatewayValidator;
            case FlowElementType.CALL_ACTIVITY:
                return callActivityValidator;
            default:
                return null;
        }
    }
}
