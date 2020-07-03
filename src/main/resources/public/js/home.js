// Define the `phonecatApp` module
var phonecatApp = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
phonecatApp.controller('HomeController',
		function LoginController($scope, $http) {

			// Get the recent activity
			$http({
				method : 'GET',
				url : '/getActivity'
			}).then(function successCallback(response) {

				$scope.activities = response.data;

			}, function errorCallback(response) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			});

			$scope.getResourceData = function(resource) {

				$scope.loginError = false;

				localStorage.setItem("resource", resource);
				window.location.href = '/resourceDetails.html';

			}

		});