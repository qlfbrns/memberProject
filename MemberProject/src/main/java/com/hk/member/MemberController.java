package com.hk.member;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.member.service.MemberService;
import com.hk.member.vo.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberService;

	@Autowired
	ServletContext sc;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}


	//@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	@GetMapping("/member/list")
	public String memberList(Locale locale, Model model) {
		//콘트롤러 역할
		//1.URL 설정(@Requestmapping, @GetMapping, @PostMapping)
		//2.DB관련작업
		//    -Service
		//    -Repository
		//3.비지니스로직(덧셈뺄셈..뭐든지..)
		//4.사용자가 입력한 정보가져오고(request.getParameter("name"))
		//5.보관소에 값 저장(sc,session,request >> model)
		//6.jsp호출  "memberList" --> 메소드 명하고 일치       규칙은 WEB-INF/spring/appServlet/servlet-context.xml에서 확인가능



		//		logger.info("Welcome home! The client locale is {}.", locale);
		//		
		//		Date date = new Date();
		//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//		
		//		String formattedDate = dateFormat.format(date);

		model.addAttribute("members", memberService.memberList());

		return "memberList";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST , headers = "content-type=multipart/*")
	//@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile,Model model) {
		logger.info("### upload");
		logger.info("실제 파일이름은 ? " + multipartFile.getOriginalFilename());
		File targetFile = new File(sc.getRealPath("/resources/fileupload/") + multipartFile.getOriginalFilename());
		logger.info("파일 저장위치는 :  " + targetFile);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
		}

		// 실제 디렉토리와 URL은 다르다.. 
		// URL은 http://localhost:9999/resources/fileupload/실제파일명
		// model에 담아서 jsp에서 img로 출력 

		model.addAttribute("imgSrc", "/resources/fileupload/" + multipartFile.getOriginalFilename());
		return "upload";
	}

	@GetMapping("/member/register")
	public String memberRegisterGet(Model model) {
		logger.info("/member/register 호출");
		return "memberRegister";
	}

	@PostMapping("/member/register")
	public String memberRegisterPost(Model model, Member member) {
		// 사용자가 입력한 값을 가져온다.
		// 1) request.getParameter -- 예전 servlet 방식
		// 2) @RequestParam을 이용하여 가져온다. -servlet방식과 동일
		// 3) DTO를 선언한다. Client에 있는 html name 값이 DTO와 동일하면  spring에서 알아서 넣어준다.
		logger.info("/member/registerPost 호출");

		logger.info("사용자에게 입력받은 값 = " + member.toString());
		int retVal = memberService.memberRegister(member);
		logger.info("insert 후에 성공인지 실패인지 알려줌...[" + retVal + "]");
		model.addAttribute("name", member.getMname());
		return "memberRegisterDone";
	}

	@GetMapping("/member/update")
	public String memberUpdateGet(@RequestParam("mno") int mno, Model model) {
		// DB에 입력되어 있는 값을 1개 조회하여
		// model에 넣는다.
		// @RequestParam형태로 가져옴...
		logger.info("/member/updateGet 호출");

		logger.info("사용자에게 입력받은 값 = " + mno);
		model.addAttribute("member",memberService.memberGetOne(mno));
		return "memberUpdateGet";
	}

	@PostMapping("/member/update")
	public String memberUpdatePost(Member member, Model model) {
		// 조희된 데이터값을 수정하여서
		// model에 넣는다.
		// 
		logger.info("/member/memberUpdatePost 호출");

		logger.info("사용자에게 입력받은 값 = " + member.toString());
		memberService.memberUpdate(member);
		model.addAttribute("member", member);
		return "memberUpdatePost";
	}

	@GetMapping("/member/delete")
	public String memberDeleteGet(@RequestParam("mno") int mno, Model model) {

		logger.info("/member/deleteGet 호출");

		logger.info("사용자에게 입력받은 값 = " + mno);
		model.addAttribute("mno",mno);
		return "memberDeleteGet";
	}

	@PostMapping("/member/delete")
	public String memberDeletePost(@RequestParam("mno") int mno, Model model) {

		logger.info("/member/deletePost 호출");
		logger.info("사용자에게 입력받은 값 = " + mno);
		memberService.memberDelete(mno);

		return "memberDeletePost";
	}

	@GetMapping("/member/login")
	public String memberLoginGet(Model model) {

		logger.info("/auth/loginGet 호출");

		return "auth/loginForm";
	}

	@PostMapping("/member/login")
	public String memberLoginPost(Member member, Model model,HttpServletRequest request) {

		logger.info("/auth/loginPost 호출");

		member = memberService.exist(member);		

		if (member!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			return "redirect:/member/list";
		} else {

			return "auth/loginFail";
		}
	}
	
	@GetMapping("/member/logout")
	public String memberLogout(Model model,HttpServletRequest request) {

		logger.info("/auth/logout 호출");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/member/login";
	}

}
