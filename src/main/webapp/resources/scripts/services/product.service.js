'use strict';

angular.module('app').factory('ProductService', function ($resource) {
    return $resource('api/product/:id', {id: '@id'});
});