/**
 * Created by sisaber on 22/06/2017.
 */
'use strict';
angular.module('appv', ['ngDialog', 'smart-table'])
    .controller("controller", ['$http', '$scope', 'ngDialog','evenement', function ($http, $scope, ngDialog,evenement) {
        var a = 0;


        $scope.clickSort = function () {
            $scope.orderByReverse = !$scope.orderByReverse;
            $scope.orderByPredicate = $scope.tr;
        };


        $scope.getDataNonValider = function () {
            $scope.bol=1;
            $scope.dist=1;
            $scope.dis=1;
            $scope.loading = true;
            a = 0;
            $http({
                'method': 'GET',
                'url': './script/eventnonv.php'
            }).then(function (response) {
                $scope.evenement = response.data.evenement;
                $scope.loading = false;
                $scope.myObjNon = {"background-color": "#ff4d97"};
                $scope.myObjValid = {"background-color": "black"};
                $scope.myObjAll = {"background-color": "black"};
                $scope.myObjcat = {"background-color": "black"};
            });
        };


        $scope.getAlldata = function () {
            $scope.bol=1;

            $scope.loading = true;
            $http({
                'method': 'GET',
                'url': './script/evenement.php'
            }).then(function (response) {
                $scope.evenement = response.data.evenement;
                $scope.loading = false;


                $scope.myObjNon = {"background-color": "black"};
                $scope.myObjValid = {"background-color": "black"};
                $scope.myObjAll = {"background-color": "FF69B4"};
                $scope.myObjcat = {"background-color": "black"};

            });
        };


        $scope.getAlldataValider = function () {
            $scope.bol=1;

            $scope.loading = true;
            $http({
                'method': 'GET',
                'url': './script/evenementvalider.php'
            }).then(function (response) {

                $scope.myObj = {"background-color": "white"};
                $scope.evenement = response.data.evenement;
                $scope.loading = false;
                $scope.myObjNon = {"background-color": "black"};
                $scope.myObjValid = {"background-color": "#FF69B4"};
                $scope.myObjAll = {"background-color": "black"};
                $scope.myObjcat = {"background-color": "black"};
            });
            // evenement.loadevent($scope);

        };



        $scope.update = function (event, row) {
            var index = $scope.evenement.indexOf(row);

            if (index !== -1) {
                $scope.evenement.splice(index, 1);
            }
            $http({
                'method': 'GET',
                'url': './script/valider.php',
                'params': event
            }).then(function () {
                $scope.getDataNonValider();
            });
        };


        $scope.devalider = function (event, row) {
            var index = $scope.evenement.indexOf(row);
            if (index !== -1) {
                $scope.evenement.splice(index, 1);
            }
            $http({
                'method': 'GET',
                'url': './script/devalider.php',
                'params': event
            }).then(function () {
                $scope.getAlldataValider();
            });
        };

        var getData = $scope.getAlldata;
        $scope.edit = function (evenement) {
            ngDialog.open({
                template: './formulairedit.html',
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


        //
        // var getCategorie = $scope.getCategoerie;
        //
        $scope.addCat = function () {
            ngDialog.open({
                template: './addcat.html',
                disableAnimation: true,
                closeByDocument: false,
                controller: ['$scope', function ($scope) {
                    $scope.savecat = function (categorie) {
                        $http({
                            'method': 'GET',
                            'url': './script/savecategorie.php',
                            'params': categorie
                        }).then(function () {
                            $scope.closeThisDialog('close');
                            getAllCat();
                        });
                    }
                }]
            })
        };


       var getAllCat=  $scope.getCategoerie= function () {
            $scope.bol=0;

            $http({
                'method':'GET',
                'url':'./script/categorie.php'

            }).then(function (response) {

                $scope.categorie= response.data.categorie;
                $scope.loading = false;
                $scope.myObjNon = {"background-color": "black"};
                $scope.myObjValid = {"background-color": "black"};
                $scope.myObjAll = {"background-color": "black"};
                $scope.myObjcat = {"background-color": "#FF69B4"};
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
                            'url': './script/desactivate.php',
                            'params': categorie
                        }).then(function () {
                            $scope.closeThisDialog('close');
                            getAllCat();
                        })

                    };
                    $scope.cancel = function () {
                        $scope.closeThisDialog('close');
                        getAllCat();
                    };
                }]
            })
        };



        // $scope.desactivateCategoerie= function (categorie) {
        //     $scope.bol=0;
        //
        //     $http({
        //         'method':'GET',
        //         'url':'./script/desactivate.php',
        //         'params':categorie
        //     }).then(function () {
        //         getAllCat();
        //
        //     })
        // };

        $scope.display = 1;
        $scope.itemsByPage = 8;
        $scope.getDataNonValider();

    }]).service('evenement', ['$http', function($http) {
    return {
        'loadevent': function ($scope) {
            $http({
                'method': 'GET',
                'url': './script/evenementvalider.php'
            }).then(function (response) {
                $scope.evenement = response.data.evenement;

            });
        }
    }
}]);

