package tw.com.triplei.portal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.triplei.portal.entity.Gift;
import tw.com.triplei.portal.service.GiftService;

@RequestMapping("/gift")
@Controller
public class GiftController {

	@Autowired
	private GiftService giftService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getNames(Model model) {
		List<Gift> gifts = giftService.getAll();
		model.addAttribute("giftlist", gifts);
		return "/gift-list";
	}

}
