'use strict';

angular.module('app').controller('NavigationCtrl', function ($scope, $http, $window, $location, BreadcrumbService, ErrorMessageService) {
    $scope.authenticated = false;
    $scope.breadcrumb = BreadcrumbService.breadcrumb;
    $scope.navigation = [
        {label: 'Home', url: '/'},
        {label: 'Person', url: '/person'},
        {label: 'Hospital', url: '/hospital'},
        {label: 'Category', url: '/category'},
        {label: 'Product', url: '/product'},
        {label: 'Help', url: '/help'}
    ];

    $scope.user = null;

    $scope.init = function () {
        $http.get('api/users/current').success(function (user) {
            if(user.username !== 'anonymousUser'){
                $scope.user = user;
            }
        });
    };

    $scope.logout = function () {
        $scope.username = undefined;
        $scope.password = undefined;

        delete $window.sessionStorage.token;
        $scope.authenticated = false;
        $window.location.href = '../';
    };

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