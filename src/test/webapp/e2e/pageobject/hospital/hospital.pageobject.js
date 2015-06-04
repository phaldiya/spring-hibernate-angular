'use strict';
global.HospitalPage = {
    get: function() {
        Helper.clickHomeLink();
        element(by.linkText('Hospital')).click();
        return this;
    },
    clickAddNew: function() {
        element(by.buttonText('Add New')).click();
        return this;
    },
    clickDelete: function(i) {
            element.all(by.buttonText('Delete')).get(i).click();
            return this;
        },
    clickCancel: function() {
            element(by.buttonText('Cancel')).click();
            return this;
    },
    enterHospitalName: function(text) {
            element(by.model('newhospital.hospitalName')).sendKeys(text);
            return this;
    },
    enterHospitalCity: function(text) {
                element(by.model('newhospital.hospitalCity')).sendKeys(text);
                return this;
        },
    clickSave: function(){
               element(by.buttonText('Save')).click();
               return this;
    },
    clickEdit:function(i){
            element.all(by.buttonText('Edit')).get(i).click();
            return this;
    }
};