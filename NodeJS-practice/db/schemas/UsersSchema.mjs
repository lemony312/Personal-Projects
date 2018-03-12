import {Mongoose} from "../adapters/Mongoose";

const userRolesMap = {
  TUTOR: "tutor",
  ADMIN: "admin",
  STUDENT: "student"
};

let collectionName = "Users";
let userRoles = [userRolesMap.TUTOR, userRolesMap.ADMIN, userRolesMap.STUDENT];

let userRolesEnum = {
  values: userRoles,
  message: 'enum validator failed for {PATH} with value {VALUE}'
};

let Schema = Mongoose.Schema;

let UsersSchema = new Schema({
  email: String,
  password: String,
  lastName: String,
  firstName: String,
  roles: [{
    type: String,
    enum: userRolesEnum,
    default: userRolesMap.STUDENT
  }]
});

export {UsersSchema}
export {collectionName as CollectionName}
export {userRolesMap as UserRoles}