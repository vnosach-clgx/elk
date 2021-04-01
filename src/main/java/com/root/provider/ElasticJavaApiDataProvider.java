package com.root.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.root.domain.Event;
import lombok.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ElasticJavaApiDataProvider implements ESInterface {

    private final ObjectMapper mapper;
    private final RestHighLevelClient client;

    private static final String EVENT_INDEX = "event_index";

    @SneakyThrows
    @Override
    public void createNewIndex(String indexName) {
        IndexRequest indexRequest = new IndexRequest(indexName);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public void applyNewMapping(String property, String type) {
        PutMappingRequest request = new PutMappingRequest(EVENT_INDEX);
        IndexMappingRequest.TypeClass newType = new IndexMappingRequest.TypeClass(type);
        IndexMappingRequest indexMappingRequest = new IndexMappingRequest(Map.of(property, newType));
        request.source(serialize(indexMappingRequest), XContentType.JSON);
        client.indices().putMapping(request, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public void storeEvent(Event event) {
        IndexRequest indexRequest = new IndexRequest(EVENT_INDEX);
        indexRequest.source(serialize(event), XContentType.JSON);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public List<Event> searchAll() {
        SearchRequest searchRequest = new SearchRequest(EVENT_INDEX);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return mapResponse(search);
    }

    @SneakyThrows
    @Override
    public List<Event> searchBy(Event.Type type) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("eventType", type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(EVENT_INDEX);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return mapResponse(search);
    }

    @SneakyThrows
    @Override
    public List<Event> searchBy(String title) {
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("title", title);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(EVENT_INDEX);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return mapResponse(search);
    }

    @SneakyThrows
    @Override
    public List<Event> searchBy(LocalDate afterDate, String name) {
        String queryString = format("datetime > %s AND title:*%s*", serialize(afterDate), name);
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(queryString);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryStringQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(EVENT_INDEX);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        return mapResponse(search);
    }

    @SneakyThrows
    private String serialize(Object o) {
        return mapper.writeValueAsString(o);
    }

    private <T> T deserialize(Class<T> clazz, Map<String, Object> response) {
        return mapper.convertValue(response, clazz);
    }

    private List<Event> mapResponse(SearchResponse search) {
        return Arrays.stream(search.getHits().getHits())
                .map(SearchHit::getSourceAsMap)
                .map(o -> deserialize(Event.class, o))
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class IndexMappingRequest {
        private Map<String, TypeClass> properties;

        @Getter
        @Setter
        @AllArgsConstructor
        private static class TypeClass {
            private String type;
        }
    }
}
