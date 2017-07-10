package tw.com.softleader.sample.movie;

public class MPerson {

	private Long id;
	private String name;
	private String idno;
	private String moviename1;
	private String moviename2;
	private String moviename3;

	@Override
	public String toString() {
		return "MPerson [id=" + id + ", name=" + name + ", idno=" + idno + ", moviename1="+moviename1+
				", moviename2="+moviename2+", moviename3="+moviename3+"]";
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

	public String getMoviename1() {
		return moviename1;
	}

	public void setMoviename1(String moviename1) {
		this.moviename1 = moviename1;
	}
	public String getMoviename2() {
		return moviename2;
	}

	public void setMoviename2(String moviename2) {
		this.moviename2 = moviename2;
	}
	public String getMoviename3() {
		return moviename3;
	}

	public void setMoviename3(String moviename3) {
		this.moviename3 = moviename3;
	}
	
}