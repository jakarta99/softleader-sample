package tw.com.softleader.sample.notebook;

import java.util.List;

/**
 * <br> 公司
 * @author Frank
 *
 */
public class WCompany {

	private Long id;

	/** 統一編號 */
	private String idno;

	/** 公司名稱 */
	private String name;

	private List<WPerson> personList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<WPerson> personList) {
		this.personList = personList;
	}

	@Override
	public String toString() {
		return "WCompany [id=" + id + ", idno=" + idno + ", name=" + name + "]";
	}

}
