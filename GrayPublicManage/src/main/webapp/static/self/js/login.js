;(function (_jquery) {

  var _loginForm = $("#loginForm").form();
  _jquery("a[name='loginBtn']").on("click", function () {
    $("#loginForm").get(0).submit();
  });

  _jquery("a[name='clearBtn']").on("click", function () {

    _loginForm.form("clear");
  });


  var loginMsg = _jquery("input[name='loginMsg']");


  var msg  = loginMsg.val();
  if ( msg && msg != '')
  {
      Component.alertDialog({},msg);
  }

})($);