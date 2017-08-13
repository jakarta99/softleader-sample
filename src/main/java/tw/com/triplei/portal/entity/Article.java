package tw.com.triplei.portal.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.triplei.portal.enums.ArticleType;

/**
 * 文章
 * 
 * 
 * @author Howard Chang
 */

@Entity
@Table(name = "ARTICLE")
public class Article {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ARTICLE_TYPE")
	private ArticleType articleType; // 文章類別

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
