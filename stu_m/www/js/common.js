var ws = new WebSocket("ws://192.168.1.126:8081/ws/app/");
// var ws = new WebSocket('ws://192.168.1.10:8181'); 
console.log(ws)
ws.onopen = function(){
    // ws.send('已打开');
};
ws.onmessage = function(evt){
    console.log(evt)
    var data = JSON.parse(JSON.parse(evt.data)['message']);
    console.log(data)
    if(data != '1'){
        // console.log(evt)
        // var data = JSON.parse(JSON.parse(evt.data)['message']);
        // console.log(data)
        var index = top.layer.open({
        type: 2,
        area: ['90%', '86%'],
        title: '答题页',
        fixed: false, //不固定
        maxmin: true,
        content: evt.data,
        cancel:function(){
        if(!confirm('您确定要关闭此页面吗')){
            return false;
        }
        }
    });
    }
    if(data == '1'){
        console.log('1111')
        layer.close(layer.index)
    }    
};