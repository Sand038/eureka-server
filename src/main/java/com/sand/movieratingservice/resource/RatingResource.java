package com.sand.movieratingservice.resource;

import com.sand.movieratingservice.model.Rating;
import com.sand.movieratingservice.model.UserRating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource
{
  @RequestMapping("/{movieId}")
  public Rating getRating(@PathVariable("movieId") String movieId)
  {
    return new Rating(movieId, 4);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getUserRatings(@PathVariable("userId") String userId)
  {
    return new UserRating(Arrays.asList(new Rating("123", 8), new Rating("234", 7), new Rating("345", 9)));
  }
}
