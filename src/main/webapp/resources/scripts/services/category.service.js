'use strict';

angular.module('app').factory('CategoryService', function ($resource) {
    return $resource('api/category/:id', {id: '@id'});
});