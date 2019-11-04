package com.sand.moviecatalogservice.service;

import java.util.Arrays;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

  @HystrixCommand(
      fallbackMethod = "getFallbackRating",
      ,
      //      threadPoolKey = "movieRatingInfoPool",
      //      threadPoolProperties = {
      //          @HystrixProperty(name = "coreSize", value = "20"),
      //          @HystrixProperty(name = "maxQueueSize", value = "10")
      //      },
      commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
      @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
      @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50"),
      @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5000")
  })
  public UserRating getRating(String userId)
  {
    return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
  }

  public UserRating getFallbackRating(String userId)
  {
    return new UserRating(Arrays.asList(new Rating("0", 0)));
  }
}
