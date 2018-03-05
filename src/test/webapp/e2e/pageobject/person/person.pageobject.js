'use strict';
global.PersonPage = {
    get: function () {
        Helper.clickHomeLink();
        element(by.linkText('Manage Persons')).click();
        return this;
    }
};