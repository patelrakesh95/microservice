package io.cap.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cap.moviecatalogservice.models.CatalogModel;
import io.cap.moviecatalogservice.models.Movie;
import io.cap.moviecatalogservice.models.Rating;
import io.cap.moviecatalogservice.models.UserRating;
import io.cap.moviecatalogservice.service.MovieInfo;
import io.cap.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("{userId}")
	public List<CatalogModel> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = userRatingInfo.getUserRatings(userId);
		List<Rating> ratings = userRating.getUserRating();

		return ratings.stream().map(rating -> {
			Movie movie = movieInfo.getCatalogItem(rating);

//			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
//					.retrieve().bodyToMono(Movie.class).block();
			return new CatalogModel(movie.getMovieInfo(), "Test Movie", rating.getRating());
		}).collect(Collectors.toList());

		// return Collections.singletonList(new CatalogModel("Transformers", "Test",
		// 4));

	}

}
