//2.定义一个controller
app.controller('brandController', function ($scope,$controller,brandService) {
    $controller('baseController',{$scope:$scope})
    //发送请求去查询数据库的数据
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {//返回的数据就是[{},{}]
                $scope.list = response;
            }
        )
    }

    //初始值
    $scope.searchEntity = {};


    //根据当前的页码 和每页显示的行数分页查询调用
    $scope.findPage = function () {
        brandService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage).success(
            function (response) {//response 是PageResult
                $scope.list = response.rows;//每页显示的行
                $scope.paginationConf.totalItems = response.total;//总记录数
            }
        );
    }
    //添加品牌
    $scope.save = function () {
        //如果entity中没有ID的值说明是新增
        var serviceObject = brandService.add($scope.entity);
        // var methodName="add";
        if ($scope.entity.id != null) {
            //如果entity中有id的值说明是更新
            serviceObject = brandService.update($scope.entity)
        }
        serviceObject.success(
            function (response) {//result
                if (response.success) {
                    //新增成功 刷新列表
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }

            }
        )


    }

    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {//tbbrand
                $scope.entity = response;
            }
        )
    }


    //删除选中的品牌
    $scope.dele = function () {
        brandService.dele($scope.selectIds).success(
            function (response) {//返回的是result
                if (response.success) {
                    $scope.reloadList();//刷新列表
                } else {
                    alert(response.message);
                }
            }
        )
    }

    $scope.search = function (pageNum, pageSize) {
        brandService.search(pageNum, pageSize, $scope.searchEntity).success(
            function (response) {//pageResult
                $scope.list = response.rows;//每页显示的行
                $scope.paginationConf.totalItems = response.total;//总记录数
            }
        )
    }
})