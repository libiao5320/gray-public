;(function(_jquery){

    var successFn =  function (data, textStatus, jqXHR) {
        console.info(" >>>>>>>>>>>>>>>> 获取菜单信息 " +JSON.stringify(data));
        var menuList  = getRestFullResult(data);

        if (!!menuList)
        {
            // var menuUl = _jquery("#menuUl");
            var treeDatas  = [];
            for (var i = 0 ; i < menuList.length;i++)
            {
                console.info(menuList[i].menuName);
                var treeJson = converToTreeData(menuList[i]);
                treeDatas[i]=treeJson;
            }

            var menuTree = _jquery("#menuUl").tree({
                data:treeDatas,
                onClick:function(node){
                    openMenu(node.attributes.url);
                }
            });
            // menuTree.loadData(treeDatas);
        }

    }


    var converToTreeData = function(menuObj){
        var treeJson = {
            "id": menuObj?.id,
            "text": menuObj?.menuName,
            "state": "closed",
            "attributes":{"url":menuObj.parentMenu == 0 ? "" :menuObj?.menuUrl}
        };

        if (!!menuObj.child && menuObj.child.length > 0)
        {
            var childTreeData = [];
            for (var i=0;i< menuObj.child.length ;i++)
            {
                childTreeData[i] = converToTreeData(menuObj.child[i]);
            }
            treeJson.children = childTreeData;
        }
        return treeJson;
    }


    var url = "http://localhost/system/menu/queryUserOwnMenu";
    console.info(" >>>>>>>>>>>>>>>> 获取菜单信息 ");
    _jquery.ajax({
        url:url,
        type:"GET",
        success:successFn
    });

    var openMenu = function(_href){
        console.info("openMenu :::::::::::" + _href);
        if (!!_href && _href != '') {
            $("iframe[name='content']").attr("src", _href);
        }
    };




    window.openMenu = openMenu;

})($);