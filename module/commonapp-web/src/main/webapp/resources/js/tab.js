function addTab(url,element){
    cleanActiveClass(element);
    element.addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
    var content = '<iframe id="iframepage" onload="iFrameHeight()" width=100% scrolling="auto" frameborder="0"  src="'+url+'"></iframe>';
	$("#page-wrapper").html(content);


}
function iFrameHeight() {
    var ifm= document.getElementById("iframepage");
    var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
    if(ifm != null && subWeb != null) {
        ifm.height = document.body.clientHeight;
    }
}
function cleanActiveClass(){

    var element = $('ul.nav a').filter(function() {
        return $(this).hasClass('active');
    }).removeClass('active').parent().parent().removeClass('in').parent();
    if (element.is('li')) {
        element.removeClass('active');
    }


}