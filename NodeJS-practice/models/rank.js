 // Dependencies
 var express = require('express');
 var mongoose = require('mongoose');
 // Create export object
 var model = {};
 var rank = mongoose.model("rank");

 model.getAllRanks = function(callback) {
     rank.find().populate("_todo _item").exec(function(err, data) {
         if (err || !data) {
             return callback(err, null);
         }
         //  console.log("getting todo", data._todo, ",item ", data._item, "and ranking", data.ranking);
         callback(null, data);
     });
 };

 model.createRank = function(data, callback) {
     //text: req.body.text,
     rank.create({
         ranking: data.ranking,
         _todo: data.todoId,
         _item: data.itemId
     }, function(err, todo) {
         //  console.log("err", err);
         if (err) {
             return callback(err);
         }
         callback(null);
     });
 };

 model.deleteRank = function(id, callback) {
     //  _id: req.params.id
     rank.remove({
         _id: id
     }, function(err, data) {
         if (err) {
             return callback(err);
         }
         return callback(null);
     });
 };

 module.exports = model;