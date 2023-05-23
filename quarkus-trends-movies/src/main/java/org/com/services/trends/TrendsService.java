package org.com.services.trends;

import com.fasterxml.jackson.databind.JsonNode;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriBuilder;
import org.com.models.ApiMoviesHeaders;
import org.com.models.MoviesBasicInfo;
import org.com.models.MoviesPublicApiRs;
import org.com.services.rest.RestService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class TrendsService {

    public static final String DEFAULT_LANGAUGE = "es-CO";
    public static final String LANGUAGE = "language";

    @ConfigProperty(name = "tmdb.api.endpoint")
    String apiTrendsEndpoint;

    @ConfigProperty(name = "tmdb.api.endpoint.provider")
    String apiProvidersEndpoint;

    @ConfigProperty(name = "tmdb.api.key")
    String apiKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrendsService.class);

    private final RestClientBuilder restClientBuilder = RestClientBuilder.newBuilder();

    public Uni<List<MoviesBasicInfo>> getTrends() {
        Uni<MoviesPublicApiRs> moviesPublicApiRsUni = getRestService(apiTrendsEndpoint).getTrends(getRequestManagementHeaders());
        return moviesPublicApiRsUni.onItem().transform(moviesPublicApiRs ->
                moviesPublicApiRs.getResults().stream().map(MoviesBasicInfo::new).toList());
    }

    public Uni<List<MoviesBasicInfo>> getMoviesTrends(double minVoteAvg) {
        printMovieProviders();

        return getTrends().onItem().transform(moviesBasicInfoList -> moviesBasicInfoList.
                stream().filter(moviesBasicInfo -> moviesBasicInfo.getMediaType().equals("movie") &&
                        moviesBasicInfo.getVoteAverage() >= minVoteAvg).toList());
    }

    private void printMovieProviders() {
        Uni<JsonNode> platforms = getRestService(apiProvidersEndpoint).getPlatforms(getRequestManagementHeaders());
        platforms.subscribeAsCompletionStage().whenComplete((moviesPublicApiRs, throwable) ->
                LOGGER.info("Movies provider - Colombia: {}", moviesPublicApiRs.toPrettyString()));
    }

    private RestService getRestService(String apiEndpoint) {
        URI uri = UriBuilder.fromUri(apiEndpoint).queryParam(LANGUAGE,DEFAULT_LANGAUGE).build();
        return restClientBuilder.baseUri(uri).build(RestService.class);
    }

    private ApiMoviesHeaders getRequestManagementHeaders() {
        ApiMoviesHeaders apiMoviesHeaders = new ApiMoviesHeaders();
        apiMoviesHeaders.setAuthorization(apiKey);
        return apiMoviesHeaders;
    }
}
