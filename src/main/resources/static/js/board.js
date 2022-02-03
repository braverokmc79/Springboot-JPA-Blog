let board ={
	
	init:function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		
		$("#btn-update").on("click",()=>{
			this.update();
		});
		
		$("#btn-delete").on("click",()=>{
			this.deleteById();
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
	
	

	deleteById:function(){
		
		if(confirm("정말 삭제 하시겠습니까?")){
				const id=$("#id").text();
				const $home=$("#home").val();
				const token = $("meta[name='_csrf']").attr("content");
				const header = $("meta[name='_csrf_header']").attr("content");
		
				$.ajax({
					type:"DELETE",
					beforeSend:function(xhr){
						xhr.setRequestHeader(header,token);
					},
					url:$home+"api/board/"+id,				
					dataType:"json"	
				}).done(function(res, status){
					console.log(res, status);
					alert("글이 삭제 되었습니다.");
					location.href="/";
					
					
				}).fail(function(res, status, error){
					console.log(res, status, error);
					console.log("res.responseText :" +res.responseText);
					console.log(JSON.stringify(res));
					alert(res.responseText);
				});
		}


	},
	
		
	
	
	update:function(){
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
	
	
	
	

}

board.init();
