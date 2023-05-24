package org.lzsf.mc.server.service;

import org.lzsf.protocol.AbstractMessage;

public abstract class AbstractMessageService {
    public abstract void execute(AbstractMessage message);
}
