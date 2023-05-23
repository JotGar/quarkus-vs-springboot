package org.com.services.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.smallrye.mutiny.Uni;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import org.com.models.ApiMoviesHeaders;
import org.com.models.MoviesPublicApiRs;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@RegisterClientHeaders
public interface RestService {

    @GET
    Uni<MoviesPublicApiRs> getTrends(@BeanParam ApiMoviesHeaders requestManagementHeaders);

    @GET
    Uni<JsonNode> getPlatforms(@BeanParam ApiMoviesHeaders requestManagementHeaders);
}
