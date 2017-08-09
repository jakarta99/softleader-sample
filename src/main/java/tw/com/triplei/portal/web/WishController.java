package tw.com.triplei.portal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.portal.entity.Wish;
import tw.com.triplei.portal.service.WishService;

@RequestMapping("/wish")
@Controller
public class WishController {

	@Autowired
	private WishService wishService;
	
	@RequestMapping("/list")
	public String checkWeekUseCount(Model model) {
		//check login-->re-direct to /gift/list 
		//-->onclick wish, checkweekusecount 
		List<Wish> wishes = wishService.getAll();
		model.addAttribute("wishlist", wishes);

		return "/wish-list";
	}

}
