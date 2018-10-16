
'use strict';
 
angular.module('mainApp').controller('AppController',
    ['AppService', '$scope',  function( AppService, $scope) {
 
        var self = this;
        self.getAllApps = getAllApps;
        $scope.scrollTo = function (target){};
 
        function getAllApps(){
            return AppService.getAllApps();
        }
    }
  ]);