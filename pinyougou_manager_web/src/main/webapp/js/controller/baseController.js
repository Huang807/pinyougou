app.controller('baseController',function ($scope) {
    //定义一个变量 是数组
    $scope.selectIds=[];//被选择的数组 id的集合

//更新数组变量  当点击复选框的时候调用
    $scope.updateSelection=function ($event,id) {
        //$event就是获取dom的元素的封装的对象
        if($event.target.checked){
            //被勾选
            $scope.selectIds.push(id);//添加元素
        }else{
            //取消勾选
            //参数1：要删除元素的索引
            //参数2：要删除的个数
            $scope.selectIds.splice($scope.selectIds.indexOf(id),1);
        }

    }


//配置 分页的配置项
    $scope.paginationConf = {
        currentPage: 1,//当前的页码
        totalItems: 10,//总记录数
        itemsPerPage: 10,//每页显示多少行
        perPageOptions: [10, 20, 30, 40, 50],//可以选择的每页显示是的行数
        //当页码被改变的时候触发调用
        onChange: function(){
//                    $scope.findPage();
            $scope.reloadList();
        }
    };
    $scope.reloadList=function(){
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    /**
     *
     * @param jsonString 被传递过来的JSON字符串  [{"id":1,"text":"联想"},{"id":3,"text":"三星"},{"id":7,"text":"中兴"}]
     * @param key 要提取的某一个key的值对应的KEY ---》  text
     */
    //var o = {key:1}
    // o.key=1
    //o['key']=1
    $scope.jsonToString=function(jsonString,key){
        var fromJson = angular.fromJson(jsonString);
        var str="";
        for (var i=0;i<fromJson.length;i++){
            str+= fromJson[i][key]+","
        }

        if(str.length>=1){
            str= str.substring(0,str.length-1);
        }
        return str;

    }
});
