package org.goonagoobob.service.cart;

import java.util.List;

import org.goonagoobob.domain.cart.cartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class cartServiceTests {
	@Autowired
	private cartService service;
	
	@Test
	public void getCartList() {
		String mid = "user1";
		List<cartVO> list = service.getCartList(mid);
		log.info(list);
	}
}
