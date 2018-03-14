angular.module("app", ['ngDialog','ADM-dateTimePicker','smart-table'])

    .controller("controller", ['$http', '$scope','$rootScope', 'ngDialog', '$location', function ($http, $scope,$rootScope, ngDialog) {

        var par = getParams();
        console.log(par);


        var getData = $rootScope.getData = function () {
            $scope.loading = true;
            $http({
                'method': 'GET',
                'url': './script/evenementIndex.php',
                'params': par
            }).then(function (response) {
                $scope.evenement = response.data.evenement;
            });
        };

        $scope.addevent = function (evenement) {
            $scope.categorie= GetdataCat();
            ngDialog.open({
                template: './formajout.html',
                disableAnimation: true,
                closeByDocument: false,
                controller: ['$scope', '$rootScope', function ($scope,$rootScope) {
                    $scope.evenement = evenement;
                    var date = new Date();
                    $scope.date = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
                    $scope.save = function (evenement) {
                        evenement.cuno=par['cuno'];
                        $http({
                            'method': 'GET',
                            'url': './script/save.php',
                            'params':evenement
                        }).then(function () {
                            $scope.closeThisDialog('close');
                            getData();
                        });
                    }
                }]
            })
        };

        GetdataCat= $scope.getCategoerie= function () {

            $http({
                'method':'GET',
                'url':'./script/categorie.php'

            }).then(function (response) {

                $scope.categorie= response.data.categorie;
            })

        };

    $scope.edit = function (evenement) {
            ngDialog.open({
                template: './editExtraNet.html',
                disableAnimation: true,
                closeByDocument: false,
                controller: ['$scope', function ($scope) {

                    $scope.evenement = angular.copy(evenement);
                    $scope.udpatechange = function (evenement) {
                        $http({
                            'method': 'GET',
                            'url': './script/updatefrom.php',
                            'params': evenement
                        }).then(function () {

                            $scope.closeThisDialog('close');
                            getData();
                        });
                    }
                }]
            })
        };

        $scope.delete = function (evenement) {
            ngDialog.open({
                template: './confirmSup.html',
                disableAnimation: true,
                closeByDocument: false,
                controller: ['$scope', function ($scope) {
                    $scope.sup = function () {
                        $http({
                            'method': 'GET',
                            'url': './script/deletevenement.php',
                            'params': evenement
                        }).then(function () {
                            $scope.closeThisDialog('close');
                            getData();
                        })

                    };
                    $scope.cancel = function () {
                        $scope.closeThisDialog('close');
                        GetDataEvent();
                    };
                }]
            })
        };

        function getParams() {
            var paramArray = location.search.substr(1).split("&");
            var params = [];
            for (var i = 0; i < paramArray.length; i++) {
                var tmpArray = paramArray[i].split("=");
                params[tmpArray[0]] = tmpArray[1] ? tmpArray[1] : '';
            }
            return params;
        }

       // GetDataEvent();
        getData();
        GetdataCat();

        $scope.display = 1;
        $scope.itemsByPage = 20;
    }


    ]).service('evenement', ['$http', function($http) {
    return {
        'dateLoading': function($scope){
            $http({
                'method': 'GET',
                'url': './script/date_loading.php'
            }).then(function (response) {
                $scope.dateLoading = response.data.dateLoading;
            });
        }
    }
}]);


