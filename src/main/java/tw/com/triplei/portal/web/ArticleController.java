package tw.com.triplei.portal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.com.triplei.portal.entity.Article;
import tw.com.triplei.portal.service.ArticleService;

@RequestMapping("/article")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/list")
	public String getListPage(Model model) {
		List<Article> articles = articleService.getAll();
		model.addAttribute("models", articles);
		return "/article-list";
	}

	@RequestMapping("/page")
	public String getPagePage(Model model) {

		return "/Article-Page";
	}

}
