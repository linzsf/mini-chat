package org.lzsf.mc.server.service;

import lombok.extern.slf4j.Slf4j;
import org.lzsf.protocol.AbstractMessage;
import org.lzsf.protocol.UserMessage;

@Slf4j
public class UserMessageService extends AbstractMessageService{
    @Override
    public void execute(AbstractMessage message) {
        log.info("start handle user message :\"" + ((UserMessage) message).toString() + "\"");
    }
}
