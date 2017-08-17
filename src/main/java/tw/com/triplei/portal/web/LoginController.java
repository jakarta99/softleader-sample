package tw.com.triplei.portal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tw.com.triplei.portal.entity.User;
import tw.com.triplei.portal.service.UserService;

@RequestMapping("/login")
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
		return new ModelAndView("/LoginPage","userInfo",user);
	}
	
}
