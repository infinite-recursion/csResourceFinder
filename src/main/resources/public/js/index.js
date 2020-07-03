// Define the `phonecatApp` module
var phonecatApp = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
phonecatApp.controller('LoginController', function LoginController($scope,
		$http) {

	$scope.userJson = {};

	$scope.login = function() {

		$scope.loginError = false;

		$http({
			method : 'POST',
			url : '/login',
			data : $scope.userJson
		}).then(function successCallback(response) {

			if (response.data == true) {
				window.location.href = '/home.html';
				localStorage.setItem("user", JSON.stringify($scope.userJson));
			} else {
				$scope.loginError = true;
			}

		}, function errorCallback(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			$socpe.loginError = true;
		});

	}

});