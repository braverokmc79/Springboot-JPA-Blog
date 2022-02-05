package com.cos.blog.constant;

public enum RoleType {
    //스프링부트 2.5 이상 역할은 자동으로 삽입되므로 'ROLE_'로 시작하면 안 됩니다
    GUEST ,    //손님
    USER  ,    //일반 사용자
    MANAGER ,  //중간 관리자
    ADMIN ;    //최고 관리자
}
