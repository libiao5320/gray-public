;(function (_jquery, w) {

  var _dd;

  var closeDialog = function (_dialogObj) {
    if (_dialogObj) {
      _dialogObj.dialog("close");
    }
  }

  /**
   * 公共組件對象
   * @type {{}}
   */
  var Component = {

    confirmDialog: function (option, content, yFn, nFN) {

      var _option = {
        title: '确认对话框',
        width: 400,
        height: 200,
        closed: false,
        cache: false,
        modal: true,
        buttons: [
          {
            text: "确认",
            handler: function () {
              if (_dd) {
                closeDialog(_dd);
              }
              if (yFn) {
                yFn();
              }
            }
          },
          {
            text: "取消",
            handler: function () {

              if (_dd) {
                closeDialog(_dd);
              }

              if (nFN) {
                nFN();
              }
            }
          }
        ]
      }

      _jquery.extend(_option, option);

      _dd = _jquery("<div>" + content + "</div>").dialog(_option);

    },

    alertDialog: function (option, content) {

      var _option = {
        title: '提示对话框',
        width: 400,
        height: 200,
        closed: false,
        cache: false,
        modal: true,
        buttons: [
          {
            text: "确认",
            handler: function () {
              if (_dd) {
                closeDialog(_dd);
              }
            }
          }]

      }

      _jquery.extend(_option, option);

      _dd = _jquery("<div>" + content + "</div>").dialog(_option);

    }

  }
  w.Component = Component;

})($, window);