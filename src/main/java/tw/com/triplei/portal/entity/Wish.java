package tw.com.triplei.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 許願池
 * 
 * author Joe the Awesome
 * 
 */

@Entity
@Table(name = "Wish")
public class Wish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "IMAGE1")
	private String image1; // 商品圖片

	@Column(name = "WISH_TYPE")
	private String wishType;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "WEEK_USE_COUNT")
	private int weekUseCount; // 累積許願池使用次數

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getWeekUseCount() {
		return weekUseCount;
	}

	public void setWeekUseCount(int weekUseCount) {
		this.weekUseCount = weekUseCount;
	}
	
	
}
