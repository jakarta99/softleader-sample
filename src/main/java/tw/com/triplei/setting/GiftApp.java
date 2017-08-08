package tw.com.triplei.setting;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.triplei.protal.entity.Gift;
import tw.com.triplei.protal.service.GiftService;

public class GiftApp {

	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		GiftService giftService = (GiftService) application.getBean("giftService");

		for (Gift gift : giftService.getAll()) {
			System.out.println(gift);
		}

	}
}
