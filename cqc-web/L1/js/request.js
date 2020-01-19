function getForAuth(url, param, callback) {
    var token = localStorage.getItem("token")
    var tokenHead = localStorage.getItem("tokenHead");
    $.ajax({
        type: "get",
        url: url,
        headers: {
            'Authorization': tokenHead + ' ' + token
        },
        success: function (result) {
            callback(result);
        },
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function postForAuth(url, param, callback) {
    var token = localStorage.getItem("token")
    var tokenHead = localStorage.getItem("tokenHead");
    $.ajax({
        type: "post",
        url: url,
        data: JSON.stringify(param),
        contentType: "application/json;charset=UTF-8",
        headers: {
            'Authorization': tokenHead + ' ' + token
        },
        success: function (result) {
            callback(result);
        },
        error: function (e) {
            console.log(e);
            alert("服务器错误");
        }
    });
}
function handleResult(result){
    if (result.code == '401') {
        $(".comm_mes").show().fadeOut(2000).find("p").text("登录已过期，请重新登录");
        // 清楚token
        localStorage.removeItem("token");
        localStorage.removeItem("tokenHead");
        localStorage.removeItem("refreshToken");
    }

}