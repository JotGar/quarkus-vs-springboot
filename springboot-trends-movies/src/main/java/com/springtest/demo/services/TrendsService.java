package com.springtest.demo.services;

import com.springtest.demo.models.MoviesBasicInfo;
import com.springtest.demo.models.MoviesPublicApiRs;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TrendsService {

    public static final String DEFAULT_LANGAUGE = "es-CO";
    public static final String LANGUAGE = "language";

    @Value("${tmdb.api.endpoint}")
    String apiTrendsEndpoint;

    @Value("${tmdb.api.endpoint.provider}")
    String apiProvidersEndpoint;

    @Value("${tmdb.api.key}")
    String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<MoviesBasicInfo> getMoviesTrends(double minVoteAvg) {
        CompletableFuture.runAsync(this::printMovieProviders);
        
        return getTrends().stream().filter(moviesBasicInfo -> moviesBasicInfo.getMediaType().equals("movie") &&
                moviesBasicInfo.getVoteAverage() >= minVoteAvg).toList();
    }

    public List<MoviesBasicInfo> getTrends() {
        ResponseEntity<MoviesPublicApiRs> movieTrends = exchange(getUrl(apiTrendsEndpoint), HttpMethod.GET, MoviesPublicApiRs.class);

        return movieTrends.getBody() != null ?
                movieTrends.getBody().getResults().stream().map(MoviesBasicInfo::new).toList() : null;
    }

    private void printMovieProviders() {
        ResponseEntity<JsonNode> movieTrends = exchange(getUrl(apiProvidersEndpoint), HttpMethod.GET, JsonNode.class);
        System.out.println(movieTrends);
    }

    private <T> ResponseEntity<T> exchange(String url, HttpMethod httpMethod, Class<T> classToResponse) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", apiKey);
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(httpHeaders), classToResponse);
    }

    private String getUrl(String url) {
        return UriComponentsBuilder.fromUriString(url).
                queryParam(LANGUAGE, DEFAULT_LANGAUGE).toUriString();
    }
    


}
