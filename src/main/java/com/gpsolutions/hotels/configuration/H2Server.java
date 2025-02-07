package com.gpsolutions.hotels.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.h2.tools.Server;
import org.springframework.stereotype.Component;

@Component
public class H2Server {
    private Server server;

    @PostConstruct
    public void start() throws Exception {
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @PreDestroy
    public void stop() {
        server.stop();
    }
}
