
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
 
            function loadAllApps(tags, sort) {
            	console.log("parameters tags: " + tags);
            	console.log("parameters sort: " + sort);
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
            
            function createApp(app, croppedImage) {
                console.log('Creating App: ' + app.title);
                var appdata = {app : app, croppedImage : croppedImage};
                var deferred = $q.defer();
                $http.post(urls.APP_SERVICE_API, appdata)
                    .then(
                        function (response) {
                        	console.log('App created: ' + app.title);
                        	var result = {creation_time : app.creation_time, iconurl : response.headers('iconurl')};
                            deferred.resolve(result);
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