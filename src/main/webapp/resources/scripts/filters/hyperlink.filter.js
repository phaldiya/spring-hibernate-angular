'use strict';


angular.module('app').filter('urlToHyperlink', ['$parse', function () {
    return function (input) {
        if(input){
            input = _.escape(input);
            var reg = /((?:https?:\/\/|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}\/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:'".,<>?«»“”‘’]))/g;
            return input.replace(reg,"<a href='$1' target='_blank'>$1</a>");
        }
    };
}]);