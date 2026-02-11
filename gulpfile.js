'use strict';

var gulp = require('gulp');
var del = require('del');
var concat = require('gulp-concat');
var cleanCSS = require('gulp-clean-css');
var htmlify = require('gulp-angular-htmlify');
var inject = require("gulp-inject");
var jshint = require('gulp-jshint');
var less = require('gulp-less');
var merge = require('merge-stream');
var protractor = require("gulp-protractor").protractor;
var rename = require("gulp-rename");
var templateCache = require('gulp-angular-templatecache');
var Server = require('karma').Server;

var karmaConfig = {
    browsers: ['ChromeHeadless'],
    frameworks: ['jasmine', 'sinon'],
    reporters: ['spec'],
    files: [
        'src/main/webapp/resources/lib/angular.js',
        'bower_components/angular-mocks/angular-mocks.js',
        'src/main/webapp/resources/lib/angular-*.js',
        'src/main/webapp/resources/lib/angulartics.min.js',
        'src/main/webapp/resources/lib/angulartics-ga.min.js',
        'src/main/webapp/resources/lib/ui-bootstrap.js',
        'src/main/webapp/resources/lib/ui-bootstrap.js',
        'src/main/webapp/resources/lib/lodash.js',
        'src/main/webapp/resources/scripts/app.js',
//        'src/main/webapp/resources/scripts/**/*.js',
        'src/main/webapp/resources/scripts/controllers/**/*.js',
        'src/main/webapp/resources/scripts/services/*.js',
        'src/main/webapp/resources/scripts/directives/*.js',
        'src/main/webapp/resources/scripts/filters/*.js',
        'src/test/webapp/spec/helpers.spec.js',
        'src/test/webapp/spec/**/*.spec.js'
    ],
    exclude: [
        'src/main/webapp/resources/lib/vendor.js',
        'src/main/webapp/resources/scripts/build.js',
        'src/main/webapp/resources/scripts/templates.js',
        'src/main/webapp/resources/scripts/services/exception.service.js'
    ]
};

// e2e tests have to be execute in following order
var e2eSourceFiles = ['src/test/webapp/e2e/helper.js',
    'src/test/webapp/e2e/pageobject/**/*.js',
    'src/test/webapp/e2e/**/*.js'
];


gulp.task('lint', function () {
    return gulp.src(['src/main/webapp/resources/scripts/**/*.js',
            'src/test/webapp/spec/**/*.js',
            'src/test/webapp/e2e/**/*.js',
            '!src/main/webapp/resources/scripts/main.js',
            '!src/main/webapp/resources/scripts/templates.js',
            '!src/main/webapp/resources/scripts/build.js'
        ])
        .pipe(jshint())
        .pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('spec', gulp.series('lint', function spec(done) {
    karmaConfig.singleRun = true;
    new Server(karmaConfig, done).start();
}));

gulp.task('spec:watch', gulp.series('lint', function specWatch(done) {
    new Server(karmaConfig, done).start();
}));

gulp.task('e2e', gulp.series('lint', function e2e() {
    return gulp.src(e2eSourceFiles)
        .pipe(protractor({
            configFile: "protractor.conf.js",
            args: []
        }));
}));

gulp.task('e2e:debug', gulp.series('lint', function e2eDebug() {
    return gulp.src(e2eSourceFiles)
        .pipe(protractor({
            configFile: "protractor.conf.js",
            args: ['debug']
        }));
}));

gulp.task('clean', function () {
    return del(['src/main/webapp/resources/css/build.css',
        'src/main/webapp/resources/lib/vendor.js',
        'src/main/webapp/resources/scripts/build.js',
        'src/main/webapp/resources/scripts/templates.js']);
});

gulp.task('copy', gulp.series('clean', function copy() {
    var libs = gulp.src(['bower_components/angular/angular.*',
        'bower_components/angular-resource/angular-resource.*',
        'bower_components/angular-route/angular-route.*',
        'bower_components/angular-animate/angular-animate.*',
        'bower_components/angular-cookies/angular-cookies.*',
        'bower_components/angular-touch/angular-touch.*',
        'bower_components/angular-aria/angular-aria.*',
        'bower_components/angular-sanitize/angular-sanitize.*',
        'bower_components/angulartics/dist/angulartics.min.js',
        'bower_components/angulartics/dist/angulartics-ga.min.js',
        'bower_components/lodash/dist/lodash.js',
        'bower_components/lodash/dist/lodash.min.js',
        'bower_components/momentjs/moment.js',
        'bower_components/momentjs/min/moment.min.js',
        'bower_components/less.js/dist/less.js',
        'bower_components/less.js/dist/less.min.js',
        'bower_components/angular-ui-bootstrap-bower/ui-bootstrap.*'])
        .pipe(gulp.dest('./src/main/webapp/resources/lib/'));

    var bootstrap = gulp.src('bower_components/bootstrap/less/**')
        .pipe(gulp.dest('./src/main/webapp/resources/css/bootstrap/'));

    return merge(libs, bootstrap);
}));

gulp.task('less', function () {
    return gulp.src('src/main/webapp/resources/css/project.less')
        .pipe(less())
        .pipe(cleanCSS())
        .pipe(rename('build.css'))
        .pipe(gulp.dest('src/main/webapp/resources/css/'));
});

gulp.task('js:lib', function () {
    return gulp.src(['src/main/webapp/resources/lib/angular.min.js',
        'src/main/webapp/resources/lib/angular-animate.min.js',
        'src/main/webapp/resources/lib/angular-cookies.min.js',
        'src/main/webapp/resources/lib/angular-resource.min.js',
        'src/main/webapp/resources/lib/angular-route.min.js',
        'src/main/webapp/resources/lib/angular-touch.min.js',
        'src/main/webapp/resources/lib/angulartics.min.js',
        'src/main/webapp/resources/lib/angulartics-ga.min.js',
        'src/main/webapp/resources/lib/lodash.min.js',
        'src/main/webapp/resources/lib/moment.min.js',
        'src/main/webapp/resources/lib/ui-bootstrap.min.js',
        'src/main/webapp/resources/lib/textAngular.min.js',
        'src/main/webapp/resources/lib/textAngular-sanitize.min.js'])
        .pipe(concat('vendor.js'))
        .pipe(gulp.dest('src/main/webapp/resources/lib/'));
});

gulp.task('js:scripts', function () {
    return gulp.src(['src/main/webapp/resources/scripts/**/*.js', '!src/main/webapp/resources/scripts/build.js', '!src/main/webapp/resources/scripts/templates.js', '!src/main/webapp/resources/scripts/main.js'])
        .pipe(concat('build.js'))
//        .pipe(uglify({mangle: false}))
        .pipe(gulp.dest('src/main/webapp/resources/scripts/'));
});

gulp.task('templates', function () {
    return gulp.src('src/main/webapp/resources/scripts/controllers/**/*.html')
        .pipe(templateCache({module: 'app', root: '../resources/scripts/controllers/'}))
        .pipe(htmlify())
        .pipe(gulp.dest('src/main/webapp/resources/scripts/'));

});

gulp.task('build', gulp.series(gulp.parallel('less', 'js:lib', 'js:scripts', 'templates'), function build() {
    var indexJsp = gulp.src(['src/main/webapp/WEB-INF/views/index.jsp'])
        .pipe(inject(gulp.src('src/main/webapp/resources/lib/vendor.js', {read: false}),
            {ignorePath: '/src/main/webapp/', addRootSlash: false, starttag: '<!-- inject:vendor:{{ext}} -->'}))
        .pipe(inject(gulp.src(['src/main/webapp/resources/css/build.css', 'src/main/webapp/resources/scripts/build.js', 'src/main/webapp/resources/scripts/templates.js'], {read: false}),
            {ignorePath: '/src/main/webapp/', addRootSlash: false}))
        .pipe(gulp.dest('src/main/webapp/WEB-INF/views/'));

    var otherJsps = gulp.src(['src/main/webapp/WEB-INF/views/devtool.jsp', 'src/main/webapp/WEB-INF/views/secure.jsp'])
        .pipe(inject(gulp.src('src/main/webapp/resources/lib/vendor.js', {read: false}),
            {
                ignorePath: '/src/main/webapp/',
                addPrefix: '..',
                addRootSlash: false,
                starttag: '<!-- inject:vendor:{{ext}} -->'
            }))
        .pipe(inject(gulp.src(['src/main/webapp/resources/css/build.css', 'src/main/webapp/resources/scripts/build.js', 'src/main/webapp/resources/scripts/templates.js'], {read: false}),
            {ignorePath: '/src/main/webapp/', addPrefix: '..', addRootSlash: false}))
        .pipe(gulp.dest('src/main/webapp/WEB-INF/views/'));

    return merge(indexJsp, otherJsps);
}));
