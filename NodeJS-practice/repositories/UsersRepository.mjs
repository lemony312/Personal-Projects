import {UsersSchema, CollectionName as collectionName} from "../db/schemas/UsersSchema";
import {BaseMongodbRepository} from './BaseMongodbRepository.mjs';

class UsersRepository extends BaseMongodbRepository {

  constructor() {
    super(collectionName, UsersSchema);
  }

  getUserById(_id) {
    return this.Users.findById({_id: BaseMongodbRepository.parseToObjectId(_id)}).lean().exec();
  }

  getUserByEmail(email) {
    let emailRegex = BaseMongodbRepository.getCaseInsensitiveRegexQuery(email);
    return this.Users.findOne({email: emailRegex}).exec();
  }

  getUser(query) {
    return this.Users.findOne(query).lean().exec();
  }

  updateUser(_id, userData) {
    let query = {_id: BaseMongodbRepository.parseToObjectId(_id)};
    return this.Users.update(query, userData)
  }

  createUser(userData) {
    let newUser = new this.Users(userData);
    return newUser.save(userData)
  }

  getAllUsers() {
    this.Users.find({}).lean().exec();
  }

}


let usersRepository = new UsersRepository();
export {usersRepository as UsersRepository}