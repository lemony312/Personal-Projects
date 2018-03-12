import _ from 'lodash'
import {Error} from "../errors/Error";
import {ErrorTypes} from "../errors/Error.mjs";
let defaultErrorHTTPCode = 500;
let defaultErrorMessage = "Internal Server Error";
let defaultSuccessHTTPCode = 200;
let invalidErrorException = "Errors must be created with the provided Error object";
let missingErrorException = "No error message specified in error response";


class Response {

    constructor(res) {
        this.native = res;
    }

    sendSuccess(data, meta = null) {
        send(this.native, data, defaultSuccessHTTPCode)
    }

    pipe(readStream) {
        readStream.pipe(this.native);
    }

    sendErrors(errors) {

        if (!errors) {
            throw missingErrorException;
        }

        let errorArray = [];

        if (_.isArray(errors)) {
            errorArray = errors;
        } else if (!errors.getError) {
            errorArray.push(new Error(ErrorTypes.FRAMEWORK_INTERNAL_ERROR, errors.toString()))
        } else {
            errorArray.push(errors)
        }

        let errorEnvelope = {
            errors: []
        };

        errorArray.forEach((errorObject) => {
            if (!errorObject instanceof Error) {
                throw invalidErrorException;
            }
            errorEnvelope.errors.push(errorObject.getError())
        });

        let firstErrorObject = errorArray[0];
        let httpCode = firstErrorObject ? firstErrorObject.getHTTPCode() : defaultErrorHTTPCode;
        let httpMessage = firstErrorObject ? firstErrorObject.getHTTPMessage() : defaultErrorMessage;

        errorEnvelope["http_status"] = httpCode;
        errorEnvelope["http_message"] = httpMessage;

        send(this.native, errorEnvelope, httpCode);
    }
}

function send(nativeRes, data, code) {
    nativeRes.status(code).json(data);
}

export {Response};