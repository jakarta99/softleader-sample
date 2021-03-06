package tw.com.triplei.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.triplei.portal.enums.GiftType;

/**
 * 積點商品
 * 
 * @author Tiger Chang
 */

@Entity
@Table(name = "GIFT")
public class Gift {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
//	@Enumerated(EnumType.STRING)
	@Column(name = "GIFT_TYPE")
	private String giftType;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "IMAGE1")
	private String image1; // 預設可放3張積點商品的圖片

	@Column(name = "IMAGE2")
	private String image2;

	@Column(name = "IMAGE3")
	private String image3;

	@Column(name = "BONUS")
	private int bonus; // 兌換點數

	@Column(name = "EXCHANGE_COUNT")
	private int exchangeCount; // 累積兌換次數

	@Column(name = "EXCHANGE_PERSON_MAX")
	private int exchangePersonMax; // 對大購買數量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(int exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public int getExchangePersonMax() {
		return exchangePersonMax;
	}

	public void setExchangePersonMax(int exchangePersonMax) {
		this.exchangePersonMax = exchangePersonMax;
	}

}
