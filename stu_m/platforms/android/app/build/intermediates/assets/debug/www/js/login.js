$(function(){
    $('.submit').click(function(){
        var name = $('#uname').val();
        var pwd = $('#upwd').val();
        if(name.trim()=='' || pwd.trim()==''){
            // alert('用户名或密码不能为空！')
            layer.msg('用户名或密码不能为空', function(){
                //关闭后的操作
            });
            return;
        }
        $.ajax({
            url:'http://192.168.1.126:8081/login/login/',
            type:'post',
            dataType:'json',
            data:{name:name,pwd:pwd},
            success:function(data){
                console.log(data)
                if(data.data == 'success'){
                    // alert('登录成功')
                    layer.msg('登录成功',{icon: 6},function(){
                        location.href = '../index.html';
                    });    
                    var session = $.session.set('username',name);
                    var session = $.session.set('password',pwd);
                    console.log(session)      
                }else {
                    // alert('用户名或密码不正确')
                    // layer.alert('账号或密码错误，请重新输入', {icon: 5}
                    
                    // );
                    layer.alert('用户名或密码错误，请重新输入', {
                        title:'登录失败',
                        skin: 'layui-layer-lan'
                        ,closeBtn: 0
                        // ,anim: 6 //动画类型
                      });
                    return;
                }
            },
            error:function(msg){
                console.log(msg);
            }
        })
    });
    
});

function keyLogin(){
    if(event.keyCode == 13){
        $('form .submit').click();
    }
}