/**
 * API call function
 * @param reqUrl      : Request URL
 * @param reqMethod   : Request Method (GET POST PUT DELETE ..)
 * @param param       : Request Parameters
 * @param preFunc     : Pre function
 * @param callback    : after function
 * @description
 *      async: false 동기 처리
 */
var procCallAjax = function(reqUrl, reqMethod, param, preFunc, callback) {
    console.log("procCallAjax Init");
    var reqData = "";
    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        async: false,
        contentType: "application/json",
        beforeSend: function(xhr){
            // preFunc
            if(_csrf_header && _csrf_token) {
                xhr.setRequestHeader(_csrf_header, _csrf_token);
            }
        },
        success: function(data) {
            callback(data);
        },
        error: function(jqXHR, exception) {
            if (jqXHR.status === 0) {
                console.log('Not connect.\n Verify Network.');
            }
            else if (jqXHR.status == 400) {
                console.log('Server understood the request, but request content was invalid. [400]');
            }
            else if (jqXHR.status == 401) {
                console.log('Unauthorized access. [401]');
            }
            else if (jqXHR.status == 403) {
                console.log('Forbidden resource can not be accessed. [403]');
            }
            else if (jqXHR.status == 404) {
                console.log('Requested page not found. [404]');
            }
            else if (jqXHR.status == 500) {
                console.log('Internal server error. [500]');
            }
            else if (jqXHR.status == 503) {
                console.log('Service unavailable. [503]');
            }
            else if (exception === 'parsererror') {
                console.log('Requested JSON parse failed. [Failed]');
            }
            else if (exception === 'timeout') {
                console.log('Time out error. [Timeout]');
            }
            else if (exception === 'abort') {
                console.log('Ajax request aborted. [Aborted]');
            }
            else {
                console.log('Uncaught Error.n' + jqXHR.responseText);
            }
        },
        complete : function(data) {
            // SKIP
            console.log("COMPLETE :: data :: ", data);
        }
    });
};


// MOVE PAGE
var procMovePage = function (pageUrl) {
    if (pageUrl === null || pageUrl.length < 1) {
        return false;
    }

    if ((!!pageUrl && typeof pageUrl === 'number') && -1 === pageUrl) {
        history.back();
    } else {
        // pageUrl = ("/" === pageUrl) ? "" : pageUrl;
        // location.href = procGetDashboardUrl() + pageUrl;
        location.href = pageUrl;
    }

};
