package com.sand.movieinfoservice.resource;

import java.util.Arrays;
import java.util.List;

import com.sand.movieinfoservice.model.Movie;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource
{
  @RequestMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") String movieId)
  {
    List<Movie> movies = Arrays.asList(
        new Movie("123", "Joker",
        "DescriptionForever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City"),
        new Movie("234", "Hobbs and Shaw",
        "DescriptionBrixton Lorr is a cybernetically enhanced soldier who possesses superhuman strength, a brilliant mind and a lethal pathogen that could wipe out half of the world's population" ),
        new Movie("345", "Captain Marvel",
        "DescriptionCaptain Marvel is an extraterrestrial Kree warrior who finds herself caught in the middle of an intergalactic battle between her people and the Skrulls")
    );
    return movies.stream().filter(m -> m.getMovieId().equals(movieId)).findFirst().get();
  }
}
