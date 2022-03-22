;(function (_jquery) {
    var _loginFn = function () {
        var url = "http://localhost/auth/oauth/token";
        var clientAuth = App.authentication();

        _jquery.ajax({
            type: "post",
            url,
            data: {
                grant_type: "password",
                username: "admin",
                password: "123456"
            },
            headers: {
                "Access-Control-Allow-Origin": "*",
              "Authorization":"Basic "+clientAuth,
              'Content-Type': 'application/x-www-form-urlencoded'  //multipart/form-data;boundary=--xxxxxxx   application/json
            },
            success: function (data, textStatus, jqXHR) {
                var token  = data?.value;
                var refreshToken = data?.refreshToken?.value;
                if ( null != token )
                {
                    userinfo.token = token;
                    userinfo.refreshToken = refreshToken;

                    document.cookie = "authorization=" + "Bearer "+token;
                    window.location.href = "http://localhost/index";
                }
            },
            dataType: "json"
        })
    }
    var _loginForm = _jquery("#loginForm").form();
    _jquery("a[name='loginBtn']").on("click", function () {
        // $("#loginForm").get(0).submit();
        _loginFn();
    });
    _jquery("a[name='clearBtn']").on("click", function () {
        _loginForm.form("clear");
    });
    var loginMsg = _jquery("input[name='loginMsg']");
    var msg = loginMsg.val();
    if (msg && msg != '') {
        Component.alertDialog({}, msg);
    }
})($);


