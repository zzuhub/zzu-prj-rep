/**
 * 验证用户登陆
 */
$(function(){
	
	$("#userName,#password").blur(function(){
		if($("#errDiv").text()){   //有错误消息
			$("#errDiv").remove()  ;
		}
	});
	
    $("#userName").bind("blur",function () {    //id字段丢失焦点时验证
          validateName() ;
    });

    $("#password").bind("blur",function () {    //password字段丢失焦点时验证
          validatePassword()  ;
    });



    $("#subBtn").click(function(){   //提交时同时验证用户ID和密码
        return validateName() && validatePassword() && validateIdcode($("#ehong-code-input").val(),$.idcode.getCode())  ;
    });

    $.idcode.setCode();

    $("#ehong-code-input").bind("blur",function(){        //将验证码输入框绑定验证
        validateIdcode($(this).val(),$.idcode.getCode())  ;
    });

}) ;


function validateName(){                      //ID非空验证
     return validateEmpty("userName")  ;
}

function validatePassword(){                //密码非空验证
    return validateEmpty("password")  ;
}

function validateEmpty(eleId){    //非空验证
     var flag=true ;
     if($("#"+eleId).val()==""){   //如果内容为空
         $("#"+eleId+"Div").attr("class","form-group  has-error") ;
         $("#"+eleId+"Span").empty()
             .append("<span class='text-danger'>该字段内容不能为空!</span>") ;
         flag=false ;
     }else{                        //内容非空
         $("#"+eleId+"Div").attr("class","form-group has-success") ;
         $("#"+eleId+"Span").empty()
             .append("<span class='text-success'>该字段内容输入正确</span>") ;
         flag=true ;
     }
     return flag ;
}



/**
 *Author ZZU·王成键
 * @param inputIdcode 输入的验证码
 * @param idcode      动态生成的验证码
 */
function validateIdcode(inputIdcode,idcode){
        var flag=true ;
        if(inputIdcode==""){    //输入的验证码为空
            $("#idcodeDiv").attr("class","form-group  has-error") ;
            $("#idcodeSpan").empty()
                .append("<span class='text-danger'>验证码内容不能为空!</span>") ;
            flag=false ;
        }else{
             if(inputIdcode.toUpperCase()==idcode.toUpperCase()){    //输入的验证码和动态生成的验证码一致
                 $("#idcodeDiv").attr("class","form-group has-success") ;
                 $("#idcodeSpan").empty().append("<span class='text-success'>验证码内容输入正确</span>") ;
                 flag=true ;
             }else{                                                   // //输入的验证码和动态生成的验证码不一致
                 $("#idcodeDiv").attr("class","form-group  has-error") ;
                 $("#idcodeSpan").empty()
                     .append("<span class='text-danger'>验证码内容输入错误!</span>") ;
                 flag=false ;
             }
        }
        return flag  ;
}








