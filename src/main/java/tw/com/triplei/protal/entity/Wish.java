package tw.com.triplei.protal.entity;

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
}
