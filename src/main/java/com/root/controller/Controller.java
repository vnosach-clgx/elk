package com.root.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/event")
@Log4j2
public class Controller {

    @GetMapping
    public UUID getUuid() {
        UUID uuid = UUID.randomUUID();
        log.info("Generates some log {}", uuid);
        return uuid;
    }
}
