

var localjs={};
localjs.os={};
localjs.os.isIOS=/iOs|iPhone|iPad|iPod/i.test(navigator.userAgent);
localjs.os.isAndroid=!localjs.os.isIOS;

localjs.takeNativeAction=function(commandname,parameters){
    var request={};
    request.name=commandname;
    request.param=parameters;
    if(window.localjs.os.isAndroid){
    console.log("")
        window.webview.takeNativeAction(JSON.stringify(request));
    }else{
        window.webkit.messageHandlers.webview.postMessage(JSON.stringify(request));
    }
}
window.localjs=localjs;

























