import {Response} from "../lib/http/Response";
import {Request} from "../lib/http/Request";

class BaseController {

    constructor(req, res, next) {
        this.request = new Request(req);
        this.response = new Response(res);
        this.next = next;
    }

    getUser() {
        return this.getLocals().user;
    }

    getLoggedInUserId() {
        return this.getLocals().user._id;
    }

    getLocals() {
        return this.request.getLocals()
    }

    getParam(key) {
        return this.getRequest() ? this.getRequest().getParam(key) : null;
    }

    getId() {
        return this.getParam('id')
    }

    pipe(readStream) {
        this.getResponse().pipe(readStream)
    }

    getRequest() {
        return this.request;
    }

    getResponse() {
        return this.response;
    }

    ok(data) {
        this.getResponse().sendSuccess(data)
    }

    err(errors) {
        this.getResponse().sendErrors(errors);
    }

}

export {BaseController}