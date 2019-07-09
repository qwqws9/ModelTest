<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
// $("#search").on('click',function(){
// 	history($("#keyword").text());
// });
// function history(keyword){
// 	popWin = window.open("/history.jsp",
// 						"popWin",
// 						"left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
// }


$(function(){
	
	
	
	$(".pageNavi").on("click",function(){
		$.ajax( 
				{
					url : "/tour/json/tourList",
					method : "POST" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},data : JSON.stringify({
						keyword : "${list[0].keyword}",
						pageNum : $(this).text()
					}),
					
					success : function(JSONData , status) {
						//alert(status);
						//alert(JSONData);
						
						//alert(JSONData.length); // 길이 판별 후 20개 이하면 이후 숫자 div 삭제하기
						
						$.each(JSONData, function(index,item){
							//이름을 변경하고난 후..
							//alert(index + item.tourName);
							//alert(index + item.tourId);
							$(".preview-"+(index+1)+ " > span > img").attr("src",item.tourThumb);
							$(".preview-"+(index+1)+ " > div:nth-child(2) > h4").text(item.tourName);
							$(".preview-"+(index+1)+ " > div:nth-child(2) > span").text(item.tourShortDesc);
							$(".preview-"+(index+1)+ " > div:nth-child(3)").text(item.tourLoc);
							
						});
						//alert("지금부터 지우자");
							 	$("div[class^=preview-]").each(function(idx){ // idx 0부터 시작함
						 			if(JSONData.length <= idx) {
						 				$(".preview-"+(idx+1)).remove();
						 			}
						 		});	
								
					}
			}); 
	})
});

</script>
</head>
<body>

<form action="/tour/tourList" method="post">

	<input id="keyword" type="text" name="keyword">
	<input type="submit" value="검색">
	<img alt="" src="">
</form>

<c:if test="${!empty list }">
	<c:forEach var="list" items="${list }" varStatus="i">
		<div class="preview-${i.index+1 }">
		<span><img src="${list.tourThumb }" height="150" width="150"></span>
		<div>
		<h4>${list.tourName }</h4>
		<span>${list.tourShortDesc }</span>
		</div>
			<div>${list.tourLoc }			
			</div>
		</div>
	</c:forEach>
	
	<c:forEach var="i" begin="1"  end="${list[0].viewCount }">
		<a href="#" class="pageNavi">${i }</a>
	</c:forEach>
	
</c:if>

</body>
</html>