// Define the `phonecatApp` module
var app = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('HomeController',
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
				console.log("Error getting activity details");

			});

			$scope.getResourceData = function(resource) {

				localStorage.setItem("resource", resource);
				window.location.href = '/resourceDetails.html';

			}

		});