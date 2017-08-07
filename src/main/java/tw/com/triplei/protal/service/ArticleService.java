package tw.com.triplei.protal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.protal.dao.ArticleDao;
import tw.com.triplei.protal.entity.Article;
import tw.com.triplei.protal.entity.Product;

@Service
public class ArticleService {
	

	@Autowired
	private ArticleDao articleDao;
	

	public Article getOne(Long id) {
		return articleDao.findOne(id);
	}

	public List<Article> getAll() {
		return articleDao.findAll();
	}

	public Article insert(Article article) {
		return articleDao.save(article);
	}

	public Article update(Article article) {
		return articleDao.save(article);
	}

	public boolean delete(Long id) {
		articleDao.delete(id);
		if (articleDao.findOne(id) != null) {
			return false;
		}
		return true;
	}

}
