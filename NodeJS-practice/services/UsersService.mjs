import {UsersRepository as usersRepository} from "../repositories/UsersRepository";
import {Error, ErrorTypes} from "../lib/errors/Error.mjs";
import _ from 'lodash'
// import bcrypt from "bcryptjs";
// import {UserFields} from "../controllers/mappers/CreateUserMapper.mjs";
// import {AuthService as authService} from "./AuthService.mjs";
// import {UserRoles} from "../db/schemas/UsersSchema.mjs";

class UsersService {

  getUserWithId(_id) {
    return usersRepository.getUserById(_id)
      .then((user) => {
        return (_.isEmpty(user)) ?
          Promise.reject(new Error(ErrorTypes.USER_DOES_NOT_EXIST)) :
          Promise.resolve(user);
      })
  }

  createUser(userData) {
    let email = userData[UserFields.EMAIL];
    let password = userData[UserFields.PASSWORD];
    let code = userData[UserFields.ACCESS_CODE];
    let requestedRole = userData[UserFields.REQUESTED_ROLE];
    let schoolID = userData[UserFields.SCHOOL];
    let firstName = userData[UserFields.FIRST_NAME];
    let lastName = userData[UserFields.LAST_NAME];
    let gradeLevel = userData[UserFields.GRADE_LEVEL];

    let validatedRoles = [];
    let existingAccessCode = null;

    return authService.getRolesForAccessCode(code, requestedRole).then(([validatedRolesArray, accessCodeObject]) => {
      validatedRoles = validatedRolesArray;
      existingAccessCode = accessCodeObject;
      return usersRepository.getUserByEmail(email)
    }).then((user) => {
      if (user) {
        return Promise.reject(new Error(ErrorTypes.USER_EMAIL_EXISTS))
      } else {

        let tokenExpirationDate = authService.getNewExpirationDate();

        let newUserData = {
          email: email,
          password: bcrypt.hashSync(password, 8),
          roles: validatedRoles,
          firstName: firstName,
          lastName: lastName,

          accessToken: {
            accessToken: authService.generateAccessToken(email, tokenExpirationDate),
            expirationDate: tokenExpirationDate
          }
        };

        if (validatedRoles.includes(UserRoles.STUDENT)) {
          newUserData.gradeLevel = gradeLevel;
          newUserData.school = schoolID;
        }

        return usersRepository.createUser(newUserData)
      }
    }).then((user) => {
      if (existingAccessCode) {
        authService.markAccessCodeUsed(existingAccessCode, user._id);
      }
      return usersRepository.getUserById(user._id);
    });

  }

  updateUser(userId, userData) {
    return usersRepository.updateUser(userId, userData);
  }

  getAllUsers() {
    return usersRepository.getAllUsers();
  }

}

let usersService = new UsersService();
export {usersService as UsersService}