<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>

    <title>${appTitle}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0 minimum-scale=1.0">

    <link href='https://fonts.googleapis.com/css?family=Lato:100,300,400,700,100italic,300italic,400italic,700italic' rel='stylesheet' type='text/css'>
    <link type="image/x-icon" rel="icon" href="resources/img/favicon.png">

    <!-- inject:css -->
    <link rel="stylesheet" href="resources/css/build.css">
    <!-- endinject -->
</head>
<body class="splash">
    <div ng-controller="LoginController" ng-init="init()" class="login-form">
        <div class="form-inline">
            <%--Login as user/user or admin/admin:<br>--%>
            <input id="un" type="text" ng-model="username" placeholder="UserName" class="form-control input-sm" >
            <input id="pw" type="password" ng-model="password" placeholder="Password" class="form-control input-sm">
            <button ng-click="login()" class="btn btn-default btn-sm">Login</button>
        </div>
        <p class="text-danger" ng-if="hasError">{{message}}</p>
        <div ng-show="authenticated">
            Authenticating user <span class="loader">...</span>
        </div>
        <button ng-click="spoof({username: 'user'})" class="btn btn-default btn-sm" ng-if="${devtool}" style="float: right; margin-top: 10px;">Spoof</button>
    </div>

    <div class="container-fluid">
        <div class="container">
                <div>
                    <h1>${appTitle}</h1>
                    <p class="lead">SpringMvcWithNG is a sample project to include spring hibernet with angularjs. as backend we are using h2 (in memory database).</p>

                </div>
        </div>
    </div>

<!-- inject:vendor:js -->
<script src="resources/lib/vendor.js"></script>
<!-- endinject -->

<script src="resources/scripts/login.js"></script>
<script src="resources/scripts/services/interceptor.service.js"></script>
<script src="resources/scripts/services/errormessage.service.js"></script>


<script>
    angular.element(document).ready(function () {
        angular.module('app').constant('APP_URL', '${appUrl}');
        angular.bootstrap(document, ["app"]);
    });
</script>
</body>
</html>