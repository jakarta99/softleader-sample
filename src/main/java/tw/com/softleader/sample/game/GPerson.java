package tw.com.softleader.sample.game;

import java.util.Collection;

public class GPerson {
	private Long pId;
	private String pName;
	private String pIdno;
	private Long id;
	private Collection<Game> games;
	//private Long name;
	//private Long type;
	
	
	@Override
	public String toString() {
		return "G_Person [pId=" + pId + ", pName=" + pName + ", pIdno=" + pIdno + ", id=" + id + "]";
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpIdno() {
		return pIdno;
	}
	public void setpIdno(String pIdno) {
		this.pIdno = pIdno;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<Game> getGames() {
		return games;
	}
	public void setGames(Collection<Game> games) {
		this.games = games;
	}
	
}
	