
'use strict';
 
app.controller('UserLoginFormController', ['$scope', 'UserService', function($scope, UserService) {
 
		$scope.userdata : {
				username: '',
				password: '',
		}};
		
		$scope.cancel = function() {
			$mdDialog.cancel();
	    };

	    $scope.submit = function() {
	    	console.log("login use username: " + $scope.userdata.username + " password: " + $scope.userdata.password);
	    	UserService.logIn(userdata).then(
      	    	function() {
      	    		$mdDialog.hide();
            	}, function() {
            		
            });
	    };
    }
]);