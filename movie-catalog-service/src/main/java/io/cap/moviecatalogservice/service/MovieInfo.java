package io.cap.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.cap.moviecatalogservice.models.Movie;
import io.cap.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
	public Movie getCatalogItem(Rating rating) {
		return template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
	}

	public Movie getFallBackCatalogItem(Rating rating) {
		return new Movie("0", "");
	}
	
}
