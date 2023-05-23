package com.springtest.demo.controllers;


import com.springtest.demo.models.MoviesBasicInfo;
import com.springtest.demo.services.TrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trends")
public class MoviesController {


    @Autowired
    TrendsService trendsService;

    @GetMapping("")
    public List<MoviesBasicInfo> getTrends() {
        return trendsService.getTrends();
    }


    @GetMapping("/movies")
    public List<MoviesBasicInfo> getMoviesTrends(@RequestHeader("X-MinVoteAverage") double minVoteAvg) {
        return trendsService.getMoviesTrends(minVoteAvg);
    }

}
