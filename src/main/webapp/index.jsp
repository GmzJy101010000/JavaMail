<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/mail/libs/jquery.js"></script>
<style type="text/css">
#sbtn{
    position:absolute;
    left:12%;
    top:38%;
 
    }
 .s{
  left:9%;
   position:absolute;
  
 }
 #r1{
  position:absolute;
    left:22%;
    top:13%;
    font-size:22px;
    color:#FF0000;
    font-weight:bold;
 }
  #r2{
  position:absolute;
    left:22%;
    top:20%;
    font-size:22px;
    color:#FF0000;
    font-weight:bold;
 }
 #r3{
   position:absolute;
    left:22%;
    top:28%;
    font-size:22px;
    color:#FF0000;
    font-weight:bold;
 }
 .s1{
 font-family :"New York";
 font-size:22px;
  font-weight:bold;
 } 
 #mail{
  left:3%;
  position:absolute;
 }  
</style>
<script type="text/javascript">

function sendMail(){
	var a=yanzform();
	
	if(a){
		var pdata=$("form").serializeArray();
		$.ajax({
			  url: "/mail/zhuce",
			  data:pdata,
			  type:"POST",
			  success: function( result ) {
				 alert(123);
			   window.location.href="/mail/page/sendMailSuccess.jsp";
			  },
			  error:function( result ) {
			   alert("error");
			  }
			});
		
	}
	
}
function yanzform(){
	var name =$("#na").val();
	var pass =$("#ps").val();
	var mail =$("#em").val();
	if(name==""||pass==""||mail==""){
		if(name==""){
		$("#r1")[0].style.display="block";
		
	}else{$("#r1")[0].style.display="none";}
	if(pass==""){
		$("#r2")[0].style.display="block";
		
	}else{$("#r2")[0].style.display="none";}
	if(mail==""){
		$("#r3")[0].style.display="block";
		
	}else{$("#r3")[0].style.display="none";}
	return false;
	}else{
		$("#r2")[0].style.display="none";
		$("#r3")[0].style.display="none";
		$("#r1")[0].style.display="none";
		return true;}
	
}
</script>
</head>
<body>
   <h3>Email</h3>     <br>  
     <form action=""> 
     <label class ="s1">username:</label> 
    <input id="na" class ="s" type="text" name="username"><label id="r1" style="display:none">不能为空</label><br><br> 
     <label class ="s1">password:</label>   
    <input id="ps" class ="s" type="password" name="pwd"><label id="r2" style="display:none">Required</label><br><br>    
     <label id="mail" class ="s1">Email:</label>   
    <input id="em" class ="s" type="text" name="mail"><label id="r3" style="display:none">Required</label><br> 
    <button id="sbtn" type="button" onclick="sendMail()">Send Email</button>
    </form>  
</body>
</html>