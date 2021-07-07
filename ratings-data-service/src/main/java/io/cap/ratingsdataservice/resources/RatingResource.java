package io.cap.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cap.ratingsdataservice.Models.Rating;
import io.cap.ratingsdataservice.Models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("Movie Rating 1", 4), new Rating("Movie Rating 2", 5));

		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);

		return userRating;
	}

}
