
'use strict';
 
angular.module('mainApp').factory('AppService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllApps: loadAllApps,
                getAllApps: getAllApps,
                loadMyApps: loadMyApps,
                getMyApps: getMyApps,
                createApp: createApp
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
            
            function loadMyApps() {
                console.log('Fetching my apps');
                var deferred = $q.defer();
                $http.get(urls.APP_SERVICE_API + '/myapps/')
                    .then(
                        function (response) {
                            console.log('Fetched successfully my apps');
                            $localStorage.myapps = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading my apps');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllApps(){
                return $localStorage.apps;
            }
            
            function getMyApps(){
                return $localStorage.myapps;
            }
            
            function createApp(app) {
                console.log('Creating App: ' + app.title);
                var deferred = $q.defer();
                $http.post(urls.APP_SERVICE_API, app)
                    .then(
                        function (response) {
                        	console.log('App created: ' + app.title);
                            deferred.resolve(app.creation_time);
                        },
                        function (errResponse) {
                        	console.error('Error while creating App: ' + errResponse.data.errorMessage);
                        	deferred.reject(app);
                        }
                    );
                return deferred.promise;
           }
        }
    ]);