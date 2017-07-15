package tw.com.softleader.sample.game;

import java.util.Collection;

public class Gcompany {

	private Long id;

	private String gCname;

	private Collection<GPerson> gpersons;
	
	private Collection<Game> games;

	@Override
	public String toString() {
		return "Gcompany [id=" + id + ", GCname=" + gCname + ", gpersons=" + gpersons + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGCname() {
		return gCname;
	}

	public void setGCname(String gCname) {
		this.gCname = gCname;
	}

	public Collection<GPerson> getGpersons() {
		return gpersons;
	}

	public void setGpersons(Collection<GPerson> gpersons) {
		this.gpersons = gpersons;
	}

	public Collection<Game> getGames() {
		return games;
	}

	public void setGames(Collection<Game> games) {
		this.games = games;
	}

}
//