package com.sand.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import com.sand.moviecatalogservice.model.CatalogItem;
import com.sand.moviecatalogservice.model.UserRating;
import com.sand.moviecatalogservice.service.MovieInfo;
import com.sand.moviecatalogservice.service.MovieRatingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
  @Autowired
  private MovieInfo movieInfo;

  @Autowired
  private MovieRatingInfo movieRatingInfo;

  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
  {
    UserRating userRating = movieRatingInfo.getRating(userId);
    return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
        .collect(Collectors.toList());
  }

}
