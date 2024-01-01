package com.w4t3rcs.newtodo.model.common;

import com.w4t3rcs.newtodo.model.properties.MessageProperties;

public interface HasMessage<T> {
    T getMessage(MessageProperties messageProperties);
}
