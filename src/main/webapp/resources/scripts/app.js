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

            .when('/category', {
                            templateUrl: '../resources/scripts/controllers/category/category.html',
                            controller: 'CategoryCtrl',
                            resolve: {
                                category: function (CategoryService, BreadcrumbService) {
                                    BreadcrumbService.push('Category List', true);
                                    return CategoryService.query().$promise;
                                }
                            }
                        })
 .when('/product', {
                                                 templateUrl: '../resources/scripts/controllers/product/product.html',
                                                 controller: 'ProductCtrl',
                                                 resolve: {
                                                     product: function (ProductService, BreadcrumbService) {
                                                         BreadcrumbService.push('Product List', true);
                                                         return ProductService.query().$promise;
                                                     }
                                                 }
                                             })

           .when('/hospital', {
                                                 templateUrl: '../resources/scripts/controllers/hospital/hospital.html',
                                                 controller: 'HospitalCtrl',
                                                 resolve: {
                                                     hospital: function (HospitalService, BreadcrumbService) {
                                                         BreadcrumbService.push('Hospital List', true);
                                                         return HospitalService.query().$promise;
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