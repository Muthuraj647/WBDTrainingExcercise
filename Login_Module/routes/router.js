const express=require('express');
const route=express.Router();
const controller=require('../controller/controller')
const auth=require('../middleware/auth');

route.get("/home",auth.verifyToken,controller.home)

route.post("/signup",auth.validateAndEncryptPwd,controller.signup);

route.get('/createTable',auth.verifyToken,controller.createTable);

route.get('/getAll',auth.verifyToken,controller.getAll)

route.post('/login',controller.getUserByName)

route.post('/changePassword',auth.verifyToken,auth.validateAndEncryptPwd, controller.changePassword)

route.get('/logout',auth.verifyToken,controller.logout)

module.exports=route;