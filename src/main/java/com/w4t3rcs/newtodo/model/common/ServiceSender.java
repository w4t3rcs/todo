package com.w4t3rcs.newtodo.model.common;

public interface ServiceSender extends Sender {
    void send(Object from, Object to, Object subject, Object body);
}
