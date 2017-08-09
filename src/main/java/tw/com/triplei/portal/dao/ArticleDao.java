package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

}
