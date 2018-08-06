;(

    function (_jquery) {

      var addDialog, editDialog, msgDialog, routeDataGrid, servicetype,
          serviceidText,
          urlText;

      div_serviceid = $("div[name='div_serviceid']");
      div_url = $("div[name='div_url']");

      // servicetype = $("select[name='servicetype']");
      // servicetype.on("change", function () {
      //
      //   var _this = $(this);
      //
      //   if (_this.val() == "1") {
      //
      //     div_serviceid.val("");
      //
      //     div_url.show();
      //
      //     div_serviceid.hide();
      //
      //   }
      //
      //   else {
      //
      //     div_url.val("");
      //     div_url.hide();
      //     div_serviceid.show();
      //
      //   }
      //
      // });


      $("input[name='routeKey']").textbox({
        label: '路由名称',
        width: "80%"
      });


      $("input[name='retryable']").combobox({
        data: [{"value": "1", "text": "是"},
          {"value": "0", "text": "否"}],
        valueField: "value",
        textField: "text",
        label: '是否允许重试:',
        width: "80%",
      });

      serviceidText = $("input[name='url']").textbox({
        label: '灰度URL',
        width: "80%"
      });

      urlText = $("input[name='serviceId']").textbox({
        label: '微服务实例',
        width: "80%"
      });


      servicetype =  $("input[name='servicetype']").combobox({
        data: [{"value": "1", "text": "URL"},
          {"value": "2", "text": "服务实例"}],
        valueField: "value",
        textField: "text",
        label: '灰度类型:',
        width: "80%",
        onChange:function(){
          var _this = $(this);
          if (_this.val() == "1") {
            serviceidText.textbox("enable");
            urlText.textbox("disable");
          }

          else {
            urlText.textbox("enable");
            serviceidText.textbox("disable");
          }
        }
      }).combobox("setValue","1");



      $("input[name='stripPrefix']").combobox({
        data: [{"value": "1", "text": "是"},
          {"value": "0", "text": "否"}],
        valueField: "value",
        textField: "text",
        label: '是否追加前缀:',
        width: "80%",
      });

      $("input[name='path']").textbox({
        label: '匹配路径',
        width: "80%"
      });

      $("input[name='index']").textbox({
        label: '排序',
        width: "80%"
      });

      serviceidText.textbox("enable"); //默认使用 SERVICE URL



      var addFN = function () {



        if (!addDialog) {

          addDialog = _jquery("#add_dd").dialog({
            title: 'Add Route',
            width: "60%",
            height: "auto",
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
              text: 'Save',
              handler: function () {

                var postData = JSON.stringify($('#add_ff').serializeJSON());

                _jquery.ajax({
                  data: postData,
                  url: "/addRoute",
                  method: "post",
                  success: function (data) {
                    addDialog.dialog('close');
                    routeDataGrid.datagrid('reload');
                    $('#add_ff').form('reset');
                  },
                  error: function () {
                    alert("error");
                    addDialog.dialog('close');
                    routeDataGrid.datagrid('reload');
                  },
                  headers: {
                    "Content-Type": "application/json"
                  }

                });
              }
            }, {
              text: 'Close',
              handler: function () {
                addDialog.dialog('close');

              }
            }]
          });
        }
        else {
          addDialog.dialog();
        }

      }

      var delFN = function (delIds) {

        if ( delIds && delIds.length > 0) {
          Component.confirmDialog({}, "是否确定删除?", function () {
            _jquery.ajax({
              data: JSON.stringify(delIds),
              url: "/delRoute",
              method: "post",
              traditional: true,
              success: function (data) {
                routeDataGrid.datagrid('reload');
              },
              error: function () {
                alert("error");
                routeDataGrid.datagrid('reload');
              },
              headers: {
                "Content-Type": "application/json"
              }

            });
          });
        }
        else
        {
          Component.alertDialog({},"请选择要删除的数据");
        }



      }

      var editFN = function (routeId) {
        var _route;

        $.get("/getRoute", {"routeId": routeId}, function (data) {

          if (data && data["code"] == "0000") {
            _route = data["value"];


            $('#edit_ff').form('load', _route);



            // 根据RouteInfo 的值 设置 ServiceType;
            if( _route["serviceId"] && _route["serviceId"] != "" ) {
              servicetype.combobox("setValue", "2");
            }
            else{
              servicetype.combobox("setValue", "1");
            }




            if (!editDialog) {



              editDialog = _jquery("#edit_dd").dialog({
                title: 'Edit Route',
                width: "60%",
                height: "auto",
                closed: false,
                cache: false,
                modal: true,
                buttons: [{
                  text: 'Save',
                  handler: function () {

                    var postData = JSON.stringify(
                        $('#edit_ff').serializeJSON());

                    _jquery.ajax({
                      data: postData,
                      url: "/updateRoute",
                      method: "post",
                      success: function (data) {
                        editDialog.dialog('close');
                        routeDataGrid.datagrid('reload');
                        $('#edit_dd').form('reset');
                      },
                      error: function () {
                        alert("error");
                        editDialog.dialog('close');
                        routeDataGrid.datagrid('reload');
                      },
                      headers: {
                        "Content-Type": "application/json"
                      }

                    });
                  }
                }, {
                  text: 'Close',
                  handler: function () {
                    editDialog.dialog('close');

                  }
                }]
              });

            }
            else {
              editDialog.dialog();
            }
          }
          else {
            alert("加载失败:" + data["value"]);
          }

        }, "json");

      }

      routeDataGrid = _jquery('#dg').datagrid({

        url: "/fetchRoute",

        // title: '路由列表',
        // iconCls: 'icon-save',
        width: "99%",
        // height: "99%",
        pagination: true,
        rownumbers: true,
        toolbar: [{
          text: '新增',
          handler: function () {
            addFN();
          }
        }, '-', {
          text: '删除',
          handler: function () {




            var checkeds = routeDataGrid.datagrid("getChecked");
            var delIds = [];


            if (checkeds && checkeds.length > 0) {

              for (var i = 0; i < checkeds.length; i++) {

                var checked = checkeds[i];
                var id = checked["id"];
                delIds.unshift(id);
              }
            }
            delFN(delIds);
          }
        }],
        onLoadSuccess: function () {
          $(".editBtn").bind("click", function () {
            var _this = $(this);

            editFN(_this.attr("_id"));
          });

          $(".delBtn").bind("click", function () {
            var _this = $(this);

            var _ids = [];
            _ids.unshift(_this.attr("_id"))
            delFN(_ids);
          });
        },
        pageNumber: "1",
        pageSize: "20",
        columns: [[
          {
            field: "id",
            // width: "10%",
            checkbox: true
          },
          {
            title: 'Route Key',
            field: "routeKey",
            // width: "10%",
            align: 'center'
          },
          {

            title: 'Path',
            field: "path",
            // width: "20%",
            align: 'center'
          },
          {

            title: 'Strip Prefix',
            field: "stripPrefix",
            // width: "10%",
            align: 'center',
            formatter: function (value, row, index) {
              if (value == "1") {
                return "是";
              } else {
                return "否";
              }
            }
          },
          {
            field: "url",
            title: 'URL',
            // width: "30%",
            align: 'center'
          },

          {
            field: "serviceId",
            title: 'Service ID',
            // width: "auto",
            align: 'center'
          },

          {
            field: "retryable",
            title: 'Retryable',
            // width: "auto",
            align: 'center',
            formatter: function (value, row, index) {
              if (value == "1") {
                return "是";
              } else {
                return "否";
              }
            }
          },

          {
            field: "state",
            title: 'State',
            // width: "auto",
            formatter: function (value, row, index) {

              if (value == "1") {
                return "正常";
              } else {
                return "失效";
              }
            },
            align: 'center'
          },
          {

            title: 'Create Time',
            field: "crtTime",
            // width: "auto",
            align: 'center'
          },
          {
            field: "crtUser",
            title: 'Create USER',
            // width: "auto",
            align: 'center'
          },

          {
            field: "Action",
            title: 'action',
            // width: "auto",
            formatter: function (value, row, index) {
              return "<a href='javascript:void(0);' class='editBtn' _id='"
                  + row["id"]
                  + "'  > 修改  </a> | <a href='javascript:void(0);' class='delBtn'  _id='"
                  + row["id"] + "' >删除</a>  ";
            },
            align: 'center'
          }
        ]]
      });

    })($);