package main.java.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.daoImpl.ActorDAOImpl;
import main.java.daoImpl.MovieDAOImpl;
import main.java.model.Actor;
import main.java.model.Movie;

@RestController
public class MainController {

	Movie movie = new Movie();
	Actor actor = new Actor();
	ApplicationContext context;
	
	/* GET REQUESTS */
	
	@RequestMapping(value = "/movies",method = RequestMethod.GET)
	public List<Movie> getAllMovies() {
		context = getContext();
		MovieDAOImpl movieDAOImpl = getMovieBean();
		
		List<Movie> movies =  movieDAOImpl.list();
		return movies;
	}
	
	@RequestMapping(value = "/movies/{name}",method = RequestMethod.GET)
	public Movie getMovie(@PathVariable String name) {
		context = getContext();
		MovieDAOImpl movieDAOImpl = getMovieBean();
		
		movie.setMovieName(name);
		movie = movieDAOImpl.list(movie);
		return movie;
	}
	
	@RequestMapping(value = "/actors",method = RequestMethod.GET)
	public List<Actor> getAllActors(){
		context = getContext();
		ActorDAOImpl actorDAOImpl = getActorBean();
		
		List<Actor> actors =  actorDAOImpl.list();
		return actors;
	}


	@RequestMapping(value = "/actors/{name}",method = RequestMethod.GET)
	public List<Actor> getActor(@PathVariable String name) {
		context = getContext();
		ActorDAOImpl actorDAOImpl = getActorBean();
		
		actor.setActorName(name);
		List<Actor> actors = actorDAOImpl.list(actor);
		return actors;
	}

	
	/* POST REQUESTS */
	
	@RequestMapping(value="/movies/{name}",method = RequestMethod.POST)
	public void saveMovie(@RequestBody Movie movie)
	{
		context = getContext();
		MovieDAOImpl movieDAOImpl = getMovieBean();
		ActorDAOImpl actorDAOImpl = getActorBean();
		movieDAOImpl.save(movie);
		
		List<Actor> actors = movie.getActors();
		for(Actor actor : actors) {
			actorDAOImpl.save(actor);
		}
	}
	
	/* DELETE REQUESTS */
	
	@RequestMapping(value="/movies/{name}" , method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable String name)
	{
		context = getContext();
		MovieDAOImpl movieDAOImpl = getMovieBean();
		Movie movie = new Movie();
		movie.setMovieName(name);
		movieDAOImpl.delete(movie);
	}
	
	
	private ApplicationContext getContext() {
		// TODO Auto-generated method stub
		return new ClassPathXmlApplicationContext("/main/resources/JdbcBean.xml");
	}

	private ActorDAOImpl getActorBean() {
		// TODO Auto-generated method stub
		return (ActorDAOImpl) context.getBean("actorBean");		
	}
	
	private MovieDAOImpl getMovieBean() {
		// TODO Auto-generated method stub
		return (MovieDAOImpl) context.getBean("movieBean");
		
	}
	
	
}
