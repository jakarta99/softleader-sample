package tw.com.triplei.portal.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 我要提問
 * 
 * @author Zhao Xun
 */
@Entity
@Table(name = "QUESTION")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ASKER_EMAIL")
	private String askerEmail;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "QUESTION_TYPE")
	private String questionType;

	@Column(name = "POST_TIME")
	private LocalDateTime postTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAskerEmail() {
		return askerEmail;
	}

	public void setAskerEmail(String askerEmail) {
		this.askerEmail = askerEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

}
