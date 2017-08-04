package tw.com.triplei.protal.entity;

import java.time.LocalDateTime;

/**
 * 文章
 */
public class Article {

	private Long id;
	
	private String articleType; // 文章類別
	
	private String title;
	
	private String content;
	
	private String author;
	
	private String bannerImage;
	
	private LocalDateTime publishTime; // 文章發布時間
	
	private int clickCount; // 點級數
}
