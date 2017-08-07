package tw.com.triplei.protal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.protal.entity.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

}
