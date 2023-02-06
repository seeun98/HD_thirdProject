package org.goonagoobob.mapper.member;

import org.goonagoobob.domain.member.memberAccount;
import org.goonagoobob.service.member.memberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class memberServiceTests {
	
	@Autowired
	private memberService service;
	
	@Test
	public void selectById() {
		memberAccount mA = service.selectById("user1");
		System.out.println(mA);
	}
}