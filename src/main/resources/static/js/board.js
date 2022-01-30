let board ={
	
	init:function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});

	},


	save:function(){
		const $home=$("#home").val();
		const token = $("meta[name='_csrf']").attr("content");
		const header = $("meta[name='_csrf_header']").attr("content");

		let data={
			title:$("#title").val(),
			content:$("#content").val(),
		};

		console.log(data);
		console.log("$home : " +$home);


		$.ajax({
			type:"POST",
			beforeSend:function(xhr){
				xhr.setRequestHeader(header,token);
			},
			url:$home+"api/board",
			data:JSON.stringify(data),
			contentType:"application/json; charset=urf-8", 
			dataType:"json"	
		}).done(function(res, status){
			console.log(res, status);
			//alert("글쓰기가 완료 되었습니다.");
			location.href="/";
			
			
		}).fail(function(res, status, error){
			console.log(res, status, error);
			console.log("res.responseText :" +res.responseText);
			console.log(JSON.stringify(res));
			alert(res.responseText);
		});

	  

	},
	
	

	login:function(){
		const $home=$("#home").val();		
		const token = $("meta[name='_csrf']").attr("content");
		const header = $("meta[name='_csrf_header']").attr("content");
		
		let data={
			username:$("#username").val(),
			password:$("#password").val(),
		};

		console.log(data);
	
		$.ajax({
			type:"POST",
			beforeSend:function(xhr){
				xhr.setRequestHeader(header,token);
			},
			url:$home+"auth/loginProc",
			//data:JSON.stringify(data),	//JSON 문자열로 변환 - http body 데이터
			data:data,	//JSON 문자열로 변환 - http body 데이터
			//contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지(MIME)
			//dataType:"json"	 //요청을 서버로해서 응담이 왔을 때 기본적으로 모든 것이 문자열(생긴것이 json이라면 ) => javascript
		}).done(function(res, status){
			console.log("done");
			console.log(status);
			console.log(res);
			if(status=="success"){
				alert(res);
				//location.href=$home;
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

board.init();
