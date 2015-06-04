'use strict';

angular.module('app').controller('HospitalCtrl', function ($scope, hospital, HospitalService) {
    $scope.hospitals = hospital;
    $scope.show=false;
    $scope.addshow=true;
    $scope.selectedHospital = null;

    $scope.edit=function(hospital){
        $scope.selectedHospital = hospital;
        angular.copy(hospital, $scope.newhospital);
        $scope.addNew();
    };

    $scope.delete=function(hospital){
       HospitalService.delete(hospital,function(){
            $scope.hospitals.splice($scope.hospitals.indexOf(hospital) , 1);
       });
    };

    $scope.addNew=function(){
        $scope.show=! $scope.show;
        $scope.addshow=false;
    };

    $scope.save=function(){
        if($scope.newhospital.id === undefined){
        console.log('calling save');
        console.log($scope.newhospital);
            HospitalService.save($scope.newhospital,function(response){
                $scope.hospitals.push(response);
                $scope.clearForm();
            });
        } else {
            console.log('calling update');
            console.log($scope.newhospital);
            HospitalService.update($scope.newhospital,function(response){
                angular.copy(response, $scope.selectedHospital);
                console.log('calling update1');
                $scope.clearForm();
            });
        }
    };

    $scope.clearForm=function()
    {
       $scope.show=false;
       $scope.hospitalForm.$submitted = false;
        $scope.newhospital={};
      $scope.addshow=true;
    };
});