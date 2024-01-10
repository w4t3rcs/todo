package com.w4t3rcs.newtodo.model.entity.message;

import com.w4t3rcs.newtodo.model.common.container.HasRecipientAddress;
import com.w4t3rcs.newtodo.model.common.container.HasMessage;
import com.w4t3rcs.newtodo.model.common.container.HasSenderAddress;

public interface TextMessage extends HasMessage<String>, HasSenderAddress<String>, HasRecipientAddress<String> {
}
