package org.com.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "original_language",
        "original_title",
        "overview",
        "media_type",
        "release_date",
        "vote_average"
})
public class MoviesBasicInfo {

    @JsonProperty("title")
    private String title;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("vote_average")
    private Double voteAverage;


    public MoviesBasicInfo() {
    }

    public MoviesBasicInfo(MoviesInfo moviesInfo) {
        this.title = moviesInfo.getTitle() == null ? moviesInfo.getName() : moviesInfo.getTitle();
        this.originalLanguage = moviesInfo.getOriginalLanguage();
        this.overview = moviesInfo.getOverview();
        this.mediaType = moviesInfo.getMediaType();
        this.releaseDate = moviesInfo.getReleaseDate();
        this.voteAverage = moviesInfo.getVoteAverage();
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("original_language")
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @JsonProperty("original_language")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @JsonProperty("original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    @JsonProperty("original_title")
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @JsonProperty("overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("vote_average")
    public Double getVoteAverage() {
        return voteAverage;
    }

    @JsonProperty("vote_average")
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

}