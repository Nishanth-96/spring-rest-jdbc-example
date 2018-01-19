package main.java.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import main.java.dao.MovieDAO;
import main.java.model.Actor;
import main.java.model.Movie;

public class MovieDAOImpl implements MovieDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Movie movie) {
		// TODO Auto-generated method stub
		String query = "insert into Movies values(\"" + movie.getMovieName()+ "\",\"" + movie.getOverview() +"\"," +
		movie.getRating() +")";
		return jdbcTemplate.update(query);
	}

	@Override
	public int delete(Movie movie) {
		// TODO Auto-generated method stub
		String query = "delete from Movies where movie_name = \"" + movie.getMovieName() + "\"";
		return jdbcTemplate.update(query);
	}

	@Override
	public int update(Movie movie) {
		// TODO Auto-generated method stub
		String query = "update Movies set movie_name=\"" +movie.getMovieName() + "\",overview=\""+movie.getOverview()
		+ "\",rating=" + movie.getRating();
		return jdbcTemplate.update(query);
	}

	@Override
	public List<Movie> list() {
		// TODO Auto-generated method stub
		String query = "select * from Movies";
		List<Movie> movies = new ArrayList<>();
		jdbcTemplate.query(query,new ResultSetExtractor<List<Movie>>() {
			@Override
			public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					Movie movie = new Movie();
					movie.setMovieName(rs.getString(1));
					movie.setOverview(rs.getString(2));
					movie.setRating(rs.getFloat(3));
					movie.setActors(getActorsFromMovie(movie));
					movies.add(movie);	
				}
				return null;
			}
		});
		return movies;
	}


	@Override
	public Movie list(Movie movie) {
		// TODO Auto-generated method stub
		String query = "select * from Movies where movie_name=\"" + movie.getMovieName() +"\"";
		Movie selectedMovie = new Movie();
		jdbcTemplate.query(query, new ResultSetExtractor<Movie>() {
			@Override
			public Movie extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					selectedMovie.setMovieName(rs.getString(1));
					selectedMovie.setOverview(rs.getString(2));
					selectedMovie.setRating(rs.getFloat(3));
					selectedMovie.setActors(getActorsFromMovie(selectedMovie));	
				}
				return null;
			}
		});
		return selectedMovie;
	}
	
	private List<Actor> getActorsFromMovie(Movie movie) {
		// TODO Auto-generated method stub
		ApplicationContext  context = new ClassPathXmlApplicationContext("/main/resources/JdbcBean.xml");
		ActorDAOImpl actorDAOImpl = (ActorDAOImpl)context.getBean("actorBean");
		
		List<Actor> actors = actorDAOImpl.list(movie);
		return actors;
	}
	
	

}
