package com.root.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Event {
    private String title;
    private Type type;
    private LocalDateTime datetime;
    private String place;
    private String description;
    private List<String> subTopics;

    public enum Type {
        WORKSHOP,
        TECH_TALK
    }
}
