'use strict';

angular.module('app').factory('CategoryService', function ($resource) {
   return $resource('api/category/:id', {id: '@id'}, {
           update: {method: 'PUT'},
           dropdown: { method: 'GET', url: 'api/category/dropdown', isArray: true }
       });
});