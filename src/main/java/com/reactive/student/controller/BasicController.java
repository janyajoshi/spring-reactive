package com.reactive.student.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("base")
public class BasicController {
    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("Hello world");
    }

    @GetMapping("flux")
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4);
    }

    @GetMapping(value = "flux-stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Integer> fluxStream() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9).delayElements(Duration.ofSeconds(1)).log();
    }
}
