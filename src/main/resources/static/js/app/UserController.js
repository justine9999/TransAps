
'use strict';
 
angular.module('mainApp').controller('UserController',
    ['UserService', '$scope', '$mdDialog', function(UserService, $scope, $mdDialog) {
 
        var self = this;
        this.showUserLoginModal = showUserLoginModal;
 
        function showUserLoginModal(event) {
        	$mdDialog.show({
        		controller: 'UserLoginFormController',
        	    templateUrl: 'partials/user_login',
        	    parent: angular.element(document.body),
        	    targetEvent: event,
        	    clickOutsideToClose: false,
        	    fullscreen: $scope.customFullscreen
        	})
        	.then(function() {
        		console.log("user login successfully and form hide");
        	}, function() {
        		console.log("user login cancel");
        	});
        }
    }
]);