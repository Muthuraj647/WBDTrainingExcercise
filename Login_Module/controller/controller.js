const express=require('express');
const bcrypt=require('bcryptjs');
const model=require('../model/dynamoDB');

let methods={

    createTable:function(req,res){
        model.createTable(res);
    },

    signup: function(req,res){
            let Password=req.body.password;
            console.log("Password before encrypt: "+Password);
            //encrypting
            bcrypt.hash(Password,5)
            .then(encryptedPwd=>{

                let user={
                    user_name:req.body.user_name,
                    email:req.body.email,
                    paasword:encryptedPwd,
                    roles:req.body.roles
                };

                console.log("Password after encrypt: "+encryptedPwd);
                model.addUsers(user,res);
            }).catch(err=>{
                console.log("Error happens at Encryption");
                console.error(err)
            })
            
            },

    getAll: function(req, res){
                model.getAllUser(res);
            }
}

module.exports=methods;