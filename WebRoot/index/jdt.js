/*焦点图js示*/
/*鼠标移过，左右按钮显示*/
$(".jst").hover(function(){
	$(this).find(".prev,.next").fadeTo("show",0.1);
},function(){
	$(this).find(".prev,.next").hide();
})
/*鼠标移过某个按钮 高亮显示*/
$(".prev,.next").hover(function(){
	$(this).fadeTo("show",0.7);
},function(){
	$(this).fadeTo("show",0.1);
})
$(".jst").slide({ titCell:".num ul" , mainCell:".jst_cnt" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });

/*  
titCell：导航元素对象（鼠标的触发元素对象）
mainCell：切换元素的包裹层对象
effect：动画效果（默认："fade"）
fade：渐显；|| left：左滚动；|| fold：淡入淡出
autoPlay：自动运行（默认：false）
delayTime：毫秒（默认：500）；切换效果持续时间（一次切换效果执行所用的时间长度）。pt>
autoPage：默认为false；支持自定义分页html，$为数字替换位置。例如autoPage:"<li><a>$</a></li>"，如果不想输出数字：autoPage:"<li><a></a></li>"。
prevCell：前一个/页按钮对象。默认（".prev"）
nextCell：后一个/页按钮对象。默认（".next"）
*/