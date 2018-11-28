
'use strict';
 
angular.module('mainApp').factory('AppService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllApps: loadAllApps,
                getAllApps: getAllApps,
                loadMyApps: loadMyApps,
                getMyApps: getMyApps,
                createApp: createApp,
                deleteApp: deleteApp
            };
 
            return factory;
 
            function loadAllApps(tags, sort) {
                console.log('Fetching all apps');
                var _tags = tags === undefined?'[]':tags;
                var _sort = sort === undefined?1:sort;
                var deferred = $q.defer();
                $http.get(urls.APP_SERVICE_API, {params: { tags: _tags, sort: _sort }})
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
            
           function deleteApp(app_title) {
                console.log('Deleting App: ' + app_title);
                var deferred = $q.defer();
                //use ['delete'] instead of .delete because delete is a js keyword and IE8 does not parse it correctly
                $http['delete'](urls.APP_SERVICE_API + app_title)
                    .then(
                        function (response) {
                        	console.log('App deleted: ' + app_title);
                            deferred.resolve();
                        },
                        function (errResponse) {
                        	console.error('Error while deleting App: ' + errResponse.data.errorMessage);
                        	deferred.reject();
                        }
                    );
                return deferred.promise;
           }
        }
    ]);