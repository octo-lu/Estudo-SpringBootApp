package com.example.nobsv2.SSE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SSE {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @GetMapping("/server-stream-sse")
    public SseEmitter streamSseEvents() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Long-lived connection
        executor.execute(() -> {
            try {
                for (int i = 0; i < 5; i++) {

                    emitter.send("SSE STREAM Managed by the Server - " + i);
                    Thread.sleep(1000); // simulate delay
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping("/client-stream-sse")
    public SseEmitter streamSseEventsClient() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Long-lived connection
        executor.execute(() -> {
            try {
                for (int i = 0; i < 100; i++) {

                    emitter.send("SSE STREAM Managed by the client - " + i);
                    Thread.sleep(1000); // simulate delay
                }
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}