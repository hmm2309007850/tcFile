   // 相机
   var app = {
    // Application Constructor
    initialize: function () {
    document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function () {
    this.receivedEvent('deviceready');
    },

    // 获取id的函数
    $: function (id) {
    return document.getElementById(id);
    },

    // Update DOM on a Received Event
    receivedEvent: function () {
    var cameraButton = this.$('openCamera');
    var _this = this;              // this表示当前对象，_this表示cameraButton对象
    cameraButton.onclick = function () {
        // 打开相机
        navigator.camera.getPicture(onSuccess, onFail, {
        quality: 50,                                            // 相片质量是50
        sourceType: Camera.PictureSourceType.CAMERA,            // 设置从摄像头拍照获取
        destinationType: Camera.DestinationType.FILE_URI,          // 以文件路径返回
        allowEdit: true                                           //可编辑
        });

        function onSuccess(imageURI) {
            var image = _this.$('img');
            image.src = imageURI;

            $('.hh').click(function(){
                // var ws = new WebSocket('ws://192.168.1.10:8181'); 
                // ws.onopen = function(){
                //     ws.send(imageURI);
                // };
                // ws.onmessage = function(evt){
                //    console.log(evt.data);
                // };

                localStorage.setItem('picture',imageURI);
    
            })
           
            // var upload = _this.$('upload');
            // upload.onclick = function(){
            //         $('.a').html('<img src="'+imageURI+'">');
            //     }

        }

        function onFail(message) {
        alert('Failed because: ' + message);
        }
    }
}
};

app.initialize();