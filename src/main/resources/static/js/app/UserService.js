
'use strict';
 
angular.module('mainApp').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
            		logIn: logIn,
            };
 
            return factory;
 
            function logIn() {
                console.log('Login user');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API + '/login/')
                    .then(
                        function (response) {
                            console.log('User login successfully');
                            $localStorage.token = response.data;
                            deferred.resolve();
                        },
                        function (errResponse) {
                            console.error('User login failed');
                            deferred.reject();
                        }
                    );
                return deferred.promise;
            }
        }
    ]);