import express from 'express'
import {UsersController} from "../contollers/UsersController.mjs";

let router = express.Router();


router.get('/', (req, res, next) => {
  new UsersController(req, res, next).getUsers();
});

router.get('/:id', (req, res, next) => {
  new UsersController(req, res, next).getUserById();
});

router.post('/', (req, res, next) => {
  new UsersController(req, res, next).createUser();
});

export {router as UsersRouter}