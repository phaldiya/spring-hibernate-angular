'use strict';


angular.module('app').filter('camelcase', ['$parse', function () {
    return function (input) {
        input = input ? input : '';
        return input.replace(/_/g, ' ').replace(/(?!PI|CHO)\b(\w)(\w*)/g, function(skip, $1, $2) { return $1.toUpperCase() + $2.toLowerCase();});
    };
}]);