;(

    function (_jquery) {

      var addDialog, editDialog, conditionDialog, conditiongrid, indexgrid,
          ruleId, conditionId, strategyId;

      var rulegrid;

      var editConditionIndex = undefined;
      var editindexIndex = undefined;

      var _graytype, _grayurl = null;

      var strategyList, strategyFieldList;

      _jquery.get("/getRouteInfo", null, function (data) {

        var _routes = [];

        console.log(data["code"]);
        if (data && data["code"] == "0000") {

          _routes = data["value"];
          console.log(_routes.length);
        }
        else {
          _routes = [{"id": "0", "path": "none"}];
        }

        $("input[name='url']").combobox({
          data: _routes,
          valueField: "path",
          textField: "path",
          label: '路由:',
          width: "80%"
        });

        _graytype = $("input[name='ruleResult[graytype]']").combobox({
          data: [{"value": "FORBIDDEN", "text": "禁止访问"},
            {"value": "FORWARD", "text": "转发"}],
          valueField: "value",
          textField: "text",
          label: '灰度处理方式:',
          width: "80%",
          onChange: function () {
            var _this = $(this);

            if (_this.val() == "FORWARD") {
              _grayurl.textbox("enable");
            }

            else {
              _grayurl.textbox("disable");
              _grayurl.textbox("setValue", "none");
            }

          }
        });

        _grayurl = $("input[name='ruleResult[grayValue]']").textbox({
          label: '灰度路由',
          iconAlign: 'left',
          width: "80%"
        })

      }, "json");

      function endConditionEdit() {
        if (editConditionIndex == undefined) {
          return true
        }
        if ($('#condition_dg').datagrid('validateRow', editConditionIndex)) {
          $('#condition_dg').datagrid('endEdit', editConditionIndex);
          editConditionIndex = undefined;
          return true;
        } else {
          return false;
        }
      }

      function endIndexEdit() {

        if (!conditionId) {
          alert("没有选中灰度条件组");
          return false;
        }

        if (editindexIndex == undefined) {
          return true
        }
        if (indexgrid.datagrid('validateRow', editindexIndex)) {
          indexgrid.datagrid('endEdit', editindexIndex);
          editindexIndex = undefined;
          return true;
        } else {
          return false;
        }
      }

      function onConditionClickCell(index, field) {
        if (editConditionIndex != index) {
          if (endConditionEdit()) {
            $('#condition_dg').datagrid('selectRow', index)
            .datagrid('beginEdit', index);
            var ed = $('#condition_dg').datagrid('getEditor',
                {index: index, field: field});
            if (ed) {
              ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox')
                  : $(ed.target)).focus();
            }
            editConditionIndex = index;
          } else {
            setTimeout(function () {
              $('#condition_dg').datagrid('selectRow', editConditionIndex);
            }, 0);
          }
        }
      }

      function onIndexClickCell(index, field) {
        if (editindexIndex != index) {
          if (endIndexEdit()) {
            indexgrid.datagrid('selectRow', index)
            .datagrid('beginEdit', index);
            var ed = indexgrid.datagrid('getEditor',
                {index: index, field: field});
            if (ed) {
              ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox')
                  : $(ed.target)).focus();
            }
            editindexIndex = index;
          } else {
            setTimeout(function () {
              $('#condition_dg').datagrid('selectRow', editindexIndex);
            }, 0);
          }
        }
      }

      var onConditionEndEdit = function (index, row) {
        var ed = $(this).datagrid('getEditor', {
          index: index,
          field: 'strategy'
        });

        $(ed.target).combobox('setValue', row.strategy);
        // row.productname = $(ed.target).combobox('getText');
      }

      var onIndexEndEdit = function (index, row) {
        var ed = $(this).datagrid('getEditor', {
          index: index,
          field: 'field'
        });

        $(ed.target).combobox('setValue', row.field);
      }

      var addConditionFN = function () {

        if (endConditionEdit()) {
          $('#condition_dg').datagrid('appendRow', {status: 'P'});
          editConditionIndex = $('#condition_dg').datagrid('getRows').length
              - 1;
          $('#condition_dg').datagrid('selectRow', editConditionIndex)
          .datagrid('beginEdit', editConditionIndex);
        }

      }

      var addIndexFN = function () {

        if (endIndexEdit()) {
          indexgrid.datagrid('appendRow', {status: 'P'});
          editindexIndex = indexgrid.datagrid('getRows').length - 1;
          indexgrid.datagrid('selectRow', editindexIndex)
          .datagrid('beginEdit', editindexIndex);
        }

      }

      var addIndexAccept = function () {

        if (endIndexEdit()) {
          indexgrid.datagrid('acceptChanges');
        }

        var rows = indexgrid.datagrid('getRows');

        if (rows && rows.length > 0) {
          for (var i = 0; i < rows.length; i++) {

            var _url = "";
            var postData = rows[i];

            postData["conditionId"] = conditionId;

            if (!postData["indexId"]) {
              _url = "/addIndex";
            }
            else {
              _url = "/updateIndex";
            }

            alert(JSON.stringify(postData));
            _jquery.ajax({
              data: JSON.stringify(postData),
              url: _url,
              method: "post",
              success: function (data) {

              },
              error: function () {
                alert("error");
                // addDialog.dialog('close');
                // routeDataGrid.datagrid('reload');
              },
              headers: {
                "Content-Type": "application/json"
              }

            })
          }
        }

        indexgrid.datagrid('reload');

      }

      var addConditionAccept = function () {

        if (endConditionEdit()) {
          $('#condition_dg').datagrid('acceptChanges');
        }

        var rows = $('#condition_dg').datagrid('getRows');

        if (rows && rows.length > 0) {
          for (var i = 0; i < rows.length; i++) {

            var _url = "";
            var postData = rows[i];

            postData["ruleId"] = ruleId;

            if (!postData["conditionId"]) {
              _url = "/addCondition";
            }
            else {
              _url = "/updateCondition";
            }

            _jquery.ajax({
              data: JSON.stringify(postData),
              url: _url,
              method: "post",
              success: function (data) {

              },
              error: function () {
                alert("error");
                // addDialog.dialog('close');
                // routeDataGrid.datagrid('reload');
              },
              headers: {
                "Content-Type": "application/json"
              }

            })
          }
        }

        // indexgrid.datagrid("load", "/fetchIndexByCondtion?conditionId="
        //     + conditionId);

      }

      var configconditionFN = function (pRuleId) {
        ruleId = pRuleId;
        conditionDialog = _jquery("#condition_dd").dialog({
          title: '配置灰度条件',
          width: "60%",
          height: "60%",
          closed: false,
          cache: false,
          modal: true
          // buttons: [{
          //   text: 'Save',
          //   handler: function () {
          //     alert("aaaa")
          //   }
          // }]
        });

        conditiongrid = _jquery('#condition_dg').datagrid({

          url: "/fetchConditionByRuleId?ruleid=" + ruleId,

          title: '灰度条件组',
          // iconCls: 'icon-save',
          width: "99%",
          height: "auto",
          emptyMsg: " No Data",
          checkOnSelect: false,
          ctrlSelect: true,
          pagination: true,
          rownumbers: true,
          pageNumber: "1",
          pageSize: "20",
          onBeginEdit: function (index, rowData) {
            var strategyEditor = $('#condition_dg').datagrid('getEditor', {
              index: index,
              field: 'strategy'
            });
            $(strategyEditor.target).combobox({
              onShowPanel: function () {   //下拉展开时动态修改options
                _jquery.get("/getGrayStrategy", null, function (data) {
                  if (data && data["code"] == "0000") {
                    strategyList = eval(data["value"]);
                  }
                  else {
                    strategyList = [];
                  }
                  $(strategyEditor.target).combobox("loadData", strategyList);

                }, "json");
              }
            });
            $(strategyEditor.target).combobox('setValue', rowData.strategy);

          },
          onEndEdit: onConditionEndEdit,
          onClickCell: onConditionClickCell,
          onClickRow: function (index, row) {
            conditionId = row["conditionId"];
            strategyId = row["strategy"];
            indexgrid.datagrid("load", "/fetchIndexByCondtion?conditionId="
                + conditionId);
          },
          onLoadSuccess: function () {

            $(".delConditionbtn").bind("click", function () {
              var _this = $(this);
              var _ids = [];
              _ids.unshift(_this.attr("_id"))
              delConditionFN(_ids);
            });

          },
          toolbar: [{
            text: '新增',
            handler: function () {
              // addFN();

              addConditionFN();
            }
          }, '-', {
            text: '删除',
            handler: function () {
              var checkeds = conditiongrid.datagrid("getChecked");
              var delIds = [];
              if (checkeds && checkeds.length > 0) {

                for (var i = 0; i < checkeds.length; i++) {

                  var checked = checkeds[i];
                  var id = checked["conditionId"];
                  delIds.unshift(id);
                }
              }
              delConditionFN(delIds);
            }
          }, '-', {
            text: '保存',
            handler: function () {
              addConditionAccept();
            }
          }],
          columns: [[
            {

              field: "conditionId",
              checkbox: true,
              align: 'center'
            },
            {
              title: "条件组名称",
              field: "conditionName",
              align: 'center',
              editor: 'textbox'
            },

            {
              title: "灰度策略",
              field: "strategy",
              align: 'center',
              editor: {
                type: 'combobox',
                options: {
                  valueField: 'strategyName',
                  textField: 'strategyName'
                }
              }
            },
            {
              title: "满足条件模式",
              field: "logicSymbol",
              editor: {
                type: 'combobox',
                options: {
                  data: [{"value": "ANY", "text": "ANY"},
                    {"value": "ALL", "text": "ALL"}],
                  valueField: "value",
                  textField: "text",
                  required: true
                }
              }
            },

            {

              title: 'Create Time',
              field: "crtTime",

              align: 'center'
            },
            {
              field: "crtUser",
              title: 'Create USER',

              align: 'center'
            },
            {
              field: "state",
              title: 'STATE',

              formatter: function (value, row, index) {
                if (value == 1) {
                  return "正常";
                } else {
                  return "失效";
                }
              },
              align: 'center'
            },
            {
              field: "Action",
              title: 'action',

              formatter: function (value, row, index) {
                return "<a href='javascript:void(0);' class='easyui-linkbutton c3 delConditionbtn' _id='"
                    + row['conditionId'] + "' > 删除</a>   ";
              },
              align: 'center'
            }]]
        });

        indexgrid = _jquery('#index_dg').datagrid({

          // url: "/fetchCondition",

          title: '灰度项',
          onBeginEdit: function (index, rowData) {
            var fieldEditor = $('#index_dg').datagrid('getEditor', {
              index: index,
              field: 'field'
            });
            $(fieldEditor.target).combobox({
              onShowPanel: function () {   //下拉展开时动态修改options
                _jquery.get("/getGrayStrategyField", {"strategy":strategyId}, function (data) {
                  if (data && data["code"] == "0000") {
                    strategyFieldList = eval(data["value"]);
                  }
                  else {
                    strategyFieldList = [];
                  }

                  $(fieldEditor.target).combobox("loadData", strategyFieldList);

                }, "json");
              }
            });
            $(fieldEditor.target).combobox('setValue', rowData.field);

          },
          onEndEdit: onIndexEndEdit,
          onClickCell: onIndexClickCell,
          onLoadSuccess: function () {
            $(".delIndexbtn").bind("click", function () {
              var _this = $(this);
              var _ids = [];
              _ids.unshift(_this.attr("_id"))
              delIndexFN(_ids);
            });

          },
          // iconCls: 'icon-save',
          width: "99%",
          height: "auto",
          emptyMsg: " No Data",
          pagination: true,
          rownumbers: true,
          pageNumber: "1",
          pageSize: "20",
          toolbar: [{
            text: '新增',
            handler: function () {
              // addFN();

              addIndexFN();
            }
          }, '-', {
            text: '删除',
            handler: function () {
              var checkeds = indexgrid.datagrid("getChecked");
              var delIds = [];
              if (checkeds && checkeds.length > 0) {

                for (var i = 0; i < checkeds.length; i++) {

                  var checked = checkeds[i];
                  var id = checked["indexId"];
                  delIds.unshift(id);
                }
              }
              delIndexFN(delIds);
            }
          }, '-', {
            text: '保存',
            handler: function () {
              addIndexAccept();
            }
          }],
          columns: [[
            {

              field: "indexId",
              checkbox: true,
              align: 'center'
            },
            {
              title: "字段",
              field: "field",
              align: 'center',
              editor: {
                type: 'combobox',

                options: {
                  valueField: 'fieldValue',
                  textField: 'fieldName'
                }

              },
              width: 135
            },
            {
              title: "操作符",
              field: "op",
              align: 'center',
              width: 135,
              editor: {
                type: 'combobox',
                options: {

                  data: [{"value": "_EQ", "text": "_EQ"},
                    {"value": "_NEQ", "text": "_NEQ"},
                    {"value": "_GT", "text": "_GT"},
                    {"value": "_LT", "text": "_LT"},
                    {"value": "_GTEQ", "text": "_GTEQ"},
                    {"value": "_LTEQ", "text": "_LTEQ"},
                    {"value": "_IN", "text": "_IN"},
                    {"value": "_NIN", "text": "_NIN"}],

                  valueField: "value",
                  textField: "text",
                  required: true
                }
              }
            },
            {
              title: "值",
              field: "value",
              align: 'center',
              width: 135,
              editor: 'textbox'
            },
            {

              title: 'Create Time',
              field: "crtTime",

              align: 'center'
            },
            {
              field: "crtUser",
              title: 'Create USER',

              align: 'center'
            },
            {
              field: "state",
              title: 'STATE',

              formatter: function (value, row, index) {
                if (value == 1) {
                  return "正常";
                } else {
                  return "失效";
                }
              },
              align: 'center'
            },
            {
              field: "Action",
              title: 'action',

              formatter: function (value, row, index) {
                return " <a href='javascript:void(0);' class='easyui-linkbutton c3 delIndexbtn' _id='"
                    + row['indexId'] + "' >删除</a>   ";
              },
              align: 'center',
              width: 135
            }]]
        });

      }

      var addFN = function () {

        $('#add_ff').form('reset');
        if (!addDialog) {
          addDialog = _jquery("#add_dd").dialog({
            title: 'Add Route',
            width: "60%",
            // height: "auto",
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
              text: 'Save',
              handler: function () {

                var postData = JSON.stringify(
                    _jquery('#add_ff').serializeJSON());

                _jquery.ajax({
                  data: postData,
                  url: "/addRule",
                  method: "post",
                  success: function (data) {
                    addDialog.dialog('close');
                    rulegrid.datagrid('reload');
                    $('#add_ff').form('reset');
                  },
                  error: function () {
                    alert("error");
                    addDialog.dialog('close');
                    rulegrid.datagrid('reload');
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

      var editFN = function (ruleId) {
        var _rule;
        _jquery.get("/getRule", {"ruleId": ruleId}, function (data) {

          if (data && data["code"] == "0000") {
            _rule = data["value"];




            $('#edit_ff').form('load', _rule);
            _graytype.combobox("setValue",
                _rule["ruleResult"]['graytype']);
            _grayurl.textbox("setValue",
                _rule["ruleResult"]['grayValue']);

            console.log(_rule["ruleResult"]['resultId']);

            _jquery("#edit_ff").find("input[name='ruleResult[resultId]']").val(
                _rule["ruleResult"]['resultId']);

            console.log(_jquery("#edit_ff").find(
                "input[name='ruleResult[resultId]']").val());


            if (!editDialog) {
              editDialog = _jquery("#edit_dd").dialog({
                title: 'Edit Rule',
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
                      url: "/updateRule",
                      method: "post",
                      success: function (data) {
                        editDialog.dialog('close');
                        rulegrid.datagrid('reload');
                        $('#edit_ff').form('reset');
                      },
                      error: function () {
                        alert("error");
                        editDialog.dialog('close');
                        rulegrid.datagrid('reload');
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

      var delRuleFN = function (delIds) {


        if ( delIds && delIds.length > 0) {

          Component.confirmDialog({}, "是否确定删除?", function () {
            _jquery.ajax({
              data: JSON.stringify(delIds),
              url: "/delRule",
              method: "post",
              traditional: true,
              success: function (data) {
                rulegrid.datagrid('reload');
              },
              error: function () {
                alert("error");
                rulegrid.datagrid('reload');
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

      var delConditionFN = function (delIds) {
        if ( delIds && delIds.length > 0) {
          Component.confirmDialog({}, "是否确定删除?", function () {
            _jquery.ajax({
              data: JSON.stringify(delIds),
              url: "/delCondition",
              method: "post",
              traditional: true,
              success: function (data) {
                conditiongrid.datagrid('reload');
              },
              error: function () {
                alert("error");
                conditiongrid.datagrid('reload');
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

      var delIndexFN = function (delIds) {
        if ( delIds && delIds.length > 0) {
          Component.confirmDialog({}, "是否确定删除?", function () {
            _jquery.ajax({
              data: JSON.stringify(delIds),
              url: "/delIndex",
              method: "post",
              traditional: true,
              success: function (data) {
                indexgrid.datagrid('reload');
              },
              error: function () {
                alert("error");
                indexgrid.datagrid('reload');
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

      rulegrid = _jquery('#dg').datagrid({

        url: "/fetchRule",

        // title: 'Frozen Columns',
        // iconCls: 'icon-save',
        width: "100%",
        height: "auto",
        emptyMsg: " No Data",
        pagination: true,
        rownumbers: true,
        checkOnSelect: false,
        ctrlSelect: true,
        toolbar: [{
          text: '新增',
          handler: function () {
            addFN();
          }
        }, '-', {
          text: '刪除',
          handler: function () {

            var checkeds = rulegrid.datagrid("getChecked");
            var delIds = [];

            if (checkeds && checkeds.length > 0) {

              for (var i = 0; i < checkeds.length; i++) {

                var checked = checkeds[i];
                var id = checked["ruleId"];
                delIds.unshift(id);
              }
            }
            delRuleFN(delIds);

          }
        }],
        onLoadSuccess: function () {
          $(".configcondition").bind("click", function () {
            var _this = $(this);
            configconditionFN(_this.attr("_id"));
          });

          $(".editrulebtn").bind("click", function () {
            var _this = $(this);
            editFN(_this.attr("_id"));
          });

          $(".delrulebtn").bind("click", function () {
            var _this = $(this);

            var _ids = [];
            _ids.unshift(_this.attr("_id"))
            delRuleFN(_ids);
          });

        },
        pageNumber: "1",
        pageSize: "20",
        columns: [[
          {

            field: "ruleId",
            checkbox: true,
            align: 'center'
          },
          {

            title: 'URL',
            field: "url",

            align: 'center'
          },
          {

            title: '灰度url',

            field: "grayValue",
            formatter: function (value, row, index) {

              return !row["ruleResult"].grayValue ? ""
                  : row["ruleResult"].grayValue;
            },

            align: 'center'
          },
          {
            field: "graytype",
            title: '灰度处理方式',
            formatter: function (value, row, index) {
              return row["ruleResult"].graytype == "FORWARD" ? "转发" : "禁止访问";
            },

            align: 'center'
          },
          {

            title: 'Create Time',
            field: "crtTime",

            align: 'center'
          },
          {
            field: "crtUser",
            title: 'Create USER',

            align: 'center'
          },
          {
            field: "state",
            title: 'STATE',

            formatter: function (value, row, index) {
              if (value == 1) {
                return "正常";
              } else {
                return "失效";
              }
            },
            align: 'center'
          },
          {
            field: "Action",
            title: 'action',

            formatter: function (value, row, index) {
              return " <a href='javascript:void(0);' class='configcondition' _id='"
                  + row["ruleId"]
                  + "' >  配置灰度条件 </a> |<a href='javascript:void(0);' class='editrulebtn' _id='"
                  + row["ruleId"]
                  + "' > 修改</a> |  <a href='javascript:void(0);' class='easyui-linkbutton c3 delrulebtn'     _id='"
                  + row["ruleId"] + "'>删除</a>   ";
            },
            align: 'center'
          }
        ]]
      });

    }
)($);
