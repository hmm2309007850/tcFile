$(function(){
    var search_btn = $('#search_btn');
    search_btn.on("touchstart",showSearch);
    // var span=$(".sc span");

    $('.search .sc').on('touchstart','span',function(){
        $(this).addClass('active').siblings().removeClass('active');
    });

    $('.bg').on('touchstart',closeSearch);

});

function showSearch(){
    $('.bg').fadeIn(500);
    $('.search').fadeIn(500);
}

function closeSearch(){
    $('.bg').fadeOut(500);
    $('.search').fadeOut(500);
}



   
    // $('.search .submit').click(function(){
        
    //     var spans = $('.search .sc span');
    //     var screen= [];
    //     for(var i = 0;i < spans.length;i++){
    //         if(spans.eq(i).hasClass('active')){
    //             screen.push(spans.eq(i).html())
    //         }
    //     }    
    //     // console.log(screen)
    //     console.log(screen.length)
    //     for(var j = 0;j < screen.length;j++){
    //         var span = '<span>'+screen[j]+'</span>';
    //         // $('article a').append(span);
    //     }
    // })





    // $.ajax({
    //     url:'http://127.0.0.1:3000',
    //     type:'get',
    //     dataType:'jsonp',
    //     data:{id: 1},
    //     success:function(data){
    //         console.log(data)
    //     },
    //     error:function(e){
    //         console.log(e)
    //     }
    // })
    
    // $('#book_download').on('click','li',function(){
    //     $.ajax({
    //         url:'http://127.0.0.1:3000',
    //         type:'post',
    //         dataType:'jsonp',
    //         data:{id: 1},
    //         success:function(data){
    //             console.log(data)
    //         },
    //         error:function(e){
    //             console.log(e)
    //         }
    //     })
    // })