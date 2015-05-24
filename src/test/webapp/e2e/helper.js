'use strict';
var http = require('http');

global.Helper = {
    clickHomeLink: function() {
        element(by.css('.glyphicon-home')).click();
    },

    clickBreadcrumbLink: function(link) {
        element(by.css('.breadcrumb')).element(by.cssContainingText('a', link)).click();
    }
};

// reset h2 database before each test
beforeEach(function (done) {
    var url = browser.baseUrl.replace('secure/', '') + 'devtool/e2e/prepare';
    http.get(url, function () { done(); }).on('error', function (e) {
        done.fail('Error resetting data: ' + e.message);
    });
});
