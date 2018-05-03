app.controller('indexController',function ($scope,loginService) {
    $scope.getLoginName=function () {
        loginService.getLoginName().success(
            function (response) {//map
                $scope.loginName = response.loginName;//登录名
            }
        )
    }
})