'use strict';
global.PersonPage = {
    get: function (reload) {
        if (reload) {
            browser.get('#/person');
        } else {
            Helper.clickHomeLink();
        }
        return this;
    }
};