// Define the `phonecatApp` module
var app = angular.module('app', ['ngAnimate','ui.bootstrap']);

// Define the `PhoneListController` controller on the `phonecatApp` module
app.controller('ResourceController', function ResourceController($scope,$rootScope, $http,$uibModal) {

	var resource = localStorage.getItem("resource");
	var user = localStorage.getItem("user");
	
	if(user==null){
		window.location.href="/index.html";
	}

	var resourceRequest = {};
	resourceRequest.resourceName = resource;
	resourceRequest.username = user;
	
	$scope.reviewSubmitted = false;

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
	
	$rootScope.haveUserLikes = false;
	
	//Get the user's likes
	$http({
		method : 'POST',
		url : '/getUserLikes',
		data : user
	}).then(function successCallback(response) {

		$scope.userLikes = response.data;
		
		$rootScope.haveUserLikes = true;

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
	
	$scope.openAddReviewModal = function(){
		
		var reviewModalInstance = $uibModal.open({
		      animation: false,
		      /*ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',*/
		      templateUrl: 'addReviewModal.html',
		      controller: 'AddReviewModalCtrl',
		      resolve: {
		        tags: function () {
		          return $rootScope.tags;
		        }
		      }
		    })
		    
		    reviewModalInstance.result.then(function (reviewContent) {
		       var reviewSubmission = reviewContent;
		       reviewSubmission.username = user;
		       reviewSubmission.resource = resource;
		        
		        //Submit the review to the backend
		        console.log(reviewSubmission);
		        
		        $scope.reviewSubmitted = true;
		        
				$http({
					method : 'POST',
					url : '/resourceDetails/submitReview',
					data : reviewSubmission
				}).then(function successCallback(response) {

					var review = response.data;
					$scope.resourceData.reviews.push(review);
	
					//disable the Add Review button
					$scope.resourceData.userRating = review.rating;

				}, function errorCallback(response) {
					console.log("Error submitting review");
				});
		        
		      }, function () {
		    	  
		    	  //Enter into here when user clicks
		    	  //cancel on modal
		    	  
		    	  
		        //$log.info('modal-component dismissed at: ' + new Date());
		      });
		    
	};
	
	
	$scope.openAddQuestionModal = function(){
		
		var questionModalInstance = $uibModal.open({
		      animation: false,
		      /*ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',*/
		      templateUrl: 'addQuestionModal.html',
		      controller: 'AddQuestionModalCtrl'
		    })
		    
		    questionModalInstance.result.then(function (question) {
		       var questionSubmission = {};
		       questionSubmission.username = user;
		       questionSubmission.comment = question;
		       questionSubmission.resource = resource;
		        
		        //Submit the review to the backend
		        console.log(questionSubmission);
		        
		        
				$http({
					method : 'POST',
					url : '/resourceDetails/submitQuestion',
					data : questionSubmission
				}).then(function successCallback(response) {

					var question = response.data;
					$scope.resourceData.questions.push(question);


				}, function errorCallback(response) {
					console.log("Error submitting question");
				});
		        
		      }, function () {
		    	  
		    	  
		      });
		    
	};
	
	
	$scope.openViewRepliesModal = function(questionData){
		
		var replyModalInstance = $uibModal.open({
		      animation: false,
		      /*ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',*/
		      templateUrl: 'viewRpliesModal.html',
		      controller: 'ViewRepliesModalCtrl',
		      resolve: {
			        question: function () {
			          return questionData;
			        },
			        userLikes :function () {
			          return $scope.userLikes;
			        },
			        user: function () {
			          return user;
			        }
			      }
		    })
		    
		    replyModalInstance.result.then(function (reply) {
		       var replySubmission = {};
		       replySubmission.username = user;
		       replySubmission.comment = reply;
		       replySubmission.resource = resource;
		       replySubmission.questionId = questionData.id;
		        
		        //Submit the review to the backend
		        console.log(replySubmission);
		        
		        
				$http({
					method : 'POST',
					url : '/resourceDetails/submitReply',
					data : replySubmission
				}).then(function successCallback(response) {

					var replyData = response.data;
					questionData.replies.push(replyData);


				}, function errorCallback(response) {
					console.log("Error submitting reply");
				});
		        
		      }, function () {
		    	  
		    	  
		      });
		    
	};

});

app.controller('AddReviewModalCtrl', function AddReviewModalCtrl($scope, $uibModalInstance,tags) {
	
	console.log("inside AddReviewModalCtrl");
	
	$scope.tags = tags;
	
	$scope.review = {}; 
	$scope.maxRating = 5;
	
	$scope.hoveringOverRating = function(value) {
	    $scope.overStar = value;
	    $scope.percent = 100 * (value / $scope.max);
	  };
	
	$scope.save = function () {
		if($scope.existingTag!=null){
			$scope.review.tag = $scope.existingTag;
		}
		else if($scope.newTag!=null&&$scope.newTag!=''){
			$scope.review.tag = $scope.newTag;
		}
	    $uibModalInstance.close($scope.review);
	  };
	  
	  $scope.cancel = function () {
		    $uibModalInstance.dismiss('cancel');
		};
	

});

app.controller('AddQuestionModalCtrl', function AddQuestionModalCtrl($scope, $uibModalInstance) {
	
	console.log("inside AddQuestionModalCtrl");
	

	$scope.save = function () {
		
	    $uibModalInstance.close($scope.question);
	  };
	  
	  $scope.cancel = function () {
		    $uibModalInstance.dismiss('cancel');
		};
	

});

app.controller('ViewRepliesModalCtrl', function ViewRepliesModalCtrl($scope, $rootScope,$uibModalInstance,$http, question,userLikes,user) {
	
	console.log("inside ViewRepliesModalCtrl");
	
	$scope.haveUserLikes = $rootScope.haveUserLikes;
	
	$scope.question = question;
	
	$scope.userLikes = userLikes;
	
	$scope.user = user;
	
	$scope.toggleLikeButtonClass = function(question) {
		
		var contentId = question.id;

		if($scope.userLikes[contentId]!=null){
			question.liked = true;
			return 'btn btn-primary';
		}
		else{
			question.liked = false;
			return 'btn btn-default';
		}

	}
	
	$scope.likeReply = function(reply){
		
		reply.requestInProgress = true;
		var likeContent = {};
		likeContent.contentType = 'reply';
		likeContent.contentId = reply.id;
		likeContent.userLikeId = $scope.userLikes[reply.id]; 
		likeContent.liked = !reply.liked;
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
				$scope.userLikes[reply.id] = userLikeId;
				reply.likes++;
			}
			//Delete the user like, since that means it
			//was un-liked
			else{
				$scope.userLikes[reply.id] = null;
				reply.likes--;
			}

			
			reply.requestInProgress = false;

		}, function errorCallback(response) {
			console.log("Error liking reply ");
		});
	}
	
	$scope.toggleAcceptance = function(reply){
		
		reply.acceptRequestInProgress = true;
		var acceptReplyRequest = {};
		acceptReplyRequest.replyId = reply.id;
		acceptReplyRequest.accepted = !reply.accepted;
		
		//Like or unlike a content
		$http({
			method : 'POST',
			url : '/resourceDetails/acceptReply',
			data : acceptReplyRequest
		}).then(function successCallback(response) {

			//Toggle the accepted field
			reply.accepted = !reply.accepted;

			
			reply.acceptRequestInProgress = false;

		}, function errorCallback(response) {
			console.log("Error toggling reply acceptance ");
		});
	};
	
	$scope.toggleAcceptButtonClass = function(reply) {
		
		if(reply.accepted){
			
			return 'btn btn-danger';
		}
		else{
			
			return 'btn btn-success';
		}

	};

	$scope.submitReply = function () {
		
	    $uibModalInstance.close($scope.reply);
	  };
	  
	  $scope.close = function () {
		    $uibModalInstance.dismiss('cancel');
		};
	

});


//Handles the logic for searching for results
app.controller('SearchController', function SearchController($scope, $http, $rootScope) {


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
		$rootScope.tags = response.data;

	}, function errorCallback(response) {
		console.log("Error getting tags for search");
	});
	
	$scope.logout = function(){
		
		localStorage.removeItem("user");
		window.location.href = '/index.html';
		

	};
	
	$scope.search = function(){
		
		
		
		var searchJson = {};
		searchJson.keyword = $scope.searchKeyword;
		searchJson.tag = $scope.selectedTag;
		
		if($scope.selectedSearchPriority!=null){
			searchJson.searchPriority = $scope.selectedSearchPriority.value;
		}
		
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
			
			window.location.href = '/searchResults.html';


		}, function errorCallback(response) {
			console.log("Error performing search");
		});
		
		
	};
	


});