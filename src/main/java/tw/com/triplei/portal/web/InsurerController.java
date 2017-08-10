package tw.com.triplei.portal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("models", insurers);
		return "/insurer-list";
	}

}
