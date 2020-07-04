// Define the `phonecatApp` module
var app = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('ResourceController', function LoginController($scope, $http) {

	var resource = localStorage.getItem("resource");
	var user = localStorage.getItem("user");

	var resourceRequest = {};
	resourceRequest.resourceName = resource;
	resourceRequest.username = user;

	// Get the resource details activity
	$http({
		method : 'POST',
		url : '/resourceDetails/getDetails',
		data : resourceRequest
	}).then(function successCallback(response) {

		$scope.resourceData = response.data;

	}, function errorCallback(response) {
		console.log("Error getting resource details");
	});

	$scope.getResourceData = function(resource) {

		localStorage.setItem("resource", resource);
		window.location.href = '/resourceDetails.html';

	}

});