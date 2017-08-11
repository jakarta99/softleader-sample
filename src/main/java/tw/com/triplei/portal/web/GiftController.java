package tw.com.triplei.portal.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.triplei.portal.entity.Gift;
import tw.com.triplei.portal.service.GiftService;

@RequestMapping("/gift")
@Controller
public class GiftController {
	
	private Gift gift = new Gift();
	
	
	@Autowired
	private GiftService giftService;

	@RequestMapping("/list")
	public String getAll(Model model) {
		List<Gift> gifts = giftService.getAll();
		model.addAttribute("gift",gift);
		model.addAttribute("giftlist", gifts);
		return "/gift-list";
	} 
	
    @RequestMapping("/insert")
    public String addNewGift(@Valid @ModelAttribute Gift gift,BindingResult result,Model model){
    	if (result.hasErrors()) {
            return "error";
        }
    	giftService.insert(gift);
    	model.addAttribute("newGift", gift);
        return "/AddGiftSuccess";
    }
	
    @RequestMapping("/delete")
    public String deleteGift(@Valid @ModelAttribute Gift gift,BindingResult result,Model model){
    	if (result.hasErrors()) {
            return "error";
        }
    	giftService.delete(gift.getId());
    	List<Gift> gifts = giftService.getAll();
    	model.addAttribute("giftlist", gifts);
        return "/gift-list";
    }	
	
	
}
