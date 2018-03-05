'use strict';

angular.module('app').factory('UtilService', function ($location) {
    return {
        resetSearchParams: function() {
            _.forEach(_.keys($location.search()), function(key) {
                $location.search(key, null);
            });
        }
    };
});