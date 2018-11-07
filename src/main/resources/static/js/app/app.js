var app = angular.module('mainApp',['ui.router','ngStorage','ngMaterial','ngMessages','ngImgCrop']);

app.run(function ($rootScope,$timeout) {
    $rootScope.$on('$viewContentLoaded', function() {
        $timeout(function() {
          componentHandler.upgradeAllRegistered();
        })
    })
});

app.constant('urls', {
    BASE: 'http://localhost:8080/TransAps',
    APP_SERVICE_API : 'http://localhost:8080/TransAps/api/app/'
});
 
app.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$mdThemingProvider',
    function($stateProvider, $urlRouterProvider, $locationProvider, $mdThemingProvider) {
		$locationProvider.html5Mode(true);
		$mdThemingProvider.theme('success-toast');
		$mdThemingProvider.theme('error-toast');
        $stateProvider
            .state('home', {
                url: '/',
                views: {
	                'view_app_list': {
	                	templateUrl: 'partials/app_list',
	                    controller:'AppController',
	                    controllerAs:'appctrl',
	                    resolve: {
	                        apps: function ($q, AppService) {
	                            console.log('Load all apps');
	                            var deferred = $q.defer();
	                            AppService.loadAllApps().then(deferred.resolve, deferred.resolve);
	                            return deferred.promise;
	                        }
	                    }
	                }
	            }
            })
        
	        .state('my-apps', {
	            url: '/myapps',
	            views: {
	                'view_app_list': {
	                	templateUrl: 'partials/my_app_list',
	    	            controller:'MyAppController',
	    	            controllerAs:'myappctrl',
	    	            resolve: {
	    	                myapps: function ($q, AppService) {
	    	                    console.log('Load my apps');
	    	                    var deferred = $q.defer();
	    	                    AppService.loadMyApps().then(deferred.resolve, deferred.resolve);
	    	                    return deferred.promise;
	    	                }
	    	            }
	                },

	                'view_app_creation_form': {
	                	abstract: true,
	                    template: '<div id="view_app_creation_form_body" ui-view="view_app_creation_form_body"></div>'
	                }
	            }
	        })
	        
	        .state('my-apps.basic', {
		        url: '',
		        views: {
		            'view_app_creation_form_body': {
		                templateUrl: 'partials/create_app_basic',
		                controller:'CreateAppFormController',
		            }
		        }
		    })
		    
		    .state('my-apps.advanced', {
		        url: '',
		        views: {
		            'view_app_creation_form_body': {
		            	templateUrl: 'partials/create_app_advanced',
		            	controller:'CreateAppFormController',
		            },
		            
		            'view_profile_image@my-apps.advanced': {
		                templateUrl: 'partials/create_app_advanced_profile_image',
		                controller:'CreateAppFormController',
		            },
		            
		            'view_content_editor@my-apps.advanced': {
		                templateUrl: 'partials/create_app_advanced_content_editor',
		                controller:'CreateAppFormController',
		            },
		        }
		    })
	        
        $urlRouterProvider.otherwise('/');
    }]);

app.directive('stickTop', function ($window) {
    var $win = angular.element($window);

    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var topClass = attrs.stickTop,
                offsetTop = element.offset().top;

            $win.on('scroll', function (e) {
                if ($win.scrollTop() >= offsetTop) {
                    element.addClass(topClass);
                } else {
                    element.removeClass(topClass);
                }
            });
        }
    };
});

app.directive("appCard", function($window) {
    return {
        templateUrl : 'partials/app_card',
        scope: false,
        link: function(scope, element, attrs) {
        	var apr = scope.apps_per_row;
        	var width = $('#apps-container').width()/apr-parseInt($(element).css('margin-right'))-3;
            element.css({
              width: width,
            });

            //on resize
            angular.element($window).bind('resize', function() {
              width = $('#apps-container').width()/apr-parseInt($(element).css('margin-right'))-3;
              element.css({
                width: width,
              });
              scope.$apply();
            })

          }
    };
});

app.directive("ratingStar", function($window) {
    return {
        templateUrl : 'partials/rating_star',
        scope: false,
        link: function(scope, element, attrs) {
        	
        	scope.getStars = function(rating) {
        	    var val = parseFloat(rating);
        	    var size = val/5*100;
        	    return size + '%';
        	  }
          }
    };
});