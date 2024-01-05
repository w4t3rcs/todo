package com.w4t3rcs.newtodo.model.entity.message;

import com.w4t3rcs.newtodo.model.common.HasRecipientAddress;
import com.w4t3rcs.newtodo.model.common.HasMessage;
import com.w4t3rcs.newtodo.model.common.HasSenderAddress;

public interface TextMessage extends HasMessage<String>, HasSenderAddress<String>, HasRecipientAddress<String> {
}
