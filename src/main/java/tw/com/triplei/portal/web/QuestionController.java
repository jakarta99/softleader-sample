package tw.com.triplei.portal.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.portal.entity.Question;
import tw.com.triplei.portal.service.QuestionService;

@RequestMapping("/question")
@Controller
public class QuestionController {
	@Autowired
	QuestionService questionservice;


	@RequestMapping("/list")
	public String getAllQuestion(Model model) {
		List<Question> list = questionservice.getAll();
		model.addAttribute("questionlist", list);
		return "/questions";
	}

	@RequestMapping("/insert")
	public String addQuestion(@ModelAttribute("Question")Question question, Model model) {

		LocalDateTime posttime = LocalDateTime.now();
		question.setPostTime(posttime);
		// 暫時沒有問題類別
		question.setQuestionType("測試用");

		questionservice.insert(question);

		return "/AskQuestionSuccess";

	}

}
