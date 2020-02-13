// 提交类型：目标DOM
// user:用户名
// pass:密码
// pass_c:二次验证密码
// email:邮箱验证
// mobile：手机验证
// code:发送验证码
// va_code:验证验证码
// sub_btn：提交按钮
// url：跳转的地址
// 传递参数为空时，不验证

function valiData(mes,url,invite_code, user,mobile,code,va_code,pass,pass_c,email,sub_btn){
    // 验证布尔值
    var invite_code_Boolean = false;    // 邀请码
    var user_Boolean = false;           //用户名
    var password_Boolean = false;       //密码
    var varconfirm_Boolean = false;     //二次密码
    var emaile_Boolean = false;         //邮箱
    var Mobile_Boolean = false;         //手机
    var Code_Boolean = false;           //验证码
    // 用户名验证
    if(!user){
        user_Boolean = true;
    }



    if(user){
        console.log(1,$(user));
        // 验证规则：/^[A-Za-z0-9_-]{4,8}$/
        if ((/^[A-Za-z0-9_-]{8,16}$/).test($(user).val())){
            user_Boolean = true;
        }
        $(user).blur(function(){
            if ((/^[A-Za-z0-9_-]{8,16}$/).test($(user).val())){
                $(user).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                user_Boolean = true;
            }else {
                $(user).css("border-bottom","1px solid red").next().text("×").css("color","red");
                user_Boolean = false;
            }
        });
    }
    // 手机验证
    if(!mobile){
        Mobile_Boolean = true;
    }
    if(mobile){
        console.log(2,$(mobile));
        if ((/^1[345789]\d{9}$/).test($(mobile).val())){
            Mobile_Boolean = true;
        }
        $(mobile).blur(function(){
            if ((/^1[345789]\d{9}$/).test($(mobile).val())){
                $(mobile).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                Mobile_Boolean = true;
            }else {
                $(mobile).css("border-bottom","1px solid red").next().text("×").css("color","red");
                Mobile_Boolean = false;
            }
        });
    }
    // 发送验证码
    console.log(3,code);
    $(code).click(function(){
        if(Mobile_Boolean == true){
            console.log(Mobile_Boolean);
            var url = host + send_sms_uri + "?type=1&mobile="+$(mobile).val();
            getForAuth(url, null, function (result) {
                if (result.code == 200) {
                    $(this).attr("disabled","disabled");
                    var time = 60;						            //重新发送时间时间
                    function timeCountDown(){
                        if(time==0){				                //倒计时完了
                            clearInterval(timer);	                //清除定时器
                            $(code).removeAttr("disabled").val("发送");		 //显示倒计时
                            return true;
                        }
                        $(code).val(time+"S后再次发送");		     //显示倒计时
                        time--;										//开始倒计时
                        console.log(1);
                        return false;
                    }
                    timeCountDown();
                    var timer = setInterval(timeCountDown,1000);    //开始循环timeCountDown函数
                } else {
                    $(".comm_mes").show().fadeOut(2000).find("p").text(result.message);
                }
            });



        }else{
            $(".comm_mes").show().fadeOut(2000).find("p").text("手机号不正确");
        }
        event.preventDefault();
    });
    // 验证验证码
    if(!va_code){
        Code_Boolean = true;
    }
    if(va_code){
        console.log(4,$(va_code));
        $(va_code).blur(function(){
            if ((/^[0-9]{4,8}$/).test($(va_code).val())){
                $(va_code).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                Code_Boolean = true;
            }else {
                $(va_code).css("border-bottom","1px solid red").next().text("×").css("color","red");
                Code_Boolean = false;
            }
        });
    }
    // 密码验证
    if(!pass){
        password_Boolean = true;
    }
    if(pass){
        console.log(5,$(pass));
        $(pass).blur(function(){
            if ((/^[A-Za-z0-9_-]{6,16}$/).test($(pass).val())){
                $(pass).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                password_Boolean = true;
            }else {
                $(pass).css("border-bottom","1px solid red").next().text("×").css("color","red");
                password_Boolean = false;
            }
            if(!$(pass_c).is(':focus')){
                if (($(pass).val())==($(pass_c).val())){
                    $(pass_c).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                    varconfirm_Boolean = true;
                }else {
                    $(pass_c).css("border-bottom","1px solid red").next().text("×").css("color","red");
                    varconfirm_Boolean = false;
                }
            }
        });
    }
    // 二次密码验证
    if(!pass_c){
        varconfirm_Boolean = true;
    }
    if(pass,pass_c){
        console.log(6,$(pass_c));
        $(pass_c).blur(function(){
            if (($(pass).val())==($(pass_c).val())){
                $(pass_c).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                varconfirm_Boolean = true;
            }else {
                $(pass_c).css("border-bottom","1px solid red").next().text("×").css("color","red");
                varconfirm_Boolean = false;
            }
        });
    }
    // 邮箱验证
    if(!email){
        emaile_Boolean = true;
    }
    if(email){
        console.log(7,email)
        $(email).blur(function(){
            if ((/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/).test($(email).val())){
                $(email).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                emaile_Boolean = true;
            }else {
                $(email).css("border-bottom","1px solid red").next().text("×").css("color","red");
                emaile_Boolean = false;
            }
        });
    }
    
    // 全部完成通过
    console.log(10,sub_btn);
    $(sub_btn).click(function(){
        if(user_Boolean && password_Boolean && varconfirm_Boolean && emaile_Boolean && Code_Boolean && Mobile_Boolean == true){
            var data = {
                "inviteCode": $(invite_code).val(),
                "account":$(user).val(),
                "mobile":$(mobile).val(),
                "password":$(pass).val(),
                "confirmPassword": $(pass_c).val(),
                "smsCode":$(va_code).val()
            }
            postForAuth(host + register_uri, data, function(result){
                if (result.code == 200) {
                    if(mes){
                        $(".comm_mes").show().fadeOut(2000).find("p").text(mes);
                    }
                    if(url){
                        setTimeout(() => {
                            window.location.href = url;
                        }, 2000);
                    }
                } else {
                    $(".comm_mes").show().fadeOut(2000).find("p").text(result.message);
                }
            });

          }else {
            $(".comm_mes").show().fadeOut(2000).find("p").text("请完善信息");
        }
        event.preventDefault();
    });
    console.log(user_Boolean,password_Boolean,varconfirm_Boolean,emaile_Boolean,Mobile_Boolean);
}



// 密码操作
// 提交类型：目标DOM
// pass:密码
// pass_c:二次验证密码
// safe:安全码
// sub_btn:按钮
// url:地址
// mes:信息
function valiPass(mes,url, old_pass, pass,pass_c,safe,sub_btn){
    var old_pass_Boolean = false;
    var password_Boolean = false;       //密码
    var varconfirm_Boolean = false;     //二次密码
    var safe_Boolean = false;
    // 密码验证
    if(!pass){
        password_Boolean = true;
    }
    if(old_pass){
        console.log(0,$(old_pass));
        // 验证规则：/^[A-Za-z0-9_-]{4,8}$/
        $(old_pass).blur(function(){
            if ((/^[A-Za-z0-9_-]{6,16}$/).test($(old_pass).val())){
                $(old_pass).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                old_pass_Boolean = true;
            }else {
                $(old_pass).css("border-bottom","1px solid red").next().text("×").css("color","red");
                old_pass_Boolean = false;
            }
        });
    }
    if(pass){
        console.log(1,$(pass));
        $(pass).blur(function(){
            if ((/^[A-Za-z0-9_-]{6,16}$/).test($(pass).val())){
                $(pass).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                password_Boolean = true;
            }else {
                $(pass).css("border-bottom","1px solid red").next().text("×").css("color","red");
                password_Boolean = false;
            }
            if(!$(pass_c).is(':focus')){
                if (($(pass).val())==($(pass_c).val())){
                    $(pass_c).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                    varconfirm_Boolean = true;
                }else {
                    $(pass_c).css("border-bottom","1px solid red").next().text("×").css("color","red");
                    varconfirm_Boolean = false;
                }
            }
        });
    }
    // 二次密码验证
    if(!pass_c){
        varconfirm_Boolean = true;
    }
    if(pass,pass_c){
        console.log(2,$(pass_c));
        $(pass_c).blur(function(){
            if (($(pass).val())==($(pass_c).val())){
                $(pass_c).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                varconfirm_Boolean = true;
            }else {
                $(pass_c).css("border-bottom","1px solid red").next().text("×").css("color","red");
                varconfirm_Boolean = false;
            }
        });
    }
    // 安全码操作
    if(!safe){
       safe_Boolean = true;
    }
    if(safe){
        console.log(3,$(safe));
        // 验证规则：/^[A-Za-z0-9_-]{4,8}$/
        $(safe).blur(function(){
            if ((/^[A-Za-z0-9_-]{4,8}$/).test($(safe).val())){
                $(safe).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                safe_Boolean = true;
            }else {
                $(safe).css("border-bottom","1px solid red").next().text("×").css("color","red");
                safe_Boolean = false;
            }
        });
    }
    // 提交
    console.log(4,sub_btn);
    $(sub_btn).click(function(){
        if(old_pass_Boolean && password_Boolean && varconfirm_Boolean && safe_Boolean){

            var data = {
                "password": $(old_pass).val(),
                "newPassword": $(pass).val(),
                "confirmPassword":$(pass_c).val(),
                "code":$(safe).val()
            }
            console.log(data);

            var uri = modify_login_pwd_uri;
            if ($(sub_btn).attr("id") == "modify_pay_pass_btn") {
                uri = modify_pay_pwd_uri;
            }
            postForAuth(host + uri, data, function(result){
                if (result.code == 200) {
                    if(mes){
                        $(".comm_mes").show().fadeOut(2000).find("p").text(mes);
                    }
                    if(url){
                        setTimeout(() => {
                            window.location.href = url;
                        }, 2000);
                    }
                } else {
                    $(".comm_mes").show().fadeOut(2000).find("p").text(result.message);
                }
            });


        }else{
            $(".comm_mes").show().fadeOut(2000).find("p").text("请完善信息");
        }
        event.preventDefault();
    });
}



// 实名认证
// /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  //身份证规则
// /^[\u4E00-\u9FA5]{1,6}$/                     //姓名规则
// mes:显示信息
// url：地址
// name:名字
// sf_number:身份证号
// qq:qq号码
// email:邮箱验证
// mobile:手机验证
function realName(mes,url,name,sf_number,qq,email,mobile,sub_btn){
    // 布尔值
    var name_Boolean = false;           
    var sf_Boolean = false;
    var qq_Boolean = false;
    var Mobile_Boolean = false;
    var qq_Boolean = false;
    var emaile_Boolean = false;         //邮箱
    var Mobile_Boolean = false;         //手机
    // 姓名验证
    if(name){
        console.log(1,name);
        $(name).blur(function(){
            if((/^[\u4E00-\u9FA5]{1,6}$/).test($(name).val())){
                $(name).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                name_Boolean = true;
            }else{
                $(name).css("border-bottom","1px solid red").next().text("X").css("color","red");
                name_Boolean = false;
            }
        });
    }
    // 身份证号验证
    if(sf_number){
        console.log(2,sf_number);
        $(sf_number).blur(function(){
            if((/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/).test($(sf_number).val())){
                $(sf_number).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                sf_Boolean = true;
            }else{
                $(sf_number).css("border-bottom","1px solid red").next().text("X").css("color","red");
                sf_Boolean = false;
            }
        });
    }
    // QQ号验证
    if(!qq){
        qq_Boolean = ture;
    }
    if(qq){
        console.log(3,qq);
        $(qq).blur(function(){
            if((/^[12345789]\d{5,12}$/).test($(qq).val())){
                $(qq).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                qq_Boolean = true;
            }else{
                $(qq).css("border-bottom","1px solid red").next().text("X").css("color","red");
                qq_Boolean = false;
            }
        });
    }
    // 邮箱验证
    if(!email){
        emaile_Boolean = true;
    }
    if(email){
        console.log(4,email);
        $(email).blur(function(){
            if ((/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/).test($(email).val())){
                $(email).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                emaile_Boolean = true;
            }else {
                $(email).css("border-bottom","1px solid red").next().text("×").css("color","red");
                emaile_Boolean = false;
            }
        });
    }
    // 手机验证
    if(!mobile){
        Mobile_Boolean = true;
    }
    if(mobile){
        console.log(5,mobile);
        if ((/^1[345789]\d{9}$/).test($(mobile).val())){
            Mobile_Boolean = true;
        }
        $(mobile).blur(function(){
            if ((/^1[345789]\d{9}$/).test($(mobile).val())){
                $(mobile).css("border-bottom","1px solid green").next().text("✔").css("color","green");
                Mobile_Boolean = true;
            }else {
                $(mobile).css("border-bottom","1px solid red").next().text("×").css("color","red");
                Mobile_Boolean = false;
            }
        });
    }
    // 提交按钮
    console.log(6,$(sub_btn));
    $(sub_btn).click(function(){
        if(name_Boolean && sf_Boolean && qq_Boolean && emaile_Boolean && Mobile_Boolean == true){
            var params = {
                "realName":$(name).val(),
                "idNumber":$(sf_number).val(),
                "qq":$(qq).val(),
                "email": $(email).val(),
                "telephone": $(mobile).val()
            }
            postForAuth(host + realInfo_apply_uri, params, function(result){
                handleResult(result);
                if (result.code == 200) {
                    if(mes){
                        $(".comm_mes").show().fadeOut(2000).find("p").text(mes);
                    }
                    if(url){
                        setTimeout(() => {
                            window.location.href = url;
                        }, 1000);
                    }
                } else {
                    $(".comm_mes").show().fadeOut(2000).find("p").text(result.message);
                }
            });



          }else {
            $(".comm_mes").show().fadeOut(2000).find("p").text("请完善信息");
        }
        event.preventDefault();
    });
    console.log(name_Boolean,sf_Boolean,qq_Boolean,emaile_Boolean,Mobile_Boolean);
}
// 插入提示
$(function(){
    if($(".comm_mes").length>0){

    }else{
        console.log(2222);
        $("body").append('<div class="comm_mes"><p>這個是jq消息</p></div>');
    }
});
