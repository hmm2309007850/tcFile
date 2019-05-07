var http = require('http');
http.createServer(function(request,response){
    response.writeHead(200,{'Content-Type': 'text/html; charset=utf-8'});
    if(request.url !== '/favicon.ico') {
        fun1(response);
        response.end();
    }
}).listen(8000);
console.log('运行http://127.0.0.1:8000');

function fun1(res){
    console.log('我是fun1')
    res.write('my is fun1')
}