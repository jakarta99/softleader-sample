package tw.com.softleader.sample.fruit;

import java.util.Collection;

public class Fman {
	private long id;
	
	private String name;
	
	private String IDno;
	
	private Collection<Fruit> fruits;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIDno() {
		return IDno;
	}

	public void setIDno(String iDno) {
		IDno = iDno;
	}

	public Collection<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(Collection<Fruit> fruits) {
		this.fruits = fruits;
	}

	
	
}
