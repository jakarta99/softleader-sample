package tw.com.triplei.portal.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
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

	public interface GetProduct extends Repository<Product,Long>{	//一個可以取值呼叫的api
		List<Product> findByInsurerId(Long insurerId);
	}
	
	@RequestMapping("/list")
	public String getProductList(@ModelAttribute("product") Product product,Model model){
		List<Product> products= productService.getAll();
		model.addAttribute("product", products);
		return "/product-stuff";
	}
	
	@RequestMapping("/shop")
	public String enterShopPage(Long id) {
		productService.getOne(id);
		return "/product-stuff";
	}
}
