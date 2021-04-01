package com.root.provider;

import com.root.domain.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ElasticJavaRestApiDataProvider implements ESInterface {

    @Override
    public void createNewIndex(String indexName) {

    }

    @Override
    public void applyNewMapping(String property, String type) {

    }

    @Override
    public void storeEvent(Event event) {

    }

    @Override
    public List<Event> searchAll() {
        return null;
    }

    @Override
    public List<Event> searchBy(Event.Type type) {
        return null;
    }

    @Override
    public List<Event> searchBy(String title) {
        return null;
    }

    @Override
    public List<Event> searchBy(LocalDate afterDate, String name) {
        return null;
    }
}
