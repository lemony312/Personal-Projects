import {BaseController} from "./BaseController";
import {UsersService as usersService} from "../services/UsersService";


class UsersController extends BaseController {

  constructor(req, res, next) {
    super(req, res, next)
  }

  getUserById() {
    let params = this.request.getParams();
    this.ok(params);
  }

  getUsers(){
    let users = usersService.getAllUsers();
    this.ok(users);
  }

  createUser() {

  }

  // updateCurrentUser() {
  //   let mapper = new UpdateUserMapper();
  //   let validator = new UpdateUsersValidator();
  //   return validator.validate((this.getRequest())).then(() => {
  //     return mapper.map(this.getRequest())
  //   }).then((fields) => {
  //     return usersService.updateUser(fields)
  //   }).catch((error) => {
  //     this.err(error)
  //   })
  // }
  //
  // getGradeLevels() {
  //   return usersService.getGradeLevels().then((gradeLevels) => {
  //     return this.ok(gradeLevels)
  //   }).catch((err) => {
  //     return this.err(err)
  //   })
  // }
  //
  // getCurrentUser() {
  //   return usersService.getUserWithId(this.getLoggedInUserId()).then((user) => {
  //     return this.ok(user)
  //   }).catch((error) => {
  //     return this.err(error)
  //   });
  // }


}

export {UsersController}