package tw.com.triplei.protal.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章
 */

@Entity
@Table(name = "ARTICLE")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ARTICLE_TYPE")
	private String articleType; // 文章類別

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "BANNER_IMAGE")
	private String bannerImage;

	@Column(name = "PUBLISH_TIME")
	private LocalDateTime publishTime; // 文章發布時間

	@Column(name = "CLICK_COUNT")
	private int clickCount; // 點擊數

	
	
	
	
}
