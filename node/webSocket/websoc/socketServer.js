var WebSocketServer = require('ws').Server,
wss = new WebSocketServer({port:8181});
wss.on('connection',function(ws){
    console.log(ws+'上线');
    ws.on('message',function(message){
        console.log('received:',message);
    });
    ws.send('http://192.168.1.10/studentApp/www');
    ws.on('close',function(){
        // global.gc();  //调用内存回收
        console.log('leave');
    })
})