package tw.com.triplei.portal.entity;

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

	@Override
	public String toString() {
		return "Insurer [id=" + id + ", name=" + name + ", information=" + information + ", logo=" + logo
				+ ", complaintRatio=" + complaintRatio + ", bisRatio=" + bisRatio + "]";
	}

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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public BigDecimal getComplaintRatio() {
		return complaintRatio;
	}

	public void setComplaintRatio(BigDecimal complaintRatio) {
		this.complaintRatio = complaintRatio;
	}

	public BigDecimal getBisRatio() {
		return bisRatio;
	}

	public void setBisRatio(BigDecimal bisRatio) {
		this.bisRatio = bisRatio;
	}

}
