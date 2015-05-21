'use strict';

describe('Person', function () {
    it("should get the perspns list page", function () {
        PersonPage.get();

        expect(element.all(by.repeater("person in persons | orderBy: 'firstName'")).count()).toBe(6);
    });
});
