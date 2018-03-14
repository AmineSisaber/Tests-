angular.module("app")
    .controller("controller_perso", ['$http', '$scope', '$location', 'language', function($http, $scope, $location, language) {
        $scope.language = language;
        var cuno = ($scope.ngDialogData && $scope.ngDialogData.cuno) ? $scope.ngDialogData.cuno : CUNO;
		$scope.name = ($scope.ngDialogData && $scope.ngDialogData.name) ? $scope.ngDialogData.name : '';
        
        $http({
            'method': 'GET',
            'url': './script/personnal.php',
            'params': {cuno: cuno}
        }).then(function (response) {
            $scope.weeks = response.data.weeks;
            $scope.months = response.data.months;
            $scope.currYear = response.data.year;
            $scope.currMonth = response.data.month;
            $scope.currWeek = response.data.week;
            $scope.monthCount = response.data.monthCount;
            $scope.weekCount = response.data.weekCount;
            $scope.planifiedSum = response.data.planifiedSum;
            $scope.realizedSum = response.data.realizedSum;
            $scope.histoSum = response.data.histoSum;
            $scope.dateLoading = response.data.dateLoading;
        });
        
        $scope.weekRealised = function(week) {
            return week < $scope.currWeek;  
        };
        
        $scope.monthRealised = function(month) {
            return month < $scope.currMonth;  
        };
}]);