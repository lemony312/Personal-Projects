 // Dependencies
 var express = require('express');
 var mongoose = require('mongoose');
 // Create export object
 var model = {};
 var todo = mongoose.model("todo");

 model.getAllPosts = function(callback) {
     // model.find({}).lean().limit(1).select("_id").exec(function(err, data) {
     todo.find({}).lean().exec(function(err, data) {
         //  console.log(data);
         if (err) {
             return callback(err);
         }
         callback(null, data);
     });
 };

 model.createPost = function(data, callback) {
     //text: req.body.text,
     todo.create({
         text: data.text,
         done: false
     }, function(err, todo) {
         if (err) {
             return callback(err, null);
         }
         callback(null, todo);
     });
 };

 model.deletePost = function(id, callback) {
     //  _id: req.params.id
     todo.remove({
         _id: id
     }, function(err, data) {
         if (err) {
             return callback(err, null);
         }
         return callback(null, data);
     });
 };

 module.exports = model;