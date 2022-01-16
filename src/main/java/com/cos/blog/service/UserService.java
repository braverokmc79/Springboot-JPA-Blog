package com.cos.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 서비스
 * 1. 트랜잭션 관리
 * 2. 두개 이상 서비스 create, update,
 *
 * select 만 있을 경우 : @Transactional(readOnly = true)
 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC 를 해준다. *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    //@Autowired   @RequiredArgsConstructor 통해 생성
    private final UserRepository userRepository;

    public User userJoin(User user) {
        User result=userRepository.save(user);      
        return result;
    }


}
