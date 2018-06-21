package com.sample.cd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;

import java.io.IOException;

@SpringBootApplication
public class PubSubApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(PubSubApplication.class);
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter( @Qualifier("pubsubInputChannel") MessageChannel inputChannel, PubSubOperations pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "testSubscription");

        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }
}
