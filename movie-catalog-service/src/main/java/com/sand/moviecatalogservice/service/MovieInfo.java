package com.sand.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sand.moviecatalogservice.model.CatalogItem;
import com.sand.moviecatalogservice.model.Movie;
import com.sand.moviecatalogservice.model.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo
{
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
  public CatalogItem getCatalogItem(Rating rating)
  {
    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
  }

  public CatalogItem getFallbackCatalogItem(Rating rating)
  {
    return new CatalogItem("No catalog item", "", rating.getRating());
  }
}
