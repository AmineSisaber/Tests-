
angular.module('app', ['smart-table', 'ngDialog'])
	.controller('controller', ['$scope', '$http', 'ngDialog', function($scope, $http, ngDialog) {
    $scope.loading = false;
    $scope.itemsByPage = 15;
    
    $scope.getData = function() {
            $scope.loading = true;
            $http({
                'method': 'GET',
                'url': './hotesse.php',
                'params': {cuno: CUNO},
            }).then(function(response){
                $scope.hotesses = response.data.hotesses;
                $scope.loading = false;
            });
        };

    $scope.viewRecord = function(prospect) {
        ngDialog.open({
            template: './hotesse.html',
            disableAnimation: true,
            closeByDocument: false,
            controller: ['$scope', function($scope) {
                $scope.prospect = prospect;
            }]
        });
    };

    $scope.editRecord = function(prospect) {
        ngDialog.open({
            template: './hotesse_edit.html',
            disableAnimation: true,
            closeByDocument: false,
            controller: ['$scope', function($scope) {
                $scope.prospect = prospect;
                $scope.statuts = [
            		'Nouveau contact',
            		'A recontacter',
            		'Déjà hôtesse',
            		'Cliente',
            		'Atelier programmé',
            		'Injoignable',
            		'Non intéressée',
            		'A réaffecter',
            		'Refus'
                ];
                $scope.updateHotesse = function(prospect) {
                	$http({
                        'method': 'GET',
                        'url': './update_hotesse.php',
                        'params': prospect
                    }).then(function(response){
                    	$scope.closeThisDialog('close');
//                    	$scope.getData();
                    });
                }
            }]
        });
    };
    
    $scope.getData();
}]).filter('newlines', function () {
	  return function (input) {
	      return input?input.split('\n'):[''];
	  };
});