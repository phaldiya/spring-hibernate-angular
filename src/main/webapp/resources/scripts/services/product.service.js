'use strict';

angular.module('app').factory('ProductService', function ($resource) {
    return $resource('api/product/:productId', {productId: '@productId'});
});