package org.lzsf.mc.client.service;

import org.lzsf.protocol.Packet;
import org.lzsf.protocol.response.Response;

public interface ClientService {
    void excute(Packet pkt);
}
