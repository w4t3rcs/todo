package com.w4t3rcs.newtodo.model.common.executor.sender;

import com.w4t3rcs.newtodo.model.entity.message.TextMessage;

public interface ServiceSender extends Sender {
    void send(TextMessage textMessage);
}
