package com.cos.blog.test;

import com.cos.blog.constant.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class DummyControllerTest {


    private final UserRepository userRepository;

    //http://localhost:8000/blog/dummy/join (요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        System.out.println("Role 정보2 :" + RoleType.USER);
        System.out.println("user Data: " + user.toString());
        System.out.println("userRepository : " +userRepository);

        userRepository.save(user);
        return "회원 가입이 완료되었습니다77!!";
    }



}
