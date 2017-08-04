package tw.com.triplei.protal.entity;

/**
 * 積點商品
 */
public class Gift {

	private Long id;
	
	private String giftType;
	
	private String name;
	
	private String brand; 
	
	private String image1; // 預設可放3張積點商品的圖片
	
	private String image2;
	
	private String image3;
	
	private int bonus; // 兌換點數
	
	private int exchangeCount; // 累積兌換次數
	
	private int exchangePersonMax; // 對大購買數量
	

}
