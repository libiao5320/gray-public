;(function (_jquery) {


    var validate = function (data) {
        if (!!data) {
            var code = data?.code;
            if (!!code && code == '000000') {
                console.info(" success ");
                return true;
            }
        } else {
            console.error("error msg " + data?.msg);
            console.error("exception msg " + data?.msg);
            return false;
        }

    }

    var getRestFullResult = function (data) {
        if (validate(data)) {
            return data?.data;
        }
    }


    var loadGridFn = function(params,successFn,errorFn){


        var that =_jquery(this);
        var opts = that.datagrid("options");

        if (!opts.url) {
            return false;
        }




        _jquery.ajax({
            type: opts.method,
            url: opts.url,
            data: {
                "currentPage":opts?.pageNumber,
                "pageSize":opts?.pageSize
            },   // param
            dataType: "json",
            success: function(data, textStatus, jqXHR) {

                console.info( "load grid success");
                var gridResult = converToGridJson(data);

                console.info( "rows" + gridResult.rows);

                console.info( "total" + gridResult.total);

                successFn(gridResult);  // loader(param, success, error)中的参数：success方法。
                // 调用loader(param, success, error)方法传入的success(data)方法。
                // 向这个方法传入的data参数，会继续传给loaderFilter(data)。
            },
            error: function(jqXHR, textStatus, errorThrown) {
                errorFn();
            }
        });

    }

    var converToGridJson = function(data){
        var result = getRestFullResult(data);
        var  gridJson = {};
        gridJson["total"] =result?.total;
        gridJson["rows"] =result?.records;
        return gridJson;
    }
    window.loadGridFn = loadGridFn;
    window.getRestFullResult = getRestFullResult;
})($);