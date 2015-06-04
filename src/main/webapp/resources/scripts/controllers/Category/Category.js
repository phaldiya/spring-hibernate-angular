'use strict';
angular.module('app').controller('CategoryCtrl', function ($scope, category, CategoryService) {
    $scope.categorys = category;
    });