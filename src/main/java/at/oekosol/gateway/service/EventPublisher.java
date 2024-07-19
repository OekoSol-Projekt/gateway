package at.oekosol.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    @Autowired
    private StreamBridge streamBridge;

    public void sendEvent(String event) {
        streamBridge.send("processEvent-out-0", MessageBuilder.withPayload(event).build());
    }
}
