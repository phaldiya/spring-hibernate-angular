'use strict';

describe('Person', function () {

    it('should login', function () {
        PersonPage.get(true);
    });

    it("should get the perspns list page", function () {
        PersonPage.get(true);

        expect(element.all(by.repeater("person in persons | orderBy: 'firstName'")).count()).toBe(6);
    });
});
