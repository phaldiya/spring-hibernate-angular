'use strict';

angular.module('app').factory('InterceptorService', function ($rootScope, $q, $window, $location, APP_URL, ErrorMessageService) {
    return {
        request: function (config) {
            config.headers = config.headers || {};
            if ($window.sessionStorage.token) {
                config.headers['X-AUTH-TOKEN'] = $window.sessionStorage.token;
            }
            return config;
        },

        responseError: function (response) {
            if (response.status === 401) {
                $window.sessionStorage.originalUrl = $location.url();
                $window.location = APP_URL;
            }
            else if (response.status === 403) {
                $location.url('/deny');
            }
            else {
                ErrorMessageService.addError(response);
            }

            return $q.reject(response);

        }
    };
});
