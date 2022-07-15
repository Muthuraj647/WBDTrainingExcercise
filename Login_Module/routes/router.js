const express=require('express');
const route=express.Router();
const controller=require('../controller/controller')
const auth=require('../middleware/auth');

route.get("/home",auth.verifyToken,controller.home)

route.post("/signup",auth.validateAndEncryptPwd,controller.signup);

route.get('/create-table',controller.createTable);

route.get('/get-all',auth.verifyToken,controller.getAll)

route.post('/login',controller.getUserByName)

route.post('/change-password',auth.verifyToken,auth.validateAndEncryptPwd, controller.changePassword)

route.get('/logout',auth.verifyToken,controller.logout)

module.exports=route;