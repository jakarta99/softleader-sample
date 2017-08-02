package tw.com.triplei.setting.entity;

import javax.persistence.*;

/**
 * 
 * 保險公司
 * 
 * 
 * @author Gary Lee
 *
 */
@Entity
@Table(name = "SETTING_INSURER")
public class SettingInsurer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LOGO_FILE")
	private String logoFile;
	
	
}
