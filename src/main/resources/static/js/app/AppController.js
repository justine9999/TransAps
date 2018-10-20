
'use strict';
 
app.controller('AppController',
    ['AppService', '$scope',  function( AppService, $scope) {
 
        var self = this;
        self.getAllApps = getAllApps;
        $scope.apps_per_row = 4;
        self.appcnt = 30;
        $scope.scrollTo = function (target){};
        $scope.appcardwidth = $('#apps-container').css("width");
        
 
        function getAllApps(){
        	var obj = AppService.getAllApps();
        	console.log('app+cnt:' + $scope.app_cnt);
        	return obj;
        }
        
        function highlight(){
        	console.log('highlight');
        }
        
        function fade(){
        	console.log('fade');
        }
    }
]);