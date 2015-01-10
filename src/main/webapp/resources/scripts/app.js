'use strict';

angular.module('app', ['ngResource', 'ngRoute', 'ngCookies', 'ngAnimate', 'ngTouch', 'ngAria', 'ui.bootstrap'])
    .config(function ($httpProvider, $routeProvider) {
        $httpProvider.interceptors.push('InterceptorService');
        $routeProvider
            .when('/', {
                templateUrl: '../resources/scripts/controllers/home.html',
                controller: 'HomeCtrl',
                resolve: {
                    breadcrumb: function(BreadcrumbService) {
                            BreadcrumbService.reset();
                    }
                }
            })
            .when('/person', {
                templateUrl: '../resources/scripts/controllers/person/list.html',
                controller: 'PersonCtrl',
                resolve: {
                    persons: function (PersonService, BreadcrumbService) {
                        BreadcrumbService.push('Persons List', true);
                        return PersonService.query().$promise;
                    }
                }
            })
            .when('/person/:id', {
                templateUrl: '../resources/scripts/controllers/person/details.html',
                controller: 'PersonDetailsCtrl',
                resolve: {
                    person: function($route, PersonService, BreadcrumbService) {
                            BreadcrumbService.push('Person - '+ $route.current.params.id);
                            return PersonService.get({id: $route.current.params.id}).$promise;
                    }
                },
                reloadOnSearch: false
            })
            .when('/help', {
                templateUrl: '../resources/scripts/controllers/help/help.html',
                controller: 'HelpCtrl',
                resolve: {
                    breadcrumb: function(BreadcrumbService) {
                        BreadcrumbService.push('Help', true);
                    }
                }
            })
            .when('/deny', {
                templateUrl: '../resources/scripts/controllers/deny.html'
            })
            .otherwise({
                redirectTo: '/deny'
            });
    });