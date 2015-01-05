'use strict';

angular.module('app').controller('HelpCtrl', function ($scope) {

    $scope.current = null;

    $scope.toggle = function (section) {
        if ($scope.current === section) {
            $scope.current = null;
        } else {
            $scope.current = section;
        }
    };
});