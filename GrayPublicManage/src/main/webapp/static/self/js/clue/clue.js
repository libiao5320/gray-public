;(function (_jquery) {
    var clueGrid = null;

    clueGrid = _jquery('#dg').datagrid({
        url: "http://localhost/sales/clueinfo/page",
        loader:loadGridFn,
        method:"get",
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
                // addFN();
            }
        }, '-', {
            text: '刪除',
            handler: function () {

                // var checkeds = rulegrid.datagrid("getChecked");
                // var delIds = [];
                //
                // if (checkeds && checkeds.length > 0) {
                //
                //     for (var i = 0; i < checkeds.length; i++) {
                //
                //         var checked = checkeds[i];
                //         var id = checked["ruleId"];
                //         delIds.unshift(id);
                //     }
                // }
                // delRuleFN(delIds);

            }
        }],
        onLoadSuccess: function () {
            // $(".configcondition").bind("click", function () {
            //     var _this = $(this);
            //     configconditionFN(_this.attr("_id"));
            // });
            //
            // $(".editrulebtn").bind("click", function () {
            //     var _this = $(this);
            //     editFN(_this.attr("_id"));
            // });
            //
            // $(".delrulebtn").bind("click", function () {
            //     var _this = $(this);
            //
            //     var _ids = [];
            //     _ids.unshift(_this.attr("_id"))
            //     delRuleFN(_ids);
            // });

        },
        pageNumber: "1",
        pageSize: "20",
        columns: [[
            {
                field: "id",
                checkbox: true,
                align: 'center'
            },
            {

                title: '线索名称',
                field: "clueName",

                align: 'center'
            },
            {

                title: '所属客商',

                field: "clientName",

                align: 'center'
            },

            {

                title: '线索类型',
                field: "clueTypeStr",

                align: 'center'
            },
            {
                field: "clueSourceStr",
                title: '线索来源',

                align: 'center'
            },
            {
                field: "responbilitierStr",
                title: '负责人',

                align: 'center'
            },
            {
                field: "crtUser",
                title: '创建时间',

                align: 'center'
            },
            {
                field: "status",
                title: '状态',

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
                title: '操作',
                //
                // formatter: function (value, row, index) {
                //     return " <a href='javascript:void(0);' class='configcondition' _id='"
                //         + row["ruleId"]
                //         + "' >  配置灰度条件 </a> |<a href='javascript:void(0);' class='editrulebtn' _id='"
                //         + row["ruleId"]
                //         + "' > 修改</a> |  <a href='javascript:void(0);' class='easyui-linkbutton c3 delrulebtn'     _id='"
                //         + row["ruleId"] + "'>删除</a>   ";
                // },
                align: 'center'
            }
        ]]
    });


})($);