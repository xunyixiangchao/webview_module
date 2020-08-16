

var localjs={};
localjs.os={};
localjs.os.isIOS=/iOs|iPhone|iPad|iPod/i.test(navigator.userAgent);
localjs.os.isAndroid=!localjs.os.isIOS;
localjs.callbacks={}

localjs.callback=function(res){
}

localjs.takeNativeAction=function(commandname,parameters){
    var request={};
    request.name=commandname;
    request.param=parameters;
    if(window.localjs.os.isAndroid){
        window.webview.takeNativeAction(JSON.stringify(request));
    }else{
        window.webkit.messageHandlers.webview.postMessage(JSON.stringify(request));
    }
}
localjs.takeNativeActionWithCallback=function(commandname,parameters,callback){
    var callbackname="nativetojs_callback_"+new Date().getTime()+"_"+Math.floor(Math.random()*10000);
    console.log("callbackname: "+callbackname);
    localjs.callbacks[callbackname]={callback:callback};
    var request={};
    request.name=commandname;
    request.param=parameters;
    request.param.callbackname=callbackname;
    console.log(request);
    if(window.localjs.os.isAndroid){
        window.webview.takeNativeAction(JSON.stringify(request));
    }else{
        window.webkit.messageHandlers.webview.postMessage(JSON.stringify(request));
    }
}
window.localjs=localjs;

























