package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG="HttpControllerTest :";

    @GetMapping("/http/lombok")
    private String lombokTest(){
        Member m=Member.builder().username("ssr").password("111").email("sss@gmail.com").build();
        System.out.println(TAG+"getter : " +m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter : " +m.getId());
        System.out.println(TAG + ": " +m.getUsername());
        return "lombok  test 완료";
    }


    //http://localhost:8080/http/get
    @GetMapping(value = "/http/get")
    public String getTest(Member m){
        System.out.println(m.getEmail());
        return "get 1 요청";
    }

    //http://localhost:8080/http/post
    @PostMapping(value = "/http/post")
    public String postTest(@RequestBody  Member member){
        return member.toString();
    }


    //http://localhost:8080/http/put
    @PutMapping(value = "/http/put")
    public String  putTest(){
        return "put 요청";
    }

    //http://localhost:8080/http/delete
    @DeleteMapping(value = "/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }



}

