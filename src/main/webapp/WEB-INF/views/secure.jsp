<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>

    <title>${appTitle}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0 minimum-scale=1.0">

    <link href='https://fonts.googleapis.com/css?family=Lato:100,300,400,700,100italic,300italic,400italic,700italic' rel='stylesheet' type='text/css'>
    <link type="image/x-icon" rel="icon" href="../resources/img/favicon.png">

    <!-- inject:css -->
    <link rel="stylesheet/less" type="text/css" href="../resources/css/project.less" />
    <script>var less = { logLevel: 1 };</script>
    <script src="../resources/lib/less.js"></script>
    <!-- endinject -->
</head>
<body ng-controller="NavigationCtrl" ng-class="{active: sideMenu}" ng-init="init()" >

<div class="navbar navbar-default navbar-fixed-top ${enviroment != 'prod' ? 'navbar-gray' : ''}" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" ng-click="sideMenu = !sideMenu">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <div class="dropdown">
                <a class="navbar-brand dropdown-toggle text-center" ng-click="sideMenu = false">${appTitle}</a>
                <%--<ul class="dropdown-menu">
                    <li><a href="https://ehs.ucop.edu/ucsafety/secure/">UC Safety Dashboard</a></li>
                </ul>--%>
            </div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav" ng-cloak>
                <li ng-class="{'active': activeTab === tab}" ng-repeat="tab in navigation"><a href ng-click="selectActiveTab(tab)">{{tab.label}}</a></li>
            </ul>
        </div>

        <div class="navbar-right dropdown" dropdown on-toggle="toggled(open)">
            <a href class="dropdown-toggle" dropdown-toggle>
                <span class="glyphicon glyphicon-user"></span>
                <span class="user" ng-cloak>{{user.username}}</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                <li>
                    <a href ng-click="logout()"><span class="uc-exit-circle"></span>Sign Out</a>
                </li>
            </ul>
        </div>
    </div>

    <ol class="breadcrumb">
        <li><a href="#/" class="glyphicon glyphicon-home"></a></li>
        <li ng-cloak ng-repeat="crumb in breadcrumb" ng-class="{active: $last}"><a ng-href="{{'#' + crumb.url}}" ng-if="!$last">{{crumb.name}}</a><span ng-if="$last">{{crumb.name}}</span></li>
        <li ng-if="breadcrumb.length === 0" class="active">Home</li>
    </ol>
</div>

<!-- Sidebar -->
<div class="sidebar">
    <ul class="sidebar-nav">
        <li ng-class="{'active': activeTab === tab}" ng-repeat="tab in navigation"><a href ng-click="selectActiveTab(tab, true)">{{tab.label}}</a></li>
    </ul>
</div>

<div class="container-fluid app-container" ng-view></div>

<%--
<div class="system-error" ng-cloak ng-if="errors.length > 0">
    <div class="text-center">
        <img ng-src="../resources/img/error.{{errors[0].status === 401 ? 'deny' : 'general'}}.svg" alt="error image">

        <div ng-if="errors[0].status === 401">
            <h2 class="pa">Access Denied</h2>
            <p>Either you are not logged in or you do not have the appropriate privileges to perform this action.
                Please contact <a href="mailto:erm@ucop.edu">erm@ucop.edu</a> for assistance.</p>
        </div>
        <div ng-if="errors[0].status === 404">
            <h2 class="pa">Well, this is embarrassing!</h2>
            <p>We are sorry but the page you are looking for does not exist. Please contact <a href="mailto:erm@ucop.edu">erm@ucop.edu</a> for assistance.</p>
        </div>
        <div ng-if="errors[0].status === 500">
            <h2 class="pa">Well, this is embarrassing!</h2>
            <p>The system is not able to complete the action you are trying to perform.  <br/>
                Please reload the page and try again.  <br/>
                If the problem persists, please contact <a href="mailto:erm@ucop.edu">erm@ucop.edu</a>
            </p>
        </div>
    </div>

    <div class="text-danger" ng-if="errors[0].data.stackTrace.length > 0">
        <hr/>
        <strong>Error Message:</strong> {{errors[0].data.message}}<br/>
        <strong>StackTrace:</strong> {{errors[0].data.stackTrace}}
    </div>
</div>
--%>

<!--[if lt IE 10]>
<div class="browser-message-container">
    <h1 class="text-center">A new version of your browser is available.</h1>
    <h2 class="text-center">To ensure the best experience, we recommend using the latest version of one of the following web browsers</h2>

    <div class="browser text-center">
        <a href="http://www.microsoft.com/windows/internet-explorer"><img src="../resources/img/icon-ie.png" alt="Internet Explorer 9"  /><br/> Internet Explorer 11</a>
        <a href="http://www.google.com/chrome"><img src="../resources/img/icon-chrome.png" /><br/>Google Chrome</a>
        <a href="http://getfirefox.com" ><img src="../resources/img/icon-firefox.png" /><br/>Mozilla Firefox</a>
        <a href="http://www.apple.com/safari/download/"><img src="../resources/img/icon-safari.png" /><br/>Apple Safari</a>
    </div>
    <div class="browser-message">
        <div class="why-upgrade">
            <img src="resources/img/why.png" />
            <h4>Why should I upgrade?</h4>
            <dl>
                <dt>Web sites load faster</dt>
                <dd>often double the speed of this older version.</dd>
                <dt>Web sites render correctly</dt>
                <dd>with more web standards compliance.</dd>
                <dt>Safer browsing</dt>
                <dd>with better security and phishing protection.</dd>
                <dt>Convenient Printing</dt>
                <dd>with fit-to-page capability.</dd>
            </dl>
        </div>
    </div>
</div>
<![endif]-->

<!-- inject:vendor:js -->
<script src="../resources/lib/angular.js"></script>
<script src="../resources/lib/angular-animate.js"></script>
<script src="../resources/lib/angular-cookies.js"></script>
<script src="../resources/lib/angular-resource.js"></script>
<script src="../resources/lib/angular-route.js"></script>
<script src="../resources/lib/angular-touch.js"></script>
<script src="../resources/lib/angular-aria.js"></script>
<script src="../resources/lib/ui-bootstrap.js"></script>
<script src="../resources/lib/lodash.js"></script>
<!-- endinject -->

<script src="../resources/scripts/app.js"></script>

<script src="../resources/scripts/filters/camelcase.filter.js"></script>
<script src="../resources/scripts/filters/unique.filter.js"></script>

<script src="../resources/scripts/services/interceptor.service.js"></script>
<script src="../resources/scripts/services/util.service.js"></script>
<script src="../resources/scripts/services/errormessage.service.js"></script>
<script src="../resources/scripts/services/breadcrumb.service.js"></script>
<script src="../resources/scripts/services/person.service.js"></script>

<script src="../resources/scripts/directives/angularui.js"></script>

<script src="../resources/scripts/controllers/home.js"></script>
<script src="../resources/scripts/controllers/navigation.js"></script>

<%--<script src="../resources/scripts/controllers/authenticat/authenticat.js"></script>--%>
<script src="../resources/scripts/controllers/person/person.js"></script>
<script src="../resources/scripts/controllers/person/person.detail.js"></script>
<script src="../resources/scripts/controllers/help/help.js"></script>


<script>
    angular.element(document).ready(function () {
        angular.module('app').constant('APP_URL', '${shibUrl}');
        angular.bootstrap(document, ["app"]);
    });
</script>
</body>
</html>
