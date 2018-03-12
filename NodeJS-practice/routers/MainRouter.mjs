import express from 'express'
import {UsersRouter as usersRouter} from "./UsersRouter";

let router = express.Router();

router.use('/users', usersRouter);



export {router as MainRouter}