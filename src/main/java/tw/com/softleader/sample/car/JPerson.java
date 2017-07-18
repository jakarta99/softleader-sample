package tw.com.softleader.sample.car;

import java.util.Collection;

public class JPerson {
	
	private Long id;
	
	private String idNo;
	
	private String name;
	
	private Long companyId;
	
	private Collection<Car> cars;

	@Override
	public String toString() {
		return "JPerson [id=" + id + ", name=" + name + ", idNo = " + idNo + ", companyId="+companyId+"]";
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

	public Collection<Car> getCars() {
		return cars;
	}

	public void setCars(Collection<Car> cars) {
		this.cars = cars;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
