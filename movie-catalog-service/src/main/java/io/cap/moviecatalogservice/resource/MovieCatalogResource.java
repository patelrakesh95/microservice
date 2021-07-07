package io.cap.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.cap.moviecatalogservice.models.CatalogModel;
import io.cap.moviecatalogservice.models.Movie;
import io.cap.moviecatalogservice.models.Rating;
import io.cap.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate template;

//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("{userId}")
	public List<CatalogModel> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = template.getForObject("http://movie-rating-service/ratingsdata/users/" + userId,
				UserRating.class);
		List<Rating> ratings = userRating.getUserRating();

		return ratings.stream().map(rating -> {
			Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

//			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
//					.retrieve().bodyToMono(Movie.class).block();
			return new CatalogModel(movie.getMovieInfo(), "Test Movie", rating.getRating());
		}).collect(Collectors.toList());

		// return Collections.singletonList(new CatalogModel("Transformers", "Test",
		// 4));

	}

}
