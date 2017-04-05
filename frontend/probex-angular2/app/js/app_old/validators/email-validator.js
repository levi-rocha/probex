"use strict";
var EmailValidator = (function () {
    function EmailValidator() {
    }
    EmailValidator.validate = function (control) {
        if (EmailValidator.EMAIL_REGEX.test(control.value)) {
            return null;
        }
        return { 'email': true };
    };
    EmailValidator.EMAIL_REGEX = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    return EmailValidator;
}());
exports.EmailValidator = EmailValidator;
//# sourceMappingURL=email-validator.js.map