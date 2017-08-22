package tw.com.triplei.portal.web;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.triplei.portal.entity.Insurer;
import tw.com.triplei.portal.service.InsurerService;

@RequestMapping("/insurer")
@Controller
public class InsurerController {

	@Autowired
	private InsurerService insurerService;

	@RequestMapping("/list")
	public String getListPage(Model model) {
		List<Insurer> insurers = insurerService.getAll();
		model.addAttribute("insurer", new Insurer());
		model.addAttribute("models", insurers);
		return "/insurer-list";
	}

	@RequestMapping("/select")
	public String getOneInsurer(@RequestParam("id") Long id, Model model) {
		Insurer insurerselectone = insurerService.getOne(id);
		model.addAttribute("insurer", new Insurer());
		model.addAttribute("models", insurerselectone);
		return "/insurer-list";
	}


	@RequestMapping("/update")
	public String updateInsurer(@ModelAttribute("insurer") Insurer insurer, Model model) {
		Insurer insurerupdate = insurerService.update(insurer);
		model.addAttribute("insurer", new Insurer());
		if (insurer.getId() != null) {
			model.addAttribute("models", insurerupdate);
		} else {
			model.addAttribute("models", insurer);
		}
		return "redirect:list";
	}

	@RequestMapping("/delete")
	public String deleteGift(@ModelAttribute("insurer") Insurer insurer, Model model) {
		insurerService.delete(insurer.getId());
		List<Insurer> insurers = insurerService.getAll();
		model.addAttribute("models", insurers);
		return "redirect:list";
	}

}

