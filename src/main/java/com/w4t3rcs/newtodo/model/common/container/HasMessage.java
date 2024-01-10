package com.w4t3rcs.newtodo.model.common.container;

import com.w4t3rcs.newtodo.model.properties.MessageProperties;

public interface HasMessage<T> {
    T getMessageSubject(MessageProperties messageProperties);

    T getMessageBody(MessageProperties messageProperties);
}
