'use strict';

angular.module('app').factory('HospitalService', function ($resource) {
    return $resource('api/hospital/:id', {id: '@id'}, {
        update: {method: 'PUT'}
    });
});