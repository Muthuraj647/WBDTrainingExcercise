const express=require('express');
const bcrypt=require('bcryptjs');
const model=require('../model/dynamoDB');
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
        roles:req.body.roles
    };
  //console.log("Password after encrypt: "+encryptedPwd);

    //adding
    model.addUsers(user,function(err,data){
        if(err){
            res.json({message:"Error Occured when adding user in table"});
        }
        else{
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
            res.json({message:"Error Occured when Querying table"});
        }
        else{
            res.json({
                message: `${data.length} Records Found`,
                Details: data
                
            })
        }
    });
}


function getUserByName(req,res){

    let user=req.body.user_name;
    let password=req.body.password;
    
    model.getUserByName(user, function(err,data){
        if(!err){
            let encryptedPwd=data[0].password;
            bcrypt.compare(password,encryptedPwd)
            .then(doMatch=>{
                if(doMatch){
                    //res.send(`Welcome ${user}`);
                    const token=jwt.sign({id:user},JWTKEY,{
                        expiresIn: '5m'
                    })
                    console.log("Token")
                    console.log(token)
                    res.json({
                        message:`Welcome ${user} Please Keep JWT Token`,
                        Token: token
                    })
                }
                else{
                    res.send("Wrong credentials")
                }
            }).catch(err=>{
                console.log("Internal Error with Decryption")
                console.log(err);
            })
            
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