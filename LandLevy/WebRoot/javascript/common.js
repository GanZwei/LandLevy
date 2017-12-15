$(function(){

  
   //鼠标移动到用户显示信息
  $(".i_user").hoverDelay({
    hoverDuring: 0,
    outDuring: 300,
    hoverEvent: function() {
      $(".userbox", $(".i_user")).slideDown("fast");
    },
    outEvent: function() {
      $(".userbox", $(".i_user")).slideUp("fast");
    }
  });
  $(".icon16_common,.icon14_common,.showBlackTips").showTips({offsetY:30,tipsTxt:function(obj){return $(obj).text()}});
  $(".micBtn").showTips({offsetY:26,tipsTxt:function(obj){return $(obj).find("i").text()}});
  $(".minBtnList").showTips({offsetY:26,tipsTxt:function(obj){return $(obj).find("i").text()}});
  
/*  $(".btnChose").click(function(){
	  if($(this).hasClass("selected")){
		  $(this).removeClass("selected");
		  $("a",$(this).parents()).removeClass("btnActive");
		  $("li",".articleListBox").removeClass("selected");
		  }else{
		  $(this).addClass("selected");
		  $("a",$(this).parents()).addClass("btnActive");
		  $("li",".articleListBox").addClass("selected");
			  }
	  });*/
/*  $(".articleListBox > li > i").click(function(){
	  if($(this).parent().hasClass("selected")){
		 $(this).parent().removeClass("selected");
		 var selectLength = $(".selected",$(this).parent().parent()).length;
		 if(selectLength<1){
			 $(".btnCtrlFun",".mgCtrlBar").removeClass("btnActive");
			 }
		  }else{
			  $(this).parent().addClass("selected");
			  $(".btnCtrlFun",".mgCtrlBar").addClass("btnActive");
			  }
	  });*/
});

//消息提示方法
function messageTips(messageType,tipscontent) {
   // messageType为error,或success
  var showtime = 300;
  var x = 9999999; //上限
  var y = 0; //下限
  var rand = parseInt(Math.random() * (x - y + 1) + y);
  var domid = "dom" + rand;
  var messagedom = "<div class='tips tips"+messageType+"' id='" + domid + "'>" + tipscontent + "</div>";
  $("body").append(messagedom);
  var tipswidth = -($("#" + domid).width() + 72) / 2;
  $("#" + domid).css({
    "margin-left": tipswidth
  });
  
  setTimeout(function() {
    $("#" + domid).animate({
      "opacity": 0
    });
    setTimeout(function() {
      $("#" + domid).remove()
    },
    400);
  },
  3000);
}

function getEvent() {
  if (document.all) {
    return window.event; //如果是ie
  }
  func = getEvent.caller;
  while (func != null) {
    var arg0 = func.arguments[0];
    if (arg0) {
      if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
        return arg0;
      }
    }
    func = func.caller;
  }
  return null;
}

//loading效果
function loading(){
  var x = 999999; //上限
  var y = 0; //下限
  var rand = parseInt(Math.random() * (x - y + 1) + y);
  var lodingbox = "<div class='loading' id='load"+rand+"'></div>" 
  $("body").append(lodingbox);
  setTimeout(
  function(){
	  $("#load"+rand).remove();
	  },6000
  );
	}
//移动窗口事件	
function bindResize(el) {
  x = 0;
  y = 0;
  $(el).mousedown(function(e) {
    mytop = $(el).parent().offset().top - $(document).scrollTop();
    myleft = $(el).parent().offset().left - $(document).scrollLeft();
    x = e.clientX;
    y = e.clientY;
    el.setCapture ? (el.setCapture(), el.onmousemove = function(ev) {
      mouseMove(ev || event);
    },
    el.onmouseup = mouseUp) : ($(document).bind("mousemove", mouseMove).bind("mouseup", mouseUp));
    e.preventDefault();
  });
  function mouseMove(e) {
    var newx = myleft + (e.clientX - x);
    var newy = mytop + (e.clientY - y);
    el.parent().css({
      "top": newy,
      "left": newx,
      "margin": 0,
    });
  }
  function mouseUp() {
    el.releaseCapture ? (el.releaseCapture(), el.onmousemove = el.onmouseup = null) : ($(document).unbind("mousemove", mouseMove).unbind("mouseup", mouseUp));
  }
}

//延时通用
(function($) {
  $.fn.hoverDelay = function(options) {
    var defaults = {
      hoverDuring: 200,
      outDuring: 200,
      hoverEvent: function() {
        $.noop();
      },
      outEvent: function() {
        $.noop();
      }
    };
    var sets = $.extend(defaults, options || {});
    var hoverTimer, outTimer;
    return $(this).each(function() {
      $(this).hover(function() {
        clearTimeout(outTimer);
        hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
      },
      function() {
        clearTimeout(hoverTimer);
        outTimer = setTimeout(sets.outEvent, sets.outDuring);
      });
    });
  }
})(jQuery);

//显示提示消息
(function($) {
	$.fn.showTips = function(options){
		var defaults = {
			offsetx:0,
			offsetY:0,
			tipsTxt:function(obj){
				$.noop();
				},
			position:"top",
			}
		var sets = $.extend(defaults, options || {});
		var myheight = sets.offsetY;
		return $(this).each(function(){
			$(this).hover(function(){
				var myscrolltop = parseInt($("body").scrollTop());//滚动条的距离
				//如果是IE则采用下面的方法获取滚动的距离。
				if(navigator.appName.indexOf("Microsoft Internet Explorer")!=-1&&document.all){
					myscrolltop = parseInt($("html").scrollTop());
					}
				var mytop = parseInt($(this).offset().top) - myheight - myscrolltop;//该对象的顶部距离，减去顶部便宜的距离，减去滚动条的距离
				var myleft = parseInt($(this).offset().left);
				var thiswidth = $(this).width();
				var tipsbox = '<span class="wrp_tips" style="top:'+mytop+'px;left:'+myleft+'px">'+sets.tipsTxt($(this))+'</span>';
				$("body").append(tipsbox);
				var mywdith = (thiswidth/2) -6 - parseInt($(".wrp_tips").width())/2;
				$(".wrp_tips").css({"margin-left":mywdith});
				},function(){
					$(".wrp_tips").remove();
					});
			});
		}
	})(jQuery);

//此插件暂未修改
$(function() {
    $.fn.manhua_bubbletips = function(options) {
        var defaults = {            
            position : "t",         //箭头指向上(t)、箭头指向下(b)、箭头指向左(l)、箭头指向右(r)
            value : 15,             //小箭头偏离左边和上边的位置
            content : ""            //内容  
        };
        var options = $.extend(defaults,options);       
        var offset = $(this).offset();
        var bid = parseInt(Math.random()*100000);       
        $("body").prepend('<div class="docBubble" id="btip'+bid+'"><i class="triangle-'+options.position+'"></i> <i class="close" title="关闭" id="btipc'+bid+'">关闭</i><div class="tl"><div class="inner"><div class="cont">'+options.content+'</div></div></div><div class="tr"></div><div class="bl"></div></div>');
        var $btip = $("#btip"+bid);
        var $btipClose = $("#btipc"+bid);
        var h = $(this).height();
        var w = $(this).width();    
        switch(options.position){
            case "t" ://当它是上面的时候
                $(".triangle-t").css('left',options.value);
                $btip.css({ "left":offset.left+w/2-options.value  ,  "top":offset.top+h+14  });
                break;
            case "b" ://当它是下面的时候
                $(".triangle-b").css('left',options.value);
                $btip.css({ "left":offset.left+w/2-options.value  ,  "top":offset.top-h-7-$btip.height()  });
                break;
            case "l" ://当它是左边的时候        
                $(".triangle-l").css('top',options.value);
                $btip.css({ "left":offset.left+w+10  ,  "top":offset.top+h/2-7-options.value });
                break;
            case "r" ://当它是右边的时候            
                $(".triangle-r").css('top',options.value);
                $btip.css({ "left":offset.left-w+25-$btip.width()  ,  "top":offset.top+h/2-7-options.value });
                break;
        }
        $btipClose.live("click",function(e){
          $btip.hide(); 
        });     
    }
});

//普通输入带提示文本框
(function($){
	$.fn.insertBox = function(options){
		var defaults = {
			type:"",//输入框的类型，text,tel等
			width:"",
			height:"",
			borderColor:"",
			padding:"",
			tipsText:"",
			must:true,//是否必填
			icon:true,//是否有图标
			iconClass:"",//图标样式
			}
		}
	});


//成功与失败，加载提示
