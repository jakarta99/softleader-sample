package tw.com.softleader.sample.game;

public class G_Person {
	private Long pId;
	private String pName;
	private String pIdno;
	private Long id;
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
	
}
	