import {BaseRepository} from './BaseRepository.mjs';
import {Mongoose as mongoose} from "../db/adapters/Mongoose";
const ObjectID = require('mongodb').ObjectID;

class BaseMongodbRepository extends BaseRepository {
  constructor(collectionName, collectionSchema) {
    super();
    this[collectionName] = mongoose.model(collectionName, collectionSchema);
  }


  static getCaseInsensitiveRegexQuery(param){
    return new RegExp(["^", param, "$"].join(""), "i");
  }

  static parseToObjectId(_id) {
    if (_id instanceof ObjectID) {
      return _id;
    }

    if (_.isString(_id)) {
      return new ObjectID(_id);
    }
    return null;
  }
}

export {BaseMongodbRepository}