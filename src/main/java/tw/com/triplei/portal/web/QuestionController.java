package tw.com.triplei.portal.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tw.com.triplei.portal.entity.Question;
import tw.com.triplei.portal.service.QuestionService;

@RequestMapping("/question")
@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionservice;
	
	@RequestMapping("/view")
	public ModelAndView showView(@Valid @ModelAttribute("question")Question question,BindingResult result,Model model) {
		return new ModelAndView("/askQuestions");
	}
	
	@RequestMapping("/list")
	public String getAllQuestion(Model model) {
		List<Question> list = questionservice.getAll();
		model.addAttribute("questionlist", list);
		return "/questions";
	}


	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question")Question question,Model model) {

		LocalDateTime posttime = LocalDateTime.now();
		question.setPostTime(posttime);
		// 暫時沒有問題類別
		question.setQuestionType("測試用");

		questionservice.insert(question);
		model.addAttribute("paste",question);
		return "/askQuestions";

	}

}
