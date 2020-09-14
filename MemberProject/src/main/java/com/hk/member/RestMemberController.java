package com.hk.member;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hk.member.service.MemberService;
import com.hk.member.vo.Member;

@RestController  //화면(jsp파일명 리턴이 필요하지않다, 데이터값만 리턴)
@RequestMapping(value="/member/rest") //순수한 형태의 text로 주겠다.
public class RestMemberController {
	private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);

	@Autowired
	MemberService memberService;

	@GetMapping(path="/list", produces="text/plain;charset=UTF-8")
	public String memberRestList(Locale locale, Model model) {
		logger.info("/member/rest/list ----------");
		return "이건 서버에서 보내준 /member/rest/list";
	}

	@GetMapping(path = "/listjson", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> memberRestListJson(Locale locale, Model model) {
		logger.info("/member/rest/listJson ----------");
		
		return memberService.memberList();
	}
	
	@GetMapping(path = "/listxml", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Member> memberRestListxml(Locale locale, Model model) {
		logger.info("/member/rest/listxml ----------");
		
		return memberService.memberList();
	}

	@GetMapping(path="/addJson" , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> memberRestAddJSon(@RequestParam("name") String name , @RequestParam("pwd") String pwd) { 
		logger.info("-----------------");
		logger.info("Client에서 보내온 값은 === " + name + ":::" + pwd);

		return memberService.memberList();
	}


}
