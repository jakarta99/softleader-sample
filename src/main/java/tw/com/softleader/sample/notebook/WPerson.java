package tw.com.softleader.sample.notebook;

import java.util.Collection;

public class WPerson {

	private Long id;  
	
	/** 姓名 */
	private String name;
	
	/** ID */
	private String idno;

	/** 公司ID */
	private Long companyId;
	
	private Collection<Notebook> notebooks;
	
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Collection<Notebook> getNotebooks() {
		return notebooks;
	}

	public void setNotebooks(Collection<Notebook> notebooks) {
		this.notebooks = notebooks;
	}

	@Override
	public String toString() {
		return "WPerson [id=" + id + ", name=" + name + ", idno=" + idno + ", notebooks=" + notebooks + "]";
	}

}
