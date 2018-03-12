import config from 'config'
import mongoose from 'mongoose'

mongoose.Promise = global.Promise;

let mongoUrl = config.get('mongoUrl');

mongoose.connect(mongoUrl);
export {mongoose as Mongoose}