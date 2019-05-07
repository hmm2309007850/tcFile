$(function(){
    var docEl = document.documentElement,
    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
    var clientWidth = docEl.clientWidth;
    docEl.style.fontSize = 10 * (clientWidth / 375) + 'px';
    window.addEventListener(resizeEvt, function() { clientWidth = docEl.clientWidth;docEl.style.fontSize = 10 * (clientWidth / 375) + 'px'; }, false);


    if($.session.get('username') == null || $.session.get('password') == null){
        alert('请先登录!');
        location.href = '../login/login.html';
    }
});