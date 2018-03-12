import validator from 'validator'
import lodash from 'lodash'

class BaseValidator {
    constructor() {
        this._ = lodash;
        this.validator = validator;
    }
}

export {BaseValidator}