'use strict';

fdescribe('Hospital', function () {
    var page = null;
    beforeEach(function() {
        page = HospitalPage.get();
    });
    /*it("should get the list page", function () {
        expect(element.all(by.repeater("hospital in hospitals | orderBy: 'hospitalCity'")).count()).toBe(3);
    });

    it("should be able to get 'Add Hospital Form'", function () {
        page.clickAddNew();

        expect(element(by.name('hospitalForm')).isDisplayed()).toBeTruthy();
    });*/

    /*it("should be able to close 'Add Hospital Form'",function(){
        page.clickAddNew()
            .clickCancel();

        expect(element(by.name('hospitalForm')).isDisplayed()).toBeFalsy();
    });
*/
    it("should be able to save Hospital",function(){
        page.clickAddNew()
            .enterHospitalName('kyser')
            .enterHospitalCity('folsom')
            .clickSave();

        expect(element.all(by.repeater("hospital in hospitals | orderBy: 'hospitalCity'")).count()).toBe(4);
        expect(element(by.name('hospitalForm')).isDisplayed()).toBeFalsy();
    });

    it("should be able to Edit Hospital",function(){
        page.clickEdit(1)
            .enterHospitalName('Priyaan')
            .enterHospitalCity('Haldiya')
            .clickSave();

        expect(element(by.cssContainingText('.name','Priyaan')));
        expect(element(by.cssContainingText('.city','Haldiya')));
    });

    it("should be able to Delete Hospital",function() {
        page.clickDelete(1);

        expect(element.all(by.repeater("hospital in hospitals | orderBy: 'hospitalCity'")).count()).toBe(2);
    });

});
