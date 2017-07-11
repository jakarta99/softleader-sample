package tw.com.softleader.sample.drink;

import java.util.Collection;

public class DPerson {

	private Long id;
	
	private String name;
	
	private String idno;
	
	private Collection<Drink> drinks;

	@Override
	public String toString() {
		return "DPerson [id=" + id + ", name=" + name + ", idno=" + idno + ", drinks=" + drinks + "]";
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

	public Collection<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(Collection<Drink> drinks) {
		this.drinks = drinks;
	}
	
	
	
	
}
