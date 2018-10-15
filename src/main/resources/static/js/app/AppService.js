
'use strict';
 
angular.module('mainApp').factory('AppService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllApps: loadAllApps,
                getAllApps: getAllApps,
            };
 
            return factory;
 
            function loadAllApps() {
                console.log('Fetching all apps');
                var deferred = $q.defer();
                $http.get(urls.APP_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all apps');
                            $localStorage.apps = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading apps');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllApps(){
                return $localStorage.apps;
            }
        }
    ]);