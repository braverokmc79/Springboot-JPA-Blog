let user ={
	
	init:function(){
		$("#btn-save").on("click",()=>{
			//function()P{, ()=>{} this를 바인딩하기 위해서,
			// function을 사용시에는 this 아니라  변수 user 를 사용 예) user.save()
			this.save();
		});

		$("#btn-login").on("click", function(){
			user.login();
		});
	},


	save:function(){
		const $home=$("#home").val();
		let data={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};

		console.log(data);
		console.log("$home : " +$home);
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청!
        //application/json 통신시 반환되는 dataType 은 json

		$.ajax({
			type:"POST",
			url:$home+"api/user",
			data:JSON.stringify(data),	//JSON 문자열로 변환 - http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json"	 //요청을 서버로해서 응담이 왔을 때 기본적으로 모든 것이 문자열(생긴것이 json이라면 ) => javascript
		}).done(function(res, status){
			console.log(res, status);
			alert("회원가입이 완료 되었습니다.");
			location.href=$home+"user/loginForm";
		}).fail(function(res, status, error){
			console.log(res, status, error);
			console.log("res.responseText :" +res.responseText);
			console.log(JSON.stringify(res));
			alert(res.responseText);
		});


		// $.ajax({
		// 	type:"POST",
		// 	url:$home+"/api/user",
		// 	data:JSON.stringify(data),	//JSON 문자열로 변환 - http body 데이터
		// 	contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지(MIME)
		// 	dataType:"json"	 //요청을 서버로해서 응담이 왔을 때 기본적으로 모든 것이 문자열(생긴것이 json이라면 ) => javascript
		// 	,success:function(result, status){
		// 		//console.log(result, status);
		// 	},
		// 	error:function(res, status, error){
		// console.log(res, status, error);
		// console.log("data :" +res.responseText);
		// console.log(JSON.stringify(res));
		// alert(res.responseText);
		// 	}			
		// });
	  

	},

	login:function(){
		const $home=$("#home").val();
		let data={
			username:$("#username").val(),
			password:$("#password").val(),
		};

		console.log(data);
	
		$.ajax({
			type:"POST",
			url:$home+"api/user/login",
			data:JSON.stringify(data),	//JSON 문자열로 변환 - http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json"	 //요청을 서버로해서 응담이 왔을 때 기본적으로 모든 것이 문자열(생긴것이 json이라면 ) => javascript
		}).done(function(res, status){
			console.log("done");
			console.log(res, status);
			if(res==1){
				alert("로그인이 완료 되었습니다..");
				location.href=$home;
			}			
		}).fail(function(res, status, error){
			console.log("fail");
			console.log(res, status, error);
			console.log(JSON.stringify(res));
			if(res.responseText=="2"){
				alert("아이디 또는 비밀번호가 일치 하지 않습니다.");
			}else{
				alert(res.responseText);
			}			
		});

	}

}

user.init();