package tw.com.triplei.protal.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 保險公司
 * 
 * @author Tiger Chang
 */
@Entity
@Table(name = "INSURER")
public class Insurer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "INFORMATION")
	private String information;

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "COMPLAINT_RATIO")
	private BigDecimal complaintRatio; // 投訴率

	@Column(name = "BIS_RATIO")
	private BigDecimal bisRatio; // 資本適足率 Bank of International Settlement ratio

}
