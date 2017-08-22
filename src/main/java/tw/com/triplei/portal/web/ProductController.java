package tw.com.triplei.portal.web;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.com.triplei.portal.entity.Product;
import tw.com.triplei.portal.service.ProductService;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/filter")
	public String showFilter(Model model) {
		model.addAttribute("product",new Product());
		return "/product-stuff";
	}

	@RequestMapping("/list")
	public String getProductList(@ModelAttribute("product") Product product,Model model){
		List<Product> products= productService.getAll();
		model.addAttribute("product",new ArrayList<Product>());
		model.addAttribute("models", products);
		return "/product-stuff";
	}
	
	@RequestMapping("/hide")
	public String getProductList2(@RequestParam("detail") long id,Model model) {
		Product product= productService.getOne(id);
		model.addAttribute("model", product);
		return "/pp";
	}
	
}
