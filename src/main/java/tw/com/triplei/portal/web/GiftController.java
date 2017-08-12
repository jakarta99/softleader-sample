package tw.com.triplei.portal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.com.triplei.portal.entity.Gift;
import tw.com.triplei.portal.service.GiftService;

@RequestMapping("/gift")
@Controller
public class GiftController {

//	 private Gift gift = new Gift();

	@Autowired
	private GiftService giftService;

	@RequestMapping("/list")
	public ModelAndView viewGifts(Model model) {
		model.addAttribute("gift", new Gift());
		return new ModelAndView("/gift-list", "giftlist", giftService.getAll());
	}

	@RequestMapping("/insert")
	public ModelAndView addNewGift(@ModelAttribute("gift") Gift gift) {
		giftService.insert(gift);
		return new ModelAndView("/AddGiftSuccess", "newGift", gift);
	}

	@RequestMapping("/update")
	public ModelAndView updateGift(@ModelAttribute("gift") Gift gift) {
		giftService.insert(gift);
		return new ModelAndView("/gift-list", "giftlist", giftService.getAll());
	}

	@RequestMapping("/delete")
	public ModelAndView deleteGift(@PathVariable("id") long id) {
		giftService.delete(id);
		return new ModelAndView("/gift-list", "giftlist", giftService.getAll());
	}

}
