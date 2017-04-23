// set up ========================
var express  = require('express');
var app      = express();                               // create our app w/ express
var mongoose = require('mongoose');                     // mongoose for mongodb
var morgan = require('morgan');             // log requests to the console (express4)
var bodyParser = require('body-parser');    // pull information from HTML POST (express4)
var methodOverride = require('method-override'); // simulate DELETE and PUT (express4)

mongoose.connect('mongodb://todopractice:a1234567@ds115071.mlab.com:15071/todopractice');     // connect to mongoDB database on modulus.io

app.use(express.static(__dirname + '/public'));                 // set the static files location /public/img will be /img for users
app.use(morgan('dev'));                                         // log every request to the console
app.use(bodyParser.urlencoded({'extended':'true'}));            // parse application/x-www-form-urlencoded
app.use(bodyParser.json());                                     // parse application/json
app.use(bodyParser.json({ type: 'application/vnd.api+json' })); // parse application/vnd.api+json as json
app.use(methodOverride());


// define model and scheme =================

var Schema = mongoose.Schema;
var todo = new Schema({
        text : String
    },
    {
        collection: 'todocollection' //forces collection name to change to whateve I want
    })

model = mongoose.model("Todos", todo);

function getAllToDo(model){
    response = {
        error: true,
        re: undefined
    };
    model.find({}, function(err, data){
        if (err){
            response.re = err;
        }
        // console.log(data);
        // res.json(data);
        response.error = false;
        response.re = data
        console.log(response);
    });
    console.log(response);
    return response;
}

// create todo and send back all todos after creation
app.post('/api/todos', function(req, res) {
    // create a todo, information comes from AJAX request from Angular
    model.create({
        text : req.body.text,
        done : false
    }, function(err, todo) {
        if (err)
            res.send(err);

        // get and return all the todos after you create another
        re = getAllToDo(model);
        if (!re.error)
            res.json(re.re)
        res.send(re.re)
    });
});

app.get('/api/todos', function(req, res) {
    re = getAllToDo(model);
    if (!re.error)
        res.json(re.re)
    res.send(re.re)
});

app.delete('/api/todos/:id', function(req, res) {
    mode.remove({
        _id : req.params.id
    }, function(err, data){
        if (err){
            res.send(err)
        }
        if (!re.error)
            res.json(re.re)
        res.send(re.re)
    });
});

// application -------------------------------------------------------------
app.get('', function(req, res) {
    res.sendfile('./public/index.html'); // load the single view file (angular will handle the page changes on the front-end)
});

// listen (start app with node server.js) ======================================
app.listen(8080);
console.log("App listening on port 8080");

