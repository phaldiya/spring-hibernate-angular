'use strict';

angular.module('app').controller('ProductCtrl', function ($scope, product, ProductService) {
    $scope.products = product;
});