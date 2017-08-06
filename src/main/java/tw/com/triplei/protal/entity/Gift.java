package tw.com.triplei.protal.entity;

import javax.persistence.*;

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

}
