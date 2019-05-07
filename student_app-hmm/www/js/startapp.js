var test = window.test || {};
test.openApp = {
    _title: "",
    _scheme: "",
    _activity: "",
    init: function() {
        test.openApp.bindEvent();
    },
    bindEvent: function() {
        setTimeout('test.openApp.open()',500)
    },
    open: function() {
            var sApp = startApp.set({
                "application": 'com.hengyi.fastvideoplayer'
            });
            sApp.start(function(compete) {
                console.log(compete);
            }, function(error) {
                console.error(error);
            }, function(result, requestCode, resultCode) {
        });
    }
}

test.openApp.init();