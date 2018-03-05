'use strict';

angular.module('app').factory('PersonService', function ($resource) {
    return $resource('api/person/:id', {id: '@id'}, {});
});