'use strict';
angular.module('app').controller('CategoryCtrl', function ($scope, category, CategoryService) {
    $scope.categorys = category;
    $scope.addnewvisible=true;
    $scope.formvisible=false;

    $scope.addcategory=function()
    {
        $scope.formvisible=true;
        $scope.addnewvisible=false;
    };

    $scope.savenew=function()
    {
        if($scope.newcategory.id == undefined)
        {
        CategoryService.save($scope.newcategory,function(response){
        $scope.categorys.push(response);
        $scope.clear();
        });
        }
        else
        {
        CategoryService.update($scope.newcategory,function(response)
        {
         angular.copy(response, $scope.selectedCategory);
        $scope.clear();
        });
        }

    };

    $scope.clear = function(){
        $scope.newcategory={};
        $scope.addnewvisible=true;
        $scope.formvisible=false;
    };

    $scope.delete = function(category){
        CategoryService.delete(category,function(response){
            $scope.categorys.splice($scope.categorys.indexOf(category) , 1);
        });
    };

   $scope.edit=function(category)
   {
    $scope.formvisible=true;
           $scope.addnewvisible=false;
    $scope.newcategory=category;

   };
    });