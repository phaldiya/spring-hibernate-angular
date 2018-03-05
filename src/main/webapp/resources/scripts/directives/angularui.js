'use strict';

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/datepicker/datepicker.html",
            "<div ng-switch=\"datepickerMode\" role=\"application\" ng-keydown=\"keydown($event)\">\n" +
            "  <daypicker ng-switch-when=\"day\" tabindex=\"0\"></daypicker>\n" +
            "  <monthpicker ng-switch-when=\"month\" tabindex=\"0\"></monthpicker>\n" +
            "  <yearpicker ng-switch-when=\"year\" tabindex=\"0\"></yearpicker>\n" +
            "</div>");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/datepicker/day.html",
            "<table class=\"datepicker\" role=\"grid\" aria-labelledby=\"{{uniqueId}}-title\" aria-activedescendant=\"{{activeDateId}}\">\n" +
            "  <thead>\n" +
            "    <tr>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-left\" ng-click=\"move(-1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-left\"></i></button></th>\n" +
            "      <th colspan=\"{{5 + showWeeks}}\"><button id=\"{{uniqueId}}-title\" role=\"heading\" aria-live=\"assertive\" aria-atomic=\"true\" type=\"button\" class=\"btn btn-default btn-sm\" ng-click=\"toggleMode()\" tabindex=\"-1\" style=\"width:100%;\"><strong>{{title}}</strong></button></th>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-right\" ng-click=\"move(1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-right\"></i></button></th>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <th ng-show=\"showWeeks\" class=\"text-center\"></th>\n" +
            "      <th ng-repeat=\"label in labels track by $index\" class=\"text-center\"><small aria-label=\"{{label.full}}\">{{label.abbr}}</small></th>\n" +
            "    </tr>\n" +
            "  </thead>\n" +
            "  <tbody>\n" +
            "    <tr ng-repeat=\"row in rows track by $index\">\n" +
            "      <td ng-show=\"showWeeks\" class=\"text-center h6\"><em>{{ weekNumbers[$index] }}</em></td>\n" +
            "      <td ng-repeat=\"dt in row track by dt.date\" class=\"text-center\" role=\"gridcell\" id=\"{{dt.uid}}\" aria-disabled=\"{{!!dt.disabled}}\">\n" +
            "        <button type=\"button\" style=\"width:100%;\" class=\"btn btn-default btn-sm\" ng-class=\"{'btn-info': dt.selected, active: isActive(dt)}\" ng-click=\"select(dt.date)\" ng-disabled=\"dt.disabled\" tabindex=\"-1\"><span ng-class=\"{'text-muted': dt.secondary, 'text-info': dt.current}\">{{dt.label}}</span></button>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody>\n" +
            "</table>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/datepicker/month.html",
            "<table role=\"grid\" aria-labelledby=\"{{uniqueId}}-title\" aria-activedescendant=\"{{activeDateId}}\">\n" +
            "  <thead>\n" +
            "    <tr>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-left\" ng-click=\"move(-1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-left\"></i></button></th>\n" +
            "      <th><button id=\"{{uniqueId}}-title\" role=\"heading\" aria-live=\"assertive\" aria-atomic=\"true\" type=\"button\" class=\"btn btn-default btn-sm\" ng-click=\"toggleMode()\" tabindex=\"-1\" style=\"width:100%;\"><strong>{{title}}</strong></button></th>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-right\" ng-click=\"move(1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-right\"></i></button></th>\n" +
            "    </tr>\n" +
            "  </thead>\n" +
            "  <tbody>\n" +
            "    <tr ng-repeat=\"row in rows track by $index\">\n" +
            "      <td ng-repeat=\"dt in row track by dt.date\" class=\"text-center\" role=\"gridcell\" id=\"{{dt.uid}}\" aria-disabled=\"{{!!dt.disabled}}\">\n" +
            "        <button type=\"button\" style=\"width:100%;\" class=\"btn btn-default\" ng-class=\"{'btn-info': dt.selected, active: isActive(dt)}\" ng-click=\"select(dt.date)\" ng-disabled=\"dt.disabled\" tabindex=\"-1\"><span ng-class=\"{'text-info': dt.current}\">{{dt.label}}</span></button>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody>\n" +
            "</table>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/datepicker/popup.html",
            "<ul class=\"dropdown-menu\" ng-style=\"{display: (isOpen && 'block') || 'none', top: position.top+'px', left: position.left+'px'}\" ng-keydown=\"keydown($event)\">\n" +
            "	<li ng-transclude></li>\n" +
            "	<li ng-if=\"showButtonBar\" style=\"padding:10px 9px 2px\">\n" +
            "		<span class=\"btn-group\">\n" +
            "			<button type=\"button\" class=\"btn btn-sm btn-info\" ng-click=\"select('today')\">{{ getText('current') }}</button>\n" +
            "			<button type=\"button\" class=\"btn btn-sm btn-danger\" ng-click=\"select(null)\">{{ getText('clear') }}</button>\n" +
            "		</span>\n" +
            "		<button type=\"button\" class=\"btn btn-sm btn-success pull-right\" ng-click=\"close()\">{{ getText('close') }}</button>\n" +
            "	</li>\n" +
            "</ul>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/popover/popover.html",
            "<div class=\"popover {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\">\n" +
            "  <div class=\"arrow\"></div>\n" +
            "\n" +
            "  <div class=\"popover-inner\">\n" +
            "      <h3 class=\"popover-title\" ng-bind=\"title\" ng-show=\"title\"></h3>\n" +
            "      <div class=\"popover-content\" ng-bind=\"content\"></div>\n" +
            "  </div>\n" +
            "</div>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/datepicker/year.html",
            "<table role=\"grid\" aria-labelledby=\"{{uniqueId}}-title\" aria-activedescendant=\"{{activeDateId}}\">\n" +
            "  <thead>\n" +
            "    <tr>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-left\" ng-click=\"move(-1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-left\"></i></button></th>\n" +
            "      <th colspan=\"3\"><button id=\"{{uniqueId}}-title\" role=\"heading\" aria-live=\"assertive\" aria-atomic=\"true\" type=\"button\" class=\"btn btn-default btn-sm\" ng-click=\"toggleMode()\" tabindex=\"-1\" style=\"width:100%;\"><strong>{{title}}</strong></button></th>\n" +
            "      <th><button type=\"button\" class=\"btn btn-default btn-sm pull-right\" ng-click=\"move(1)\" tabindex=\"-1\"><i class=\"glyphicon glyphicon-chevron-right\"></i></button></th>\n" +
            "    </tr>\n" +
            "  </thead>\n" +
            "  <tbody>\n" +
            "    <tr ng-repeat=\"row in rows track by $index\">\n" +
            "      <td ng-repeat=\"dt in row track by dt.date\" class=\"text-center\" role=\"gridcell\" id=\"{{dt.uid}}\" aria-disabled=\"{{!!dt.disabled}}\">\n" +
            "        <button type=\"button\" style=\"width:100%;\" class=\"btn btn-default\" ng-class=\"{'btn-info': dt.selected, active: isActive(dt)}\" ng-click=\"select(dt.date)\" ng-disabled=\"dt.disabled\" tabindex=\"-1\"><span ng-class=\"{'text-info': dt.current}\">{{dt.label}}</span></button>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody>\n" +
            "</table>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/typeahead/typeahead-match.html",
        "<a tabindex=\"-1\">{{match.label}}</a>");
}]);

angular.module("app").run(["$templateCache", function ($templateCache) {
    $templateCache.put("template/typeahead/building.html",
        "<a tabindex=\"-1\">{{match.model.secondaryName != null ? match.model.secondaryName : match.model.primaryName}} <small class=\"text-muted\" ng-if=\"match.model.address\">({{match.model.address}})</small></a>");
}]);


angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/typeahead/person.html",
        "<a tabindex=\"-1\">{{match.label}} ({{match.model.email}})</a>");
}]);


angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/typeahead/typeahead-popup.html",
        "<ul class=\"typeahead dropdown-menu\" ng-style=\"{display: isOpen()&&'block' || 'none', top: position.top+'px', left: position.left+'px', 'min-width': position.width+'px'}\">\n" +
            "    <li ng-repeat=\"match in matches\" ng-class=\"{active: isActive($index) }\" ng-mouseenter=\"selectActive($index)\" ng-click=\"selectMatch($index)\">\n" +
            "        <typeahead-match index=\"$index\" match=\"match\" query=\"query\" template-url=\"templateUrl\"></typeahead-match>\n" +
            "    </li>\n" +
            "</ul>");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/carousel/carousel.html",
        "<div ng-mouseenter=\"pause()\" ng-mouseleave=\"play()\" class=\"carousel\">\n" +
            "    <ol class=\"carousel-indicators\" ng-show=\"slides().length > 1\">\n" +
            "        <li ng-repeat=\"slide in slides()\" ng-class=\"{active: isActive(slide)}\" ng-click=\"select(slide)\"></li>\n" +
            "    </ol>\n" +
            "    <div class=\"carousel-inner\" ng-transclude></div>\n" +
            "    <a ng-click=\"prev()\" class=\"carousel-control left\" ng-show=\"slides().length > 1\"><span class=\"glyphicon glyphicon-chevron-left\"></span></a>\n" +
            "    <a ng-click=\"next()\" class=\"carousel-control right\" ng-show=\"slides().length > 1\"><span class=\"glyphicon glyphicon-chevron-right\"></span></a>\n" +
            "</div>\n" +
            "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/tooltip/tooltip-html-unsafe-popup.html",
        "<div class=\"tooltip {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\">\n" +
        "  <div class=\"tooltip-arrow\"></div>\n" +
        "  <div class=\"tooltip-inner\" bind-html-unsafe=\"content\"></div>\n" +
        "</div>\n" +
        "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/tooltip/tooltip-popup.html",
        "<div class=\"tooltip {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\">\n" +
        "  <div class=\"tooltip-arrow\"></div>\n" +
        "  <div class=\"tooltip-inner\" ng-bind=\"content\"></div>\n" +
        "</div>\n" +
        "");
}]);

angular.module("app").run(["$templateCache", function($templateCache) {
    $templateCache.put("template/carousel/slide.html",
        "<div ng-class=\"{\n" +
            "    'active': leaving || (active && !entering),\n" +
            "    'prev': (next || active) && direction=='prev',\n" +
            "    'next': (next || active) && direction=='next',\n" +
            "    'right': direction=='prev',\n" +
            "    'left': direction=='next'\n" +
            "  }\" class=\"item\" ng-transclude></div>\n" +
            "");
}]);
