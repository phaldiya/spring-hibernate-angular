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

// reset h2 database after testing (only called once at the end)
/*aftereAll(function (done) {
    resetData(done);
});*/

// reset h2 database before each test
beforeEach(function (done) {
    resetData(done);
});

function resetData(callback) {
    var url = browser.baseUrl.replace('secure/', '') + 'devtool/e2e/prepare';
    http.get(url, function () { callback(); }).on('error', function (e) {
        callback.fail('Error resetting data: ' + e.message);
    });
}