const express=require('express');
const bcrypt=require('bcryptjs');
const model=require('../service/dynamoDB');
const jwt=require('jsonwebtoken');
const secrets=require('../vault');
const JWTKEY=secrets.secret.JWTKEY_NODEJS;
const blocklist=secrets.secret.JWTblocklist;
const auth=require('../middleware/auth')

function home(req,res){
    res.json({
        message: `Hello ${req.user_name} Welcome`
    })
}

function createTable(req,res){
    model.createTable(function(err,data){
        if(err){
            res.json({message:"Error Occured when creating table"});
        }
        else if(typeof(data)=="string"){
            res.json({message:data})
        }
        else{
            res.json({message:"Creadted"})
        }
    });
}

function signup(req,res){ 
    let user={
        user_name:req.body.user_name,
        email:req.body.email,
        password:req.body.password,
        user_roles:req.body.user_roles
    };
  //console.log("Password after encrypt: "+encryptedPwd);

    //adding
    model.addUsers(user,function(err,data){
        if(err){
            res.status(500)
            res.json({message:"Internal Server Error"});
        }
        else{
            res.status(201);
            res.json({
                message:"User Added",
                Details: data
                
            })
        }
    });
}

function getAll(req, res){
    model.getAllUser(function(err,data){
        if(err){
            res.status(500)
            res.json({message:"Internal Server Error"});
        }
        else{
            res.status(200)
            res.json({
                message: `${data.length} Records Found`,
                Details: data
                
            })
        }
    });
}


function getUserByName(req,res){
    let userObj={
        user_name:req.body.user_name,
        password:req.body.password,
        user_role:req.body.user_roles
    }
    
    model.getUserByName(userObj, function(err,data){
        if(!err){
            let encryptedPwd=data.password;
            //console.log(encryptedPwd)
            bcrypt.compare(userObj.password,encryptedPwd)
            .then(doMatch=>{
                if(doMatch){
                    //res.send(`Welcome ${user}`);
                    let token=jwt.sign({name:userObj.user_name,role:userObj.user_role},JWTKEY,{
                        expiresIn: '20m'
                    })
                    token="Bearer "+token;
                    console.log("Token")
                    console.log(token)
                    res.status(200)
                    res.json({
                        message:`Welcome ${data.user_name} Please Keep JWT Token`,
                        Token: token
                    })
                }
                else{
                    res.status(401)
                    res.send({message:"Invalid Password"})
                }
            }).catch(err=>{
                res.status(500)
                res.json({message:"Internal Server Error"});
                console.log("Internal Error with Decryption")
                console.log(err);
            })
            
        }
        else{
            res.status(err)
            res.json({
                message:"Invalid Username"})
        }
    });
}


function changePassword(req,res){

    let user={
        user_name:req.user_name,
        password:req.body.password
    };
  //console.log("Password after encrypt: "+encryptedPwd);

    //adding
    model.changePwd(user,function(err,data){
        if(err){
            res.status(500)
            res.json({message:"Error Occured when Updating user in table"});
        }
        else{
            res.json({
                message:"Password Updated",
                Details: data
                
            })
        }
    });
    
}

function logout(req,res){
    const token=req.body.token || req.query.token|| req.headers["x-access-token"];
    blocklist.push(token);
    auth.removeBlocklist();
    console.log('Blocklist')
    console.log(blocklist);
    res.json({
        message:"Log out!"
    })
    
}



module.exports={
    home,
    createTable,
    signup,
    getAll,
    getUserByName,
    changePassword,
    logout
}