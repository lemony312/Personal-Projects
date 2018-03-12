import _ from 'lodash'

class Request {

  constructor(req) {
    this.native = req
  }

  getLocals() {
    if (_.isEmpty(this.native.locals)) {
      this.native.locals = {};
    }
    return this.native.locals;
  }

  getParam(key) {
    if (this.native.query && key in this.native.query) {
      return this.native.query[key];
    }
    if (this.native.body && key in this.native.body) {
      return this.native.body[key];
    }
    if (this.native.params && key in this.native.params) {
      return this.native.params[key];
    }
    if (this.native.files && key in this.native.files) {
      return this.native.files[key];
    }
    return null;
  };

  getUser() {
    return this.getLocals().user;
  }


  getParams() {
    let obj = {};
    if (this.native.query) {
      for (let i in this.native.query) {
        if (!isValidParam(i)) continue;
        obj[i] = this.native.query[i];
      }
    }

    if (this.native.body) {
      for (let i in this.native.body) {
        if (!isValidParam(i)) continue;
        obj[i] = this.native.body[i];
      }
    }

    if (this.native.files) {
      for (let i in this.native.files) {
        if (!isValidParam(i)) continue;
        obj[i] = this.getFiles(i);
      }
    }
    if (this.native.params) {
      for (let i in this.native.params) {
        if (!isValidParam(i)) continue;
        obj[i] = this.native.params[i];
      }
    }

    function isValidParam(param) {
      return !(typeof param === 'undefined' || param === null || param === 'null');

    }

    return obj;
  };


  getFiles(name) {
    if (this.native.files && this.native.files[name]) {
      let file = this.native.files[name];
      if (Array.isArray(file)) {
        let files = [];
        for (let i = 0; i < file.length; i++) {
          files.push(buildFileObj(file[i]));
        }
        return files;
      } else {
        return buildFileObj(file);
      }
    }

    function buildFileObj(file) {
      let ext = '';
      if (file.name) {
        let i = file.name.lastIndexOf('.');
        ext = (i < 0) ? '' : file.name.substr(i + 1);
      }
      return {
        size: file.size,
        path: file.path,
        name: file.name,
        type: file.type,
        extension: ext
      }
    }

    return null;
  };

  getAccessToken() {
    return this.native.header('X-ACCESS-TOKEN');
  };

  getAPIKey() {
    return this.native.header('X-API-KEY');
  };

}

export {Request}