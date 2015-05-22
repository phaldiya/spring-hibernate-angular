// Reference Configuration File
// https://github.com/angular/protractor/blob/master/docs/referenceConf.js
var SpecReporter = require('jasmine-spec-reporter');

exports.config = {

    // ---------------------------------------------------------------------------
    // ----- Connect to Browser Drivers ------------------------------------------
    // ---------------------------------------------------------------------------

    // The location of the standalone Selenium Server jar file, relative
    // to the location of this config. If no other method of starting Selenium
    // Server is found, this will default to
    // node_modules/protractor/selenium/selenium-server...
    //seleniumServerJar: './node_modules/protractor/selenium/selenium-server-standalone-2.44.0.jar',

    // If sauceUser and sauceKey are specified, seleniumServerJar will be ignored.
    // The tests will be run remotely using Sauce Labs.
    //sauceUser: 'teampurple',
    //sauceKey: 'e3acae46-9d4e-4d75-a69c-d874934b9d56',



    // ---------------------------------------------------------------------------
    // ----- Set up browsers -----------------------------------------------------
    // ---------------------------------------------------------------------------
    // For a full list of available capabilities, see
    // https://code.google.com/p/selenium/wiki/DesiredCapabilities and
    // https://code.google.com/p/selenium/source/browse/javascript/webdriver/capabilities.js
    // If you would like to run more than one instance of WebDriver on the same
    // tests, use multiCapabilities, which takes an array of capabilities.
    // If this is specified, capabilities will be ignored.
    // http://peter.sh/experiments/chromium-command-line-switches/
    multiCapabilities: [{
        'browserName': 'chrome',
        'chromeOptions': {
            args: ['--test-type',
                '--disable-extensions',
                '--disable-cache',
                '--disable-offline-load-stale-cache',
                '--disk-cache-size=0',
                '--v8-cache-options=off'
            ]
        }
    }],

    // ---------------------------------------------------------------------------
    // ----- Global test information ---------------------------------------------
    // ---------------------------------------------------------------------------

    // A base URL for your application under test. Calls to protractor.get()
    // with relative paths will be prepended with this.
    baseUrl: 'http://localhost:9090/spring-hibernate-angular/secure/',

    // A callback function called once protractor is ready and available, and
    // before the specs are executed
    // You can specify a file containing code to run by setting onPrepare to
    // the filename string.
    onPrepare: function() {
        // At this point, global 'protractor' object will be set up, and jasmine
        // add jasmine spec reporter
        // reference: https://www.npmjs.com/package/jasmine-spec-reporter
        jasmine.getEnv().addReporter(new SpecReporter({ displayStacktrace: 'summary', displayFailuresSummary: true }));

        browser.driver.manage().window().setSize(1200, 1024);
        browser.driver.get(exports.config.baseUrl.replace('secure/', ''));
        browser.executeAsyncScript(function(callback) {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'devtool/token/user', true);
            xhr.onload = function() { window.sessionStorage.token = JSON.parse(xhr.responseText).token; callback(); };
            xhr.send();
        }).then(function() { browser.get('#/'); });
    },


    // ---------------------------------------------------------------------------
    // ----- The test framework --------------------------------------------------
    // ---------------------------------------------------------------------------
    // Test framework to use.
    framework: 'jasmine2',

    // Options to be passed to Jasmine-node.
    jasmineNodeOpts: {
        showColors: true,               // If true, print colors to the terminal.
        defaultTimeoutInterval: 50000,   // Default time to wait in ms before a test fails.
        print: function() {}           // Function called to print jasmine results.
        // grep: 'pattern',                // If set, only execute specs whose names match the pattern, which is internally compiled to a RegExp.
        // invertGrep: false               // Inverts 'grep' matches
    }
};
