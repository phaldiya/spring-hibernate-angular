'use strict';

angular.module('app').controller('PersonDetailsCtrl', function ($scope, person) {
    $scope.person = person;
});