/******************************************************************
 * 例： new ProCityValue('存放省份城市的位置控件名', {proId:"存放省份的控件Id", cityId:"存放城市的控件Id", proBtn:function(value){}, cityBtn：function(value){}})
 * 
 * param中有
 * proId： 省份控件ID
 * cityId： 城市控件ID
 * proBtn：省份选中后调用方法
 * cityBtn： 城市选中后调用方法
 * proValue： 省份初始值
 * cityValue： 城市初始值
 *
 * @author 王丹
 * @version 2015-7-2
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 
 * history:
 *
 */
var proBtn, cityBtn;
function ProCityValue(eleId, param){
	var proId = param.proId;
	var cityId = param.cityId;
	proBtn = (param.proBtn != null && typeof(param.proBtn) == "function")? param.proBtn : null;
	cityBtn = (param.cityBtn != null && typeof(param.cityBtn) == "function")? param.cityBtn : null;
	var proDefault = param.proValue == null ? "-- 请选择省份 --" : param.proValue;
	var cityDefault = param.cityValue == null ? "-- 请选择城市 --" : param.cityValue;
	
	var html = new Array();
	html.push('<div class="selectContainer">');
	html.push('<span id="C_PRO_SPAN" class="selectOption gray">');
	html.push(proDefault);
	html.push('</span>');
	html.push('<ul id="C_PRO_LI" class="selectMenu">');
	html.push('</ul>');
	html.push('<span class="shows"></span>');
	html.push('</div>');
	html.push('<div class="selectContainer smlw">');
	html.push('<span id="C_CITY_SPAN" class="selectOption gray">');
	html.push(cityDefault);
	html.push('</span>');
	html.push('<ul id="C_CITY_LI" class="selectMenu">');
	html.push('</ul>');
	html.push('<span class="shows"></span>');
	html.push('</div>');
	$("#"+eleId).append($(html.join('')));
	
	html = new Array();
	var length = where.length;
	for(i = 1 ; i < length ; i++) { 
		html.push('<li onclick="setCityValue(');
		html.push(i);
		html.push(',\'');
		html.push(proId);
		html.push('\',\'');
		html.push(cityId);
		html.push('\')" >');
		html.push(where[i].loca);
		html.push('</li>');
	}
	$("#C_PRO_LI").html(html.join(''));
	
    jQuery.fn.select = function(options){ 
        return this.each(function(){ 
            var $this = $(this); 
            var $shows = $this.find(".shows"); 
            var $selectOption = $this.find(".selectOption"); 
            var $el = $this.find("ul > li"); 
                                       
            $this.click(function(e){ 
                $(this).toggleClass("zIndex"); 
                $(this).children("ul").toggleClass("dis"); 
                e.stopPropagation(); 
            }); 
               
            $el.bind("click",function(){ 
                var $this_ = $(this); 
                    
                $this.find("span").removeClass("gray"); 
                $this_.parent().parent().find(".selectOption").text($this_.text()); 
            }); 
               
            $("body").bind("click",function(){ 
                $this.removeClass("zIndex"); 
                $this.find("ul").removeClass("dis");     
            }) 
        }); 
    }
	$(".selectContainer").select();
}


/******************************************************************
 * 选择省份，给城市赋值
 */
function setCityValue(k, proId, cityId){
	var value = where[k].loca;
	$("#"+proId).val(value);
	$("#C_PRO_SPAN").html(value);
	$("#C_CITY_SPAN").html("-- 请选择城市 --");
	$("#C_CITY_LI").html("");
	var html = new Array();
	var loca3 = (where[k].locacity).split("|");
	var length = loca3.length;
	for(j = 1 ; j < length ; j++){
		html.push('<li onclick="setCity(\'');
		html.push(loca3[j]);
		html.push('\', \'');
		html.push(cityId);
		html.push('\')" >');
		html.push(loca3[j]);
		html.push('</li>');
	}
	$("#C_CITY_LI").html(html.join(''));
	if(proBtn != null && typeof(proBtn) == "function"){
		proBtn(value);
	}
}

/******************************************************************
 * 选择城市
 */
function setCity(value, cityId){
	$("#"+cityId).val(value);
	$("#C_CITY_SPAN").html(value);
	if(cityBtn != null && typeof(cityBtn) == "function"){
		cityBtn(value);
	}
}

/******************************************************************
 * 得到省市信息
 */
var where = new Array(35);
function comefrom(loca,locacity) { this.loca = loca; this.locacity = locacity; }
where[1] = new comefrom("北京","|东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
where[2] = new comefrom("上海","|黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
where[3] = new comefrom("天津","|和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
where[4] = new comefrom("重庆","|万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
where[5] = new comefrom("河北","|石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
where[6] = new comefrom("山西","|太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
where[7] = new comefrom("内蒙古","|呼和浩特|包头|乌海|赤峰|呼伦贝尔盟|阿拉善盟|哲里木盟|兴安盟|乌兰察布盟|锡林郭勒盟|巴彦淖尔盟|伊克昭盟");
where[8] = new comefrom("辽宁","|沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
where[9] = new comefrom("吉林","|长春|吉林|四平|辽源|通化|白山|松原|白城|延边");
where[10] = new comefrom("黑龙江","|哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河|大兴安岭");
where[11] = new comefrom("江苏","|南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
where[12] = new comefrom("浙江","|杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
where[13] = new comefrom("安徽","|合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
where[14] = new comefrom("福建","|福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
where[15] = new comefrom("江西","|南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
where[16] = new comefrom("山东","|济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
where[17] = new comefrom("河南","|郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
where[18] = new comefrom("湖北","|武汉|宜昌|荆州|襄樊|黄石|荆门|黄冈|十堰|恩施|潜江|天门|仙桃|随州|咸宁|孝感|鄂州");
where[19] = new comefrom("湖南","|长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|湘西|张家界");
where[20] = new comefrom("广东","|广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
where[21] = new comefrom("广西","|南宁|柳州|桂林|梧州|北海|防城港|钦州|贵港|玉林|南宁地区|柳州地区|贺州|百色|河池");
where[22] = new comefrom("海南","|海口|三亚");
where[23] = new comefrom("四川","|成都|绵阳|德阳|自贡|攀枝花|广元|内江|乐山|南充|宜宾|广安|达川|雅安|眉山|甘孜|凉山|泸州");
where[24] = new comefrom("贵州","|贵阳|六盘水|遵义|安顺|铜仁|黔西南|毕节|黔东南|黔南");
where[25] = new comefrom("云南","|昆明|大理|曲靖|玉溪|昭通|楚雄|红河|文山|思茅|西双版纳|保山|德宏|丽江|怒江|迪庆|临沧");
where[26] = new comefrom("西藏","|拉萨|日喀则|山南|林芝|昌都|阿里|那曲");
where[27] = new comefrom("陕西","|西安|宝鸡|咸阳|铜川|渭南|延安|榆林|汉中|安康|商洛");
where[28] = new comefrom("甘肃","|兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳|临夏|甘南");
where[29] = new comefrom("宁夏","|银川|石嘴山|吴忠|固原");
where[30] = new comefrom("青海","|西宁|海东|海南|海北|黄南|玉树|果洛|海西");
where[31] = new comefrom("新疆","|乌鲁木齐|石河子|克拉玛依|伊犁|巴音郭勒|昌吉|克孜勒苏柯尔克孜|博尔塔拉|吐鲁番|哈密|喀什|和田|阿克苏");
where[32] = new comefrom("香港","");
where[33] = new comefrom("澳门","");
where[34] = new comefrom("台湾","|台北|高雄|台中|台南|屏东|南投|云林|新竹|彰化|苗栗|嘉义|花莲|桃园|宜兰|基隆|台东|金门|马祖|澎湖");
where[35] = new comefrom("其它","|北美洲|南美洲|亚洲|非洲|欧洲|大洋洲");
