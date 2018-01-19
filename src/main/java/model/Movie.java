package main.java.model;

import java.util.List;

public class Movie{
	
	private String movieName;
	private String overview;
	private float rating;
	private List<Actor> actors;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public float getRating() {
		return rating;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}

}
