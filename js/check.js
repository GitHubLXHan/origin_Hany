/**
 * Created by Hany on 2017/12/12.
 */
//注册成功成功后返回给register.jsp页面
function checkR(msg){
    if(msg=="注册成功"){
        alert(msg);
        window.location.href="login.jsp";
    }else if(msg!=''&&msg!="注册成功"){
        alert(msg);
        window.location.href="register.jsp";
    }
}

//登录成功后返回给login.jsp页面
function checkL(msg){
    if(msg=="登录成功"){
        alert(msg);
        window.location.href="show";
    }else if(msg!=''&&msg!="登陆成功"){
        alert(msg);
        window.location.href="login.jsp";
    }
}

//判断是否要注销账号
function checkLogout(){
	var is = false;
	if(comfirm('确定要注销吗')){
		is = true;
	};
	return is;
}