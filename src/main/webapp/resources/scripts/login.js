'use strict';

angular.module('app', [])
    .config(function() {})
    .controller('LoginController', function ($scope, $http, $window) {
        $scope.login = function () {
            $scope.authenticated = false;
            $scope.hasError = false;

            $http.post('api/login', { username: $scope.username, password: $scope.password }).success(function (result, status, headers) {
                if (headers('X-AUTH-TOKEN') !== null) {
                    $scope.authenticated = true;
                    $window.sessionStorage.token = headers('X-AUTH-TOKEN');
                    $window.location.href = 'secure/';
                } else {
                    $scope.hasError = true;
                    $scope.message = "Access Forbidden, try again";
                }
            }). error(function(data, status) {
                $scope.hasError = true;

                if (status === 401) {
                    $scope.message = "Invalid username or password, try again";
                }
                if (status === 403) {
                    $scope.message = "Access Forbidden, try again";
                }
            });
        };

        $scope.spoof = function (user) {
            $scope.authenticated = false;
            $scope.hasError = false;

            $http.post('api/spoof',{spoof: user.username}).success(function (result, status, headers) {

                if (headers('X-AUTH-TOKEN') !== null) {
                    $scope.authenticated = true;
                    $window.sessionStorage.token = headers('X-AUTH-TOKEN');
                    $window.location.href = 'secure/';
                } else {
                    $scope.hasError = true;
                    $scope.message = "Access Forbidden, try again";
                }

            }). error(function(data, status) {
                $scope.hasError = true;

                if (status === 403) {
                    $scope.message = "Access Forbidden, try again";
                }
            });
        };
    });