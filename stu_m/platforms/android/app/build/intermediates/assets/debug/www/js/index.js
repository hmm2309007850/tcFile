
// 直播 / 作业

var test = window.test || {};
test.openApp = {
    _title: "",
    _scheme: "",
    _activity: "",
    init: function() {
        test.openApp.bindEvent();
    },
    bindEvent: function() {
        $('.openApp').click(function() {
            var $this = $(this);
            test.openApp._title = $this.data('title');
            test.openApp._scheme = $this.data('scheme');
            test.openApp._activity = $this.data('activity');
            test.openApp.open();
        })
    },
    open: function() {
            test.openApp._scheme
                console.log(test.openApp._scheme + ' is available :)');
                // 打开第三方app
                var sApp = startApp.set({
                    "application": test.openApp._scheme
                });
                sApp.start(function(compete) {
                    console.log(compete);
                    var ws = new WebSocket('ws://192.168.1.120:8083/app'); 
                    ws.onopen = function(){
                        ws.send('已打开');
                    };
                    ws.onmessage = function(evt){
                        var hh = startApp.set({
                            "application": 'org.edx.mobile'
                        });
                        hh.start(function(xx){
                            console.log(xx);
                        })
                    }         
                    // var ws = new WebSocket('ws://192.168.1.120:8083/app'); 
                    // // var ws = new WebSocket('ws://192.168.1.10:8181'); 
                    // ws.onopen = function(){
                    //     ws.send('已打开');
                    // };
                    // ws.onmessage = function(evt){
                    //     if(evt.data != '1'){
                    //         var index = top.layer.open({
                    //         type: 2,
                    //         area: ['90%', '86%'],
                    //         title: '答题页',
                    //         fixed: false, //不固定
                    //         maxmin: true,
                    //         content: evt.data,
                    //         cancel:function(){
                    //         if(!confirm('您确定要关闭此页面吗')){
                    //             return false;
                    //         }
                    //         }
                    //     });
                    //     }
                    //     if(evt.data == '1'){
                    //         layer.close(layer.index)
                    //     }    
                    // };
                }, function(error) {
                    console.error(error);
                }, function(result, requestCode, resultCode) {
                });
    }
}

