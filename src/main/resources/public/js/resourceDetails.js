// Define the `phonecatApp` module
var app = angular.module('app', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('ResourceController', function LoginController($scope, $http) {

	var resource = localStorage.getItem("resource");
	var user = localStorage.getItem("user");

	var resourceRequest = {};
	resourceRequest.resourceName = resource;
	resourceRequest.username = user;

	// Get the resource details
	$http({
		method : 'POST',
		url : '/resourceDetails/getDetails',
		data : resourceRequest
	}).then(function successCallback(response) {

		$scope.resourceData = response.data;

	}, function errorCallback(response) {
		console.log("Error getting resource details");
	});
	
	$scope.haveUserLikes = false;
	
	//Get the user's likes
	$http({
		method : 'POST',
		url : '/getUserLikes',
		data : user
	}).then(function successCallback(response) {

		$scope.userLikes = response.data;
		
		$scope.haveUserLikes = true;

	}, function errorCallback(response) {
		console.log("Error getting user likes");
	});
	

	$scope.toggleLikeButtonClass = function(content) {
		
		var contentId = content.id;

		if($scope.userLikes[contentId]!=null){
			content.liked = true;
			return 'btn btn-primary';
		}
		else{
			content.liked = false;
			return 'btn btn-default';
		}

	}
	
	$scope.likeContent = function(content,contentType){
		
		content.requestInProgress = true;
		var likeContent = {};
		likeContent.contentType = contentType;
		likeContent.contentId = content.id;
		likeContent.userLikeId = $scope.userLikes[content.id]; 
		likeContent.liked = !content.liked;
		likeContent.username = user;
		
		//Like or unlike a content
		$http({
			method : 'POST',
			url : '/resourceDetails/likeContent',
			data : likeContent
		}).then(function successCallback(response) {

			var userLikeId = response.data.userLikeId;
			
			//This means the content was liked
			if(userLikeId!=null){
				$scope.userLikes[content.id] = userLikeId;
				content.likes++;
			}
			//Delete the user like, since that means it
			//was un-liked
			else{
				$scope.userLikes[content.id] = null;
				content.likes--;
			}
			
			
			
			content.requestInProgress = false;

		}, function errorCallback(response) {
			console.log("Error liking content type: " + contentType);
		});
	}

});