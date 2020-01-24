function getData(url, param, callback) {
    $.ajax({
        type: "get",
        url: url,
        success: function (result) {
            callback(result);
        },
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}
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
            if (result.code == '401') {
                $(".comm_mes").show().fadeOut(2000).find("p").text("登录已过期，请重新登录");
                // 清除token
                localStorage.removeItem("token");
                localStorage.removeItem("tokenHead");
                localStorage.removeItem("refreshToken");
                return false;
            }
            if (result.code == "406") {
                // 账号被封
                localStorage.setItem("close", 1);
                $(".comm_mes").show().fadeOut(2000).find("p").text("账号被封，请联系管理员解封");
                alert("账号被封，请联系管理员解封");
                return false;
            }
            localStorage.removeItem("close");
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
            if (result.code == '401') {
                $(".comm_mes").show().fadeOut(2000).find("p").text("登录已过期，请重新登录");
                // 清除token
                localStorage.removeItem("token");
                localStorage.removeItem("tokenHead");
                localStorage.removeItem("refreshToken");
                return false;
            }
            if (result.code == "406") {
                // 账号被封
                localStorage.setItem("close", 1);
                $(".comm_mes").show().fadeOut(2000).find("p").text("账号被封，请联系管理员解封");
                alert("账号被封，请联系管理员解封");
                return false;
            }
            localStorage.removeItem("close");
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
        // 清除token
        localStorage.removeItem("token");
        localStorage.removeItem("tokenHead");
        localStorage.removeItem("refreshToken");
    }
}

function hashToken() {
    var token =  localStorage.getItem("token");
    if (token) {
        return true;
    }
    return false;
}

function hashClose() {
    var close =  localStorage.getItem("close");
    if (close) {
        return true;
    }
    return false;
}

function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("tokenHead");
    localStorage.removeItem("refreshToken");
}