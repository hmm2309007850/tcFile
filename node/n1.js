// var http = require('http');
// http.createServer(function(request,response){
//     // request 请求   response 向客户端输出
//     // 协议头
//     response.writeHead(200,{'Content-Type':'text/html; charset=utf-8'});
//     if(request.url!=='/favicon.ico'){
//         // 清除第2次访问
//         console.log('访问');
//         response.write('hi,你好吗？'); //输出http没有结束
//         response.end('结束');  //协议尾  结束http
//     }
// }).listen(8000);
// console.log('运行:http://127.0.0.1:8000');