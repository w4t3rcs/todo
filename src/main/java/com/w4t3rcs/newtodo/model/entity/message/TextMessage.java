package com.w4t3rcs.newtodo.model.entity.message;

import com.w4t3rcs.newtodo.model.common.Formatter;
import com.w4t3rcs.newtodo.model.common.HasMessage;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;

public interface TextMessage extends HasMessage<String>, Formatter<String, String> {
    @Override
    String getMessage(MessageProperties messageProperties);

    @Override
    String format(String text);
}
