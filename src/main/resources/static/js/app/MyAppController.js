
'use strict';
 
app.controller('MyAppController',
    ['AppService', '$scope',  function( AppService, $scope) {
 
        var self = this;
        self.getMyApps = getMyApps;        
 
        function getMyApps(){
        	var obj = AppService.getMyApps();
        	console.log("ctrl: " + obj.length);
        	return obj;
        }
    }
]);