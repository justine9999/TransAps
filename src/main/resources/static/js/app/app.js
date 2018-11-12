var app = angular.module('mainApp',['ui.router','ngStorage','ngMaterial','ngMessages','ngImgCrop','ngSanitize']);

app.run(function ($rootScope,$timeout) {
    $rootScope.$on('$viewContentLoaded', function() {
        $timeout(function() {
          componentHandler.upgradeAllRegistered();
        })
    });
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
	        
	        .state('my-apps.basicinfo', {
		        url: '',
		        views: {
		            'view_app_creation_form_body': {
		                templateUrl: 'partials/create_app_basicinfo',
		                controller:'CreateAppFormController',
		            }
		        }
		    })
		    
		    .state('my-apps.appicon', {
		        url: '',
		        views: {
		            'view_app_creation_form_body': {
		                templateUrl: 'partials/create_app_appicon',
		                controller:'CreateAppFormController',
		            }
		        }
		    })
		    
		    .state('my-apps.appdetails', {
		        url: '',
		        views: {
		            'view_app_creation_form_body': {
		                templateUrl: 'partials/create_app_appdetails',
		                controller:'CreateAppFormController',
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

app.directive("ratingStar", function($window) {
    return {
        templateUrl : 'partials/rating_star',
        scope: {
        	rate: "@"
        },
        link: function(scope, element, attrs) {
        	scope.getStars = function() {
        	    var val = parseFloat(scope.rate);
        	    var size = val/5*100;
        	    return size + '%';
        	}
        }
    };
});

app.directive('addTagEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.addTagEnter);
                });

                event.preventDefault();
            }
        });
    };
});

app.directive('stateChangeStyler', ['$state', '$rootScope', function($state, $rootScope) {
    return function (scope, element, attrs) {
    	var sname = attrs.uiSref;
    	var ckbox = $(element).find('label')[0];
        $rootScope.$on('$stateChangeStart', function() {
        	$rootScope.preloader = true;
        });
        
        $rootScope.$on('$stateChangeSuccess', function() {
        	$rootScope.preloader = false;
            if(sname === $state.current.name || sname === $state.$current.parent.name){
            	element.addClass('state-select-tr');
            	ckbox.MaterialCheckbox.check();
            }else{
            	element.removeClass('state-select-tr');
            	ckbox.MaterialCheckbox.uncheck();
            }
        });
    };
}]);


app.directive('quillEditor', function($compile) {
    return {
      restrict: 'E',
      link: function($scope, $element) {
          var template= '<div id="editor"></div>';
          var linkFunc = $compile(template);
          var content = linkFunc($scope);
          $element.append(content);
        
          //a work around for when you cancel md-dialog with the 'editor' tab active
          if(document.querySelector('#editor')){
        	  var quill = new Quill('#editor', {
                  modules: {
                	imageResize: {},
                	VideoResize: {},
                    toolbar: [
                      [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                      [{ 'size': ['small', false, 'large', 'huge'] }],
                      ['bold', 'italic', 'underline', 'strike', 'link'],
                      [{ 'color': [] }, { 'background': [] }],
                      [{ 'font': [] }],
                      [{ 'align': [] }],
                      ['clean'],
                      ['blockquote', 'code-block'],
                      ['video', 'image'],
                      [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                      [{ 'script': 'sub'}, { 'script': 'super' }],
                      [{ 'indent': '-1'}, { 'indent': '+1' }],
                    ]
                  },
                  placeholder: 'There is not yet any detailed information for the app...',
                  theme: 'snow'
                });
              
                quill.on('text-change', function() {
                  var justHtml = quill.root.innerHTML;
                              
                  $scope.$apply(function() {
                    $scope.quillDataHTML = justHtml;
                  });
              });
          }
          
      },
    };
  });