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

    window.getRestFullResult = getRestFullResult;
})($);