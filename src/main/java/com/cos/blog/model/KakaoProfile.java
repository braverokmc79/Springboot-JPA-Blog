package com.cos.blog.model;

import groovy.transform.ToString;
import lombok.Data;
//
//
//"id": 209833097,
//"connected_at": "2021-11-30T09:33:34Z",
//"properties": {
//  "nickname": "최준호"
//},
//"kakao_account": {
//  "profile_nickname_needs_agreement": false,
//  "profile": {
//    "nickname": "홍길동"
//  },
//  "has_email": true,
//  "email_needs_agreement": false,
//  "is_email_valid": true,
//  "is_email_verified": true,
//  "email": "honggildong@gmail.com"
//}

@Data
@ToString
public class KakaoProfile {
	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	public class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}

	@Data
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;

		@Data
		public class Profile {
			public String nickname;
			public String profile_image;
			public String thumbnail_image;
		}
	}

}
