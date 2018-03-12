 // Dependencies
 var express = require('express');
 var mongoose = require('mongoose');

 // Create export object
 var model = {};
 var item = mongoose.model("item");

 model.getAllItems = function(callback) {
     //  .select("text")
     item.findOne({}).exec(function(err, data) {
         if (err || !data) {
             return callback(err, null);
         }
         callback(null, data);
     });
 };

 model.getItem = function(data, callback) {
     //  .select("text")
     var query = {
         _todo: data.todoId,
         _rank: data.rankId,
     }
     item.find(query).exec(function(err, data) {
         if (err || !data) {
             return callback(err, null);
         }
         callback(null, data);
     });
 };

 model.createItem = function(data, callback) {
     item.create({
         _todo: data.todoId,
         _rank: data.rankId,
         val: data.val
     }, function(err, todo) {
         if (err) {
             return callback(err);
         }
         callback(null);
     });
 };

 model.deleteItem = function(id, callback) {
     item.remove({
         _id: id
     }, function(err, data) {
         if (err) {
             return callback(err);
         }
         return callback(null);
     });
 };


 module.exports = model;