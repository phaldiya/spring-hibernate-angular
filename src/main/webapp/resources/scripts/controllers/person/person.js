'use strict';

angular.module('app').controller('PersonCtrl', function ($scope, persons) {
    $scope.persons = persons;
});