'use strict';

global.Helper = {
    clickHomeLink: function() {
        element(by.css('.glyphicon-home')).click();
    },

    clickBreadcrumbLink: function(link) {
        element(by.css('.breadcrumb')).element(by.cssContainingText('a', link)).click();
    }
};