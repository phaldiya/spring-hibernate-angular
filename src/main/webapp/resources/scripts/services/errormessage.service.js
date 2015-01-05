'use strict';

angular.module('app').factory('ErrorMessageService', function () {
    return {
        errors: [],

        // Is the current user authenticated?
        addError: function (error) {
            this.errors.push(error);
        },

        clearErrors: function () {
            this.errors.length = 0;
        }
    };
});
