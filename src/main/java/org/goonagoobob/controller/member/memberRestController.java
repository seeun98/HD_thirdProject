package org.goonagoobob.controller.member;

import javax.servlet.http.HttpServletResponse;

import org.goonagoobob.domain.member.memberAccount;
import org.goonagoobob.domain.member.memberJoin;
import org.goonagoobob.service.member.memberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/memberRestAPI")
@RequiredArgsConstructor
public class memberRestController {
	private final memberService service;
	
	
	/*
	 * @PostMapping(value = "/joinMemberAccount", consumes = "application/json",
	 * produces = { MediaType.APPLICATION_JSON_VALUE })
	 */
	@RequestMapping(value="/joinMemberAccount", method=RequestMethod.POST)
	public ResponseEntity<String> joinMember(HttpServletResponse response, @RequestBody memberJoin mj) throws Exception {
		System.out.println(mj);
		System.out.println("password?? " + mj.getMpassword());
		
		int num = service.joinMemberAccount(mj);
		
		return num == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/idCheck", consumes = "application/json", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void idCheck(@RequestBody String mid) {

		log.info("id 체크를 해보자 ... ");
		memberAccount mA = service.selectById(mid);
		System.out.println(mA.getMid());

	}

}