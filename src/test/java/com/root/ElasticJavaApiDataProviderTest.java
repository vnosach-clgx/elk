package com.root;

import com.root.domain.Event;
import com.root.provider.ElasticJavaApiDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ElasticJavaApiDataProviderTest {


    @Autowired
    private ElasticJavaApiDataProvider subject;

    @Test
    void searchAll_successful() {
        List<Event> events = subject.searchAll();

        assertThat(events.size()).isGreaterThan(1);
    }

    @Test
    void searchBy_successful() {
        List<Event> events = subject.searchBy(Event.Type.WORKSHOP);

        assertThat(events.size()).isGreaterThan(1);
    }

    @Test
    void searchByDateAndName_successful() {
        List<Event> events = subject.searchBy(LocalDate.of(2021, 4, 1), "Haskell");

        assertThat(events.size()).isEqualTo(1);
    }
}
