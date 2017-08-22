package tw.com.triplei.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import tw.com.triplei.portal.enums.gender;

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
//	@ManyToOne(fetch=FetchType.EAGER,targetEntity="")
	private Long insurerId;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "DEPOSIT_METHOD")
	private String depositmethod;
	
	
	@Column(name = "INTEREST")
	private String interest;
	
	@Column(name = "SUM")
	private String sum;
	
	@Column(name = "PERIOD")
	private String period;
	
	@Column(name = "BACK")
	private String back;
	
	@Column(name = "INSURER")
	private String insurer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Long insurerId) {
		this.insurerId = insurerId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDepositmethod() {
		return depositmethod;
	}

	public void setDepositmethod(String depositmethod) {
		this.depositmethod = depositmethod;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String getInsurer() {
		return insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}
	// 其他商品細項待確認需求後陸續增加

}
