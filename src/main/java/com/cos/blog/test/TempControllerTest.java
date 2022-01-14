package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping(value = "/temp/home")
    public String tempHeme(){
        System.out.println("tempHome update()");
        //1.파일리턴 기본경로: src/main/resources/static

        //2. thymeleaf 라이브러리 등록 시
//            <dependency>
//            <groupId>org.springframework.boot</groupId>
//            <artifactId>spring-boot-starter-thymeleaf</artifactId>
//        </dependency>
//
// 파일 html  리턴기본경로: src/main/resources/templates

        return "home.html";
    }


    @GetMapping(value = "/temp/test")
    public String testJsp(){

        //jsp 사용시 pom.xml 에서 주석처리한다.
        //spring-boot-starter-thymeleaf
        //
        System.out.println("JSP 페이지");
        return "test";
    }



}



