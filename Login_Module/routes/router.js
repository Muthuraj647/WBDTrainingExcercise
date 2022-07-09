const express=require('express');
const route=express.Router();
const controller=require('../controller/controller')

route.post("/signup",controller.signup);

route.get('/createTable',controller.createTable);

route.get('/getAll',controller.getAll)


module.exports=route;