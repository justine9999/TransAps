var app = angular.module('mainApp',['ui.router','ngStorage','ngMaterial','ngMessages']);

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
            })
        
	        .state('my-apps', {
	            url: '/myapps',
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