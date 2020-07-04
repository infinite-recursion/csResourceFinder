// Define the `phonecatApp` module
var app = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('SearchResultsController',
		function SearchResultsController($scope, $http, $rootScope) {

	
			//Get the search results if searched
			//on a different page
			$scope.results = JSON.parse(localStorage.getItem("searchResults"));
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
			
			/**
			 * Below is the logic for searching for results
			 */
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
			
			$scope.search = function(){
				
				$scope.results.length = 0;
				
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
					//Refresh the search results
					/*for(var i = 0; i < searchResults.length; i++){
						
						$scope.results.push(searchResults[i]);
					}*/
					
					//$rootScope.$apply();
					
					localStorage.setItem("searchResults", JSON.stringify(searchResults));
					
					window.location.href = '/searchResults.html';


				}, function errorCallback(response) {
					console.log("Error performing search");
				});
				
				
			};

		});

