package com.sand.moviecatalogservice.service;

import java.util.Arrays;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sand.moviecatalogservice.model.Rating;
import com.sand.moviecatalogservice.model.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieRatingInfo
{
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "getFallbackRating")
  public UserRating getRating(String userId)
  {
    return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
  }

  public UserRating getFallbackRating(String userId)
  {
    return new UserRating(Arrays.asList(new Rating("0", 0)));
  }
}
