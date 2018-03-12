import express from 'express'
import path from 'path'
import cookieParser from "cookie-parser"
import bodyParser from "body-parser"

import {MainRouter as mainRouter} from "./routers/MainRouter.mjs";

const app = express();
let __dirname = path.resolve();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(cookieParser());

app.set('views', path.join(__dirname, '/views'));
app.set('view engine', 'jade');

app.get('/', function (req, res, next) {
  res.send("ᒡ◯ᵔ◯ᒢ")
});

app.all('*', mainRouter);

app.use(function (req, res, next) {
  const err = new Error('Not Found');
  err.status = 404;
  next(err);
});

app.use(function (err, req, res, next) {
  res.locals.message = err;
  res.locals.error = req.app.get('env') !== 'production' ? err : {};
  let code = err.status || 500;
  res.status(code).json({res: err});
});

export {app}