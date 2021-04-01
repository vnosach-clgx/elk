package com.root.provider;

import com.root.domain.Event;

import java.time.LocalDate;
import java.util.List;

public interface ESInterface {
    void createNewIndex(String indexName);

    void applyNewMapping(String property, String type);

    void storeEvent(Event event);

    List<Event> searchAll();

    List<Event> searchBy(Event.Type type);

    List<Event> searchBy(String title);

    List<Event> searchBy(LocalDate afterDate, String name);

}
