package tw.com.triplei.protal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 保險(儲蓄險)商品
 * 
 * 
 * @author Sarah Chou
 */

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "INSURER_ID")
	private Long insurerId;
	
	// 其他商品細項待確認需求後陸續增加

}
