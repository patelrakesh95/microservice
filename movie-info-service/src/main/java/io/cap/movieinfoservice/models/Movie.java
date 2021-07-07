package io.cap.movieinfoservice.models;

public class Movie {

	private String movieId;
	private String movieInfo;
	
	public Movie(String movieId, String movieInfo) {
		this.movieId = movieId;
		this.movieInfo = movieInfo;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieInfo() {
		return movieInfo;
	}
	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}
	
	
	
}
