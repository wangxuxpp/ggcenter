// JavaScript Document
var $D = function(id){return typeof id == 'string' ? document.getElementById(id) : id;};
String.prototype.trim = function(){return this.replace(/(\s+)|(\s+$)/g,"");};
function search(q, for_q){
    if($D(q) == null) return;
    if(!$D(q).value) {$D(for_q).style.display = '';    }
    $D(q).onfocus = function(){$D(for_q).style.display = 'none';};
    $D(q).onblur = function(){
        if($D(q).value.trim().length == 0) $D(for_q).style.display = 'block';
        else $D(for_q).style.display = 'none';
    };
    $D(for_q).onclick = function(){$D(for_q).style.display = 'none';$D(q).focus();    };
    $D(q).onblur();
};