var ws = new WebSocket('ws://192.168.1.10:8181');   //192.168.1.137:8083/app
ws.onopen = function(){
    // alert('opened');
    ws.send('我是client');
};
ws.onmessage = function(evt){
 var hh = document.getElementById('hh');

};
ws.onclose = function(){
    
};
ws.onerror = function(err){
    
}

