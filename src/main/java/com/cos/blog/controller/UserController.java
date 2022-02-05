package com.cos.blog.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.constant.KakaoRequestCode;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**  허용
//그냥주소가 /이면 index.jsp 허용
//static 이하여 있는  /js/** , /css/** ,  /image/**


@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


	private final PrincipalDetailService principalDetailService;
	
    
    @Value("${cos.key}")
    private String cosKey;
    
    
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    

    @GetMapping("/auth/loginForm")
    public String loginForm() {        
        return "user/loginForm";
    }

    

    @GetMapping(value = "/auth/loginForm/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "user/loginForm";
    }
    
//    
//    @GetMapping("/user/updateForm")
//    public String updateUserForm(Principal principal,  Model model) {
//    	User userInfo =userService.getByUsername(principal.getName());
//    	model.addAttribute("userInfo" ,userInfo);    	
//    	return "user/updateUserForm";
//    }
    
    /**
     * PrincipalDetails 사용시
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/user/updateForm")
    public String updateUserForm(@AuthenticationPrincipal PrincipalDetails principal,  Model model) {
    	model.addAttribute("userInfo" ,principal.getUser());    	
    	return "user/updateUserForm";
    }
    
    
    
    @GetMapping("/auth/kakao/callback")
    //@ResponseBody
    public String kakaoCallback(String code) {
/**    
 * 		https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#refresh-token
    	POST /oauth/token HTTP/1.1
    	Host: kauth.kakao.com
    	Content-type: application/x-www-form-urlencoded;charset=utf-8
    	    

    	grant_type   : authorization_code
    	client_id  :  3e5c01480582f1d1db8f55cea63a6e820
    	redirect_uri : http://localhost:8000/auth/kakao/callback
    	code  :
   */ 	
    	/** 2. 반환된 code 을 사용하여 헤더와 바디가 포함된  post 방식으로  다시 kako 에 요청  */
	    //POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
	    //Retrofit2
	    //OkHttp
	    //RestTemplate    
    	RestTemplate rt =new RestTemplate();
    	
    	//HttpHeader 오브젝트 생성
    	HttpHeaders headers=new HttpHeaders();
    	headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
    	
    	//HttpBody 오브젝트 생성
    	MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
    	params.add("grant_type", KakaoRequestCode.GRANT_TYPE);
    	params.add("client_id", KakaoRequestCode.CLIENT_ID);
    	params.add("redirect_uri",KakaoRequestCode.REDIRECT_URI);
    	params.add("code",code);
    	
    	//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
    	HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest=new HttpEntity<>(params, headers);
    	
    	//Http 요청하기  - Post 방식으로 - 그리고 response 변수의 응답 받음.
    	ResponseEntity<String> response=rt.exchange(
    			"https://kauth.kakao.com/oauth/token",    			
    			HttpMethod.POST,
    			kakaoTokenRequest,
    			String.class
    	);
/*
 * 
 * 	다음과 같이 response.getBody() 로 다음과 같은 JSON 데이터를 받을수 있다.
{
	"access_token": "yq3nNezpw27tM1-SfdsfTshFJSMZj_f5OTfdsfDfqniLrM2gopcNEAAAF-wfAvgw",
	"token_type": "bearer",
	"refresh_token": "JmZpH9TnnsBEo10wsdfZV123dssWhT4CN3cjl-MVh0F-xMQopfcNEAAAF-wfAvgQ",
	"expires_in": 21599,
	"scope": "account_email profile_image gender profile_nickname",
	"refresh_token_expires_in": 5183999
}
 *     	
 *     
 *       
 */
    	
    	/** 3. 위 JSON 데이터를  Gson, Json Simple , ObjectMapper 등로로 파싱후 access_token(카카오 엑세트 토큰)만 추출하여 얻는다. */
    	//OAuthToken 객체는 위 Gson 데이터와 동일한 변수명으로 만든다.
    	// 다음은 ObjectMapper 를 이용하여 json 파싱후 oauthToken  추출
    	ObjectMapper objectMapper=new ObjectMapper();
    	OAuthToken oauthToken =null;
    	try {
    		oauthToken=objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
   
    	
    	/** 4. 카카오엑세트 토큰을 이용하여 다시 카카오에 요청 후 사용자 정보 조회를 한다. (ex 이메일, 닉네임 ..)   */
/*    	
    	GET/POST /v2/user/me HTTP/1.1
		Host: kapi.kakao.com
		Authorization: Bearer {ACCESS_TOKEN}
		Content-type: application/x-www-form-urlencoded;charset=utf-8
    	 	    	
    	요청주소 : https://kapi.kakao.com/v2/user/me
*/
    	

    	RestTemplate rt2 =new RestTemplate();
    	
    	//HttpHeader 오브젝트 생성
    	HttpHeaders headers2=new HttpHeaders();
    	headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
    	headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
    	
    	//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
    	HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest=new HttpEntity<>(headers2);
    	
    	//Http 요청하기  - Post 방식으로 - 그리고 response 변수의 응답 받음.
    	ResponseEntity<String> response2=rt2.exchange(
    			"https://kapi.kakao.com/v2/user/me",    			
    			HttpMethod.POST,
    			kakaoProfileRequest,
    			String.class
    	);
    	
    	
  
/**

{
  "id": 2298323097,
  "connected_at": "2021-11-30T09:33:34Z",
  "properties": {
    "nickname": "홍길동"
  },
  "kakao_account": {
    "profile_nickname_needs_agreement": false,
    "profile": {
      "nickname": "홍길동"
    },
    "has_email": true,
    "email_needs_agreement": false,
    "is_email_valid": true,
    "is_email_verified": true,
    "email": "honggildong@gmail.com"
  }
}

 */
    
	/** 5. 위 JSON 데이터를  Gson, Json Simple , ObjectMapper 등로로 파싱후 KakaoProfile 객체에 저장. */
	/**	
	 * 다음 사이틀 이용해서 KakaoProfile 객체를 생성
	 * https://www.jsonschema2pojo.org/
	 * 
	 * */
    	ObjectMapper objectMapper2=new ObjectMapper();
    	KakaoProfile kakaoProfile =null;
    	try {
    		kakaoProfile=objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
    	
    	
    	System.out.println(" cosKey : " + cosKey);
    	System.out.println(" kakaoProfile : " + kakaoProfile.toString());
    	System.out.println("카카오 아이디(번호):" + kakaoProfile.getId());
    	System.out.println("카카오 이메일 : "+ kakaoProfile.getKakao_account().getEmail());
    	
    	System.out.println("블로그서버 유저네임 : " +kakaoProfile.getKakao_account().getEmail() + "_"+kakaoProfile.getId());
    	UUID garbasePassword=UUID.randomUUID();
    	System.out.println("블로그서버 이메일 :" + kakaoProfile.getKakao_account().getEmail());
    	System.out.println("블로그서버 패스워드 : "+garbasePassword);
    	System.out.println();
    	
    	User kakaoUser=User.builder()
    			.username(kakaoProfile.getKakao_account().getEmail() + "_"+kakaoProfile.getId())
    			.password(cosKey)
    			.email(kakaoProfile.getKakao_account().getEmail())
    			.oauth("kakao")
    			.build();
    	
    	
    	//가입자 혹은 비가입자 체크처리
    	User user=userService.findByUserName(kakaoProfile.getKakao_account().getEmail() + "_"+kakaoProfile.getId());
    	if(user==null) {
    		userService.userJoin(kakaoUser);	
    		user=kakaoUser;
    	}
    	
    	//UsernamePasswordAuthenticationToken 아이디외 비밀번호가 필요하기때문에
    	//카카오 oauth 로그인 비밀번호는  cosKey 값으로 모두 동일하다. 보안 주의 
      //  Authentication authentication= new UsernamePasswordAuthenticationToken(user.getUsername(), cosKey);
      //SecurityContextHolder.getContext().setAuthentication(authentication);    	    
        
    	
    	
    	sessionReset(user);
    	
    	
    	
    	return "redirect:/";    	
    }
    
    

    /**
     *  시큐리티 세션 재설정  
     *  시큐리티 세션 재설정은 서비스에서  트랜잭션이 종료된 후 실행되는  컨트롤에서 설정해야 한다. 
     */
    public  void sessionReset(User user) {    	
        //유저 한명에 권한이 여러개 설정될수 있기 때문에 list 한다. ex)GUEST,USER ,MANAGER,ADMIN  
        Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> user.getRole().toString());
       
		UserDetails  userDetails=principalDetailService.loadUserByUsername(user.getUsername());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    
    
    
}
