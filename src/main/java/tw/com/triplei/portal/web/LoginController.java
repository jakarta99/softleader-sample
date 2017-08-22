package tw.com.triplei.portal.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.triplei.portal.entity.User;
import tw.com.triplei.portal.service.UserService;

@RequestMapping("/login")
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	private User user;

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		userService.loginCheck(user);
		User userCheck = userService.loginCheck(user);
		System.out.println(userCheck);
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if (userCheck!=null) {
			out.write(userCheck.getName());
		} else {
	        out.write("/index"); 
		}
		out.close();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		Long phone = Long.valueOf(request.getParameter("phone"));
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setPhone(phone);
		user.setGender(gender);
		
		System.out.println(user);
		User userInserted = userService.insert(user);
		System.out.println(userInserted);
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if (userInserted!=null) {
			out.write(userInserted.getName());
		} else {
	        out.write("/index"); 
		}
		out.close();
	}
}
