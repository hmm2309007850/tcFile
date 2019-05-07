$(function(){
    var search_button = $('#seach_button');
    var close_button = $('#close_search');
    search_button.on("touchstart",showSearch);
    close_button.on("touchstart",closeSearch);
    var span=$(".sc span");

    $('.bg').click(function(){
        $('.bg').fadeOut(800);
        closeSearch()
    });

    $('.search .sc').on('click','span',function(){
        $(this).addClass('active').siblings().removeClass('active');
    });

    $('.search .submit').click(function(){
        
        var spans = $('.search .sc span');
        var screen= [];
        for(var i = 0;i < spans.length;i++){
            if(spans.eq(i).hasClass('active')){
                screen.push(spans.eq(i).html())
            }
        }    
        // console.log(screen)
        console.log(screen.length)
        for(var j = 0;j < screen.length;j++){
            var span = '<span>'+screen[j]+'</span>';
            // $('article a').append(span);
        }
    })
});

function showSearch(){
    var mobileHeight=window.screen.availHeight ;
    console.log(mobileHeight);
    $('.search').css('height',mobileHeight+'px');
    $('.search').show();
    $('.bg').show();
}
function closeSearch(){
    $('.search').hide();
    $('.bg').hide();
}









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