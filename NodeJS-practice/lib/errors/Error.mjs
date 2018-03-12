class Error {

    constructor(errorType, message) {
        this.errorType = errorType;
        this.message = message;
    }

    getError() {
        return {
            name: this.errorType.name,
            message: this.message ? this.message : this.errorType.message
        }
    }

    getHTTPCode() {
        return this.errorType.responseType.code;
    }

    getHTTPMessage() {
        return this.errorType.responseType.message;
    }
}


const HTTP_RESPONSE_TYPES = {
    OK: {
        200: 'OK'
    },
    BAD_REQUEST:
        {
            code: 400,
            message: 'Bad Request'
        },
    UNAUTHORIZED: {
        code: 401, message: 'Unauthorized'
    },
    NOT_FOUND: {
        code: 404, message: 'Not Found'
    },
    FORBIDDEN: {
        code: 403, message: 'Forbidden'
    },
    INTERNAL_SERVER_ERROR: {
        code: 500, message: 'Internal Server Error'
    }
};

const ErrorTypes = {
    USER_EMAIL_REQUIRED: {
        name: "USER_EMAIL_REQUIRED",
        message: "Email address is required",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_LAST_NAME_REQUIRED: {
        name: "USER_LAST_NAME_REQUIRED",
        message: "Please enter your last name",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_FIRST_NAME_REQUIRED: {
        name: "USER_FIRST_NAME_REQUIRED",
        message: "Please enter your first name",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_PASSWORD_REQUIRED: {
        name: "USER_PASSWORD_REQUIRED",
        message: "Password is required",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    USER_EMAIL_EXISTS: {
        name: "USER_EMAIL_EXISTS",
        message: "A user already exists with this email address.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    USER_ACCOUNT_DOES_NOT_EXIST: {
        message: "This account does not exist, please try another email address",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_USER_ID_FORMAT: {
        name: "INCORRECT_USER_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_COURSE_ID_FORMAT: {
        name: "INCORRECT_COURSE_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_MODULE_ID_FORMAT: {
        name: "INCORRECT_MODULE_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_CLASS_ID_FORMAT: {
        name: "INCORRECT_CLASS_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_STUDENT_ID_FORMAT: {
        name: "INCORRECT_STUDENT_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_PROBLEM_ID_FORMAT: {
        name: "INCORRECT_PROBLEM_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_CHOICE_ID_FORMAT: {
        name: "INCORRECT_PROBLEM_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_CLASS_ID: {
        name: "MISSING_CLASS_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_STUDENT_ID: {
        name: "MISSING_STUDENT_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_ASSESSMENT_ID: {
        name: "MISSING_ASSESSMENT_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INCORRECT_ASSESSMENT_ID_FORMAT: {
        name: "INCORRECT_ASSESSMENT_ID_FORMAT",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_MODULE_ID: {
        name: "MISSING_MODULE_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_PROBLEM_ID: {
        name: "MISSING_PROBLEM_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    MISSING_CHOICE_ID: {
        name: "MISSING_CHOICE_ID",
        message: "Please send a valid BSON ID",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_INCORRECT_EMAIL_FORMAT: {
        name: "USER_INCORRECT_EMAIL_FORMAT",
        message: "Please enter a valid email address",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_INVALID_ROLE: {
        name: "USER_INVALID_ROLE",
        message: "Please send a valid role when creating an account. Valid roles are 'Admin', 'Instructor', 'Student' and are case-sensitive.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_SCHOOL_ID_REQUIRED: {
        name: "USER_SCHOOL_ID_REQUIRED",
        message: "Please send a valid school id",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_COURSE_ID_REQUIRED: {
        name: "USER_COURSE_ID_REQUIRED",
        message: "Please send a valid course id",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },


    COURSE_NAME_REQUIRED: {
        name: "COURSE_NAME_REQUIRED",
        message: "Please send a valid course name",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    CLASS_NAME_REQUIRED: {
        name: "CLASS_NAME_REQUIRED",
        message: "Please send a valid class name",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    ACCESS_CODE_REQUIRED: {
        name: "ACCESS_CODE_REQUIRED",
        message: "Please send an access code in the access_code field if you are not a student.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_DOES_NOT_EXIST: {
        name: "USER_DOES_NOT_EXIST",
        message: "This user does not exist.",
        responseType: HTTP_RESPONSE_TYPES.NOT_FOUND
    },

    COURSE_DOES_NOT_EXIST: {
        name: "COURSE_DOES_NOT_EXIST",
        message: "This course does not exist.",
        responseType: HTTP_RESPONSE_TYPES.NOT_FOUND
    },

    CLASS_DOES_NOT_EXIST: {
        name: "CLASS_DOES_NOT_EXIST",
        message: "This class does not exist.",
        responseType: HTTP_RESPONSE_TYPES.NOT_FOUND
    },

    MODULE_DOES_NOT_EXIST: {
        name: "MODULE_DOES_NOT_EXIST",
        message: "This module does not exist.",
        responseType: HTTP_RESPONSE_TYPES.NOT_FOUND
    },

    INVALID_ACCESS_CODE: {
        name: "INVALID_ACCESS_CODE",
        message: "Please enter a valid access code.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INVALID_INSTRUCTOR_ACCESS_CODE: {
        name: "INVALID_INSTRUCTOR_ACCESS_CODE",
        message: "Please enter a valid access code.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INVALID_INSTRUCTOR_CODE: {
        name: "INVALID_INSTRUCTOR_CODE",
        message: "Please enter a valid access code.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },


    EMAIL_DOES_NOT_EXIST: {
        name: "EMAIL_DOES_NOT_EXIST",
        message: "This email address does not exist.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INVALID_CLASS_CODE: {
        name: "INVALID_CLASS_CODE",
        message: "Please enter a valid class code.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    INVALID_CLASS_ID: {
        name: "INVALID_CLASS_ID",
        message: "Please enter a valid class id.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_MALFORMED_PASSWORD: {
        name: "USER_MALFORMED_PASSWORD",
        message: "Please enter a valid password. Passwords should be between 5 and 32 characters.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    USER_INVALID_INSTRUCTOR_ACCESS_CODE: {
        name: "USER_INVALID_INSTRUCTOR_ACCESS_CODE",
        message: "Please enter a valid instructor access code",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    USER_INVALID_PASSWORD: {
        name: "USER_INVALID_PASSWORD",
        message: "You have entered an incorrect password.",
        responseType: HTTP_RESPONSE_TYPES.UNAUTHORIZED
    },

    USER_INVALID_PASSWORD_RESET_TOKEN: {
        name: "USER_INVALID_PASSWORD_RESET_TOKEN",
        message: "You have entered an invalid password reset token.",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_INVALID_SCHOOL: {
        name: "USER_INVALID_SCHOOL",
        message: "Please choose a valid school",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },

    USER_INVALID_GRADE_LEVEL: {
        name: "USER_INVALID_GRADE_LEVEL",
        message: "Please choose a grade level",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    FRAMEWORK_BAD_INPUT_FORMAT: {
        name: "FRAMEWORK_BAD_INPUT_FORMAT",
        message: "Bad Input Format",
        responseType: HTTP_RESPONSE_TYPES.BAD_REQUEST
    },
    FRAMEWORK_REPOSITORY_ERROR: {
        name: "FRAMEWORK_REPOSITORY_ERROR",
        message: "Repository error",
        responseType: HTTP_RESPONSE_TYPES.INTERNAL_SERVER_ERROR
    },
    FRAMEWORK_SERVICE_ERROR: {
        name: "FRAMEWORK_SERVICE_ERROR",
        message: "Service Error",
        responseType: HTTP_RESPONSE_TYPES.INTERNAL_SERVER_ERROR
    },
    FRAMEWORK_CONTROLLER_ERROR: {
        name: "FRAMEWORK_CONTROLLER_ERROR",
        message: "Controller Error",
        responseType: HTTP_RESPONSE_TYPES.INTERNAL_SERVER_ERROR
    },
    FRAMEWORK_ROUTER_ERROR: {
        name: "FRAMEWORK_ROUTER_ERROR",
        message: "Router Error",
        responseType: HTTP_RESPONSE_TYPES.INTERNAL_SERVER_ERROR
    },
    FRAMEWORK_INTERNAL_ERROR: {
        name: "FRAMEWORK_INTERNAL_ERROR",
        message: "Internal Framework ERror",
        responseType: HTTP_RESPONSE_TYPES.INTERNAL_SERVER_ERROR
    },

    UNAUTHORIZED_USER_ERROR: {
        name: "UNAUTHORIZED_USER_ERROR",
        message: "You are not authorized to access this resource",
        responseType: HTTP_RESPONSE_TYPES.UNAUTHORIZED,
    },

    UNAUTHORIZED_APPLICATION_ERROR: {
        name: "UNAUTHORIZED_APPLICATION_ERROR",
        message: "Your application is not authorized to access this resource",
        responseType: HTTP_RESPONSE_TYPES.UNAUTHORIZED,
    }
};


export {Error}
export {ErrorTypes}



