'use strict';

angular.module('app').controller('ProductCtrl', function ($scope, product, categories, ProductService) {
    $scope.products = product;
    $scope.categories = categories;
    $scope.addvisible=true;
    $scope.formisible=false;

    $scope.delete=function(product){
        ProductService.delete(product,function(response){
        $scope.products.splice($scope.products.indexOf(product),1);
        });
    }

    $scope.addnewproduct=function(){
         $scope.addvisible=false;
            $scope.formisible=true;
    }
});

