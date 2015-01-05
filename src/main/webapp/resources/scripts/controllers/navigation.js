'use strict';

angular.module('app').controller('NavigationCtrl', function ($scope, $location, BreadcrumbService, ErrorMessageService) {
    $scope.breadcrumb = BreadcrumbService.breadcrumb;
    $scope.navigation = [
        {label: 'Home', url: '/'},
        {label: 'Person', url: '/person'},
        /*{label: 'Chemical', url: '/chemical'},*/
        {label: 'Help', url: '/help'}
    ];

    $scope.selectActiveTab = function (tab, toggleSidebar) {
        $scope.activeTab = tab;
        if(toggleSidebar) {
            $scope.sideMenu = !$scope.sideMenu;
        }
        $location.url(tab.url);
    };

    $scope.$watch(function () { return $location.url(); }, function () {
        ErrorMessageService.clearErrors();
    });

});