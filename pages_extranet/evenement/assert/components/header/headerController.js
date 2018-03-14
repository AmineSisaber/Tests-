angular.module("app").controller("headerCtrl", function($scope, $state, $http, ngDialog) {
	$scope.modules = [];
	$http.get('./api/dashboard/modules').then(function(response) {
		$scope.modules = response.data;
	});
//	$scope.currentState = $state.current;
	
//	$scope.go = function(route){
//        $state.go(route);
//    };
    
//    $scope.$on("$stateChangeSuccess", function(event, toState) {
//    	if($scope.tabs) {
//	        $scope.tabs.forEach(function(tab) {
//	            tab.active = $scope.active(tab.state);
//	        });
//    	}
//    });
 
//    $scope.isActive = function(route){
//    	console.log('route : ' + route + ' -> ' + $state.is(route));
//        return $state.is(route);
//    };
});