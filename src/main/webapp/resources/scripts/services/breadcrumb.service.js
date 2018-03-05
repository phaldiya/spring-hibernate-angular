'use strict';

angular.module('app').factory('BreadcrumbService', function ($cookieStore, $location) {

    return {
        breadcrumb: $cookieStore.get('breadcrumb') ? $cookieStore.get('breadcrumb') : [],

        push: function(name, reset){
            if(reset){
                this.breadcrumb.length = 0;
            }

            if (!_.any(this.breadcrumb, function (item) { return item.name === name; })) {
                this.breadcrumb.push({name: name, url: $location.url()});
            }
            $cookieStore.put('breadcrumb', this.breadcrumb);
        },

        pop: function(){
            this.breadcrumb.pop();
            $cookieStore.put('breadcrumb', this.breadcrumb);
        },

        /**
         * reset the breadcrumb stack. remove all the child of **name** node.
         * Example: Report > Summery > Room > Edit
         * reset()             : Home
         * reset('Summery')    : Report > Summery
         */
        reset: function(name){
            if(name) {
                while(this.breadcrumb.length > 1 && this.breadcrumb[this.breadcrumb.length - 1].name !== name){
                    this.breadcrumb.pop();
                }
            } else {
                this.breadcrumb.length = 0;
            }

            $cookieStore.put('breadcrumb', this.breadcrumb);
        }
    };
});