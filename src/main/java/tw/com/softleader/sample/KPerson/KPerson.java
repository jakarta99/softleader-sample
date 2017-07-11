package tw.com.softleader.sample.KPerson;

import java.util.Collection;
import tw.com.softleader.sample.cloth.Cloth;


public class KPerson {
	
	
	private Long id;
	
	private String name;
	private String idNo;
	private Collection<Cloth> clothes;
	@Override
	public String toString(){
		return " ID : " + id + " NAME : " + name + " IDNO :" + idNo + " CLOTHES :" + clothes ;
		
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

	public Collection<Cloth> getClothes() {
		return clothes;
	}

	public void setClothes(Collection<Cloth> clothes) {
		this.clothes = clothes;
	}





	
}
