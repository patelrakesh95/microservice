package io.cap.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.cap.moviecatalogservice.models.Rating;
import io.cap.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallBackUserRatings")
	public UserRating getUserRatings(String userId) {
		return template.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
	}

	public UserRating getFallBackUserRatings(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}
	
}
