package tw.com.softleader.sample.color;

import java.util.Collection;

public class Hperson {
	private Long id;
	private String name;
	private String idNo;
	private Collection<Color> colors;
	
	@Override
	public String toString() {
		return "Hperson [id=" + id + ", name=" + name + ", idNo=" + idNo + ", colors=" + colors + "]";
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
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Collection<Color> getColors() {
		return colors;
	}
	public void setColors(Collection<Color> colors) {
		this.colors = colors;
	}

}
