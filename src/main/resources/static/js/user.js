let user ={
	
	init:function(){
		$("#btn-save").on("click",()=>{ //function()P{, ()=>{} this를 바인딩하기 위해서,  function을 사용시에는 this 아니라  변수 user 를 사용. user.save()		
			this.save();
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

		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청!
        //application/json 통신시 반환되는 dataType 은 json

		$.ajax({
			type:"POST",
			url:$home+"/api/user",
			data:JSON.stringify(data),	//JSON 문자열로 변환 - http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json"	 //요청을 서버로해서 응담이 왔을 때 기본적으로 모든 것이 문자열(생긴것이 json이라면 ) => javascript
		}).done(function(res, status){
			console.log(res, status);
			alert("회원가입이 완료 되었습니다.");
			//location.href=$home;
		}).fail(function(errorRes){
			console.log("errorRes");
			console.log(errorRes);
			console.log("status :" +errorRes.status);			
			alert(JSON.stringify(errorRes));
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
		// 	error:function(errorRes){
		// 			console.log("errorRes");
		// 			console.log(errorRes);
		// 			console.log("status :" +errorRes.status);			
		// 			alert(JSON.stringify(errorRes));
		// 	}			
		// });
	  
		//error : function(jqXHR, status, error){

	}

}


user.init();