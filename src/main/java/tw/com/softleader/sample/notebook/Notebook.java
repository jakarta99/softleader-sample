package tw.com.softleader.sample.notebook;

import java.math.BigDecimal;

/**
 * <br> 筆記型電腦
 */
public class Notebook {
	
	// Primary Key
	private Long id;
	// 品牌
	private String brand;
	// 規格
	private String specification;
	// 報價
	private BigDecimal quotation;
	
	private Long wpersonId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getQuotation() {
		return quotation;
	}

	public void setQuotation(BigDecimal quotation) {
		this.quotation = quotation;
	}

	public Long getWpersonId() {
		return wpersonId;
	}

	public void setWpersonId(Long wpersonId) {
		this.wpersonId = wpersonId;
	}

	@Override
	public String toString() {
		return "Notebook [id=" + id + ", brand=" + brand + ", specification=" + specification + ", quotation="
				+ quotation + ", wpersonId=" + wpersonId + "]";
	}

}
