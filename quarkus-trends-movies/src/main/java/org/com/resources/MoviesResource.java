package org.com.resources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.com.models.MoviesBasicInfo;
import org.com.services.trends.TrendsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/trends")
public class MoviesResource {

    @Inject
    TrendsService trendsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesResource.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<MoviesBasicInfo>> getTrends() {
        return trendsService.getTrends();
    }


    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<MoviesBasicInfo>> getMoviesTrends(@HeaderParam("X-MinVoteAverage") double minVoteAvg) {
        LOGGER.info("Init get movies service");
        return trendsService.getMoviesTrends(minVoteAvg);
    }

}


