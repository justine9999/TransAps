var app = angular.module('mainApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: 'http://localhost:8080/TransAps',
    APP_SERVICE_API : 'http://localhost:8080/TransAps/api/app/'
});
 
app.config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
    function($stateProvider, $urlRouterProvider, $locationProvider) {
		$locationProvider.html5Mode(true);
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
            });
        $urlRouterProvider.otherwise('/');
    }]);

app.directive("appWidget", function() {
    return {
        templateUrl : 'partials/app_widget'
    };
});

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