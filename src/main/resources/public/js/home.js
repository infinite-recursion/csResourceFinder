// Define the `phonecatApp` module
var app = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('HomeController',
		function HomeController($scope, $http) {
	
			//Return user to the login screen
			if(localStorage.getItem("user")==null){
				window.location.href = '/index.html';
			}

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

			};


		});

//Handles the logic for searching for results
app.controller('SearchController', function SearchController($scope, $http) {


	$scope.searchPriorityOptions = [];
	var highestRating = {};
	highestRating.value = 'highest rating';
	highestRating.name = 'highest rating';
	$scope.searchPriorityOptions.push(highestRating);
	
	var highestTag = {};
	highestTag.value = 'highest tag';
	highestTag.name = 'highest tag frequency';
	$scope.searchPriorityOptions.push(highestTag);
	
	$scope.selectedSearchPriority = highestRating.value;
	// Get the tags for the search
	$http({
		method : 'GET',
		url : '/getTags'
	}).then(function successCallback(response) {

		$scope.tags = response.data;

	}, function errorCallback(response) {
		console.log("Error getting tags for search");
	});
	
	
	$scope.logout = function(){
		
		localStorage.removeItem("user");
		window.location.href = '/index.html';
		

	};
	
	$scope.checkSearchMode = function(){
		
		if($scope.selectedTag==null || $scope.selectedTag==''){
			$scope.selectedSearchPriority = highestRating.value;
		}
	};
	
	$scope.search = function(){
	
		var searchJson = {};
		searchJson.keyword = $scope.searchKeyword;
		searchJson.tag = $scope.selectedTag;
		searchJson.searchPriority = $scope.selectedSearchPriority;
		
		
		if($scope.selectedTag==null && searchJson.searchPriority=='highest tag'){
			searchJson.searchPriority = null
		}

		// Perform the search
		$http({
			method : 'POST',
			url : '/search',
			data: searchJson
		}).then(function successCallback(response) {

			var searchResults = response.data;
			
			localStorage.setItem("searchResults", JSON.stringify(searchResults));
			localStorage.setItem("searchParams",JSON.stringify(searchJson));
			
			window.location.href = '/searchResults.html';


		}, function errorCallback(response) {
			console.log("Error performing search");
		});
		
		
	};
	


});