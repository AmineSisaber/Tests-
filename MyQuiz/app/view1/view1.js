'use strict';

angular.module('myApp.view1', ['ngRoute','servicesVue1'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])
.controller('View1Ctrl', ['$scope',"$http" ,function($scope,$http) {
    var getData  = function () {
        $scope.loading = true;
        $http({
            'method': 'GET',
            headers: {  'Content-Type':  'application/x-www-form-urlencoded',
                'Authorization': 'my-auth-token' },
            'url': 'http://localhost:1024/api/questions/list'
        }).then(function (response) {
            $scope.lists = response.data;
        });
    };
    $scope.addQuestion  = function (question) {
       var q= JSON.stringify(question);
        if(!$scope.questionform.$valid) {
            return $scope.msj="Un ou plusieurs champs non pas étaient saisis";
        }else {
        $http({
            'method': 'POST',
            'url': 'http://localhost:1024/api/question/new',
            'params':{data: q}
        }).then(function (response) {

        })};
    };
    var a = getData();
}]);

