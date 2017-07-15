package tw.com.softleader.sample.movie;

import java.util.Collection;

public class MPerson {

	private Long id;
	private String name;
	private String idno;
	private Long cId;
	private Collection<Movie> movies;

	@Override
	public String toString() {
		return "MPerson : id= " + id + ",name= " + name + "cId" + cId + "movies" + movies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Collection<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Collection<Movie> movies) {
		this.movies = movies;
	}

}