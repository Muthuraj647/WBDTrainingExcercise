const express=require('express');
const bcrypt=require('bcryptjs');
const model=require('../model/dynamoDB');
const jwt=require('jsonwebtoken');
const JWTKEY="secretKey";

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
    let Password=req.body.password;

    //password validation
    let regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    if(!regularExpression.test(Password))
    {
        res.json({
            message:"Password should be Valid"
        })
        return;
    }
    //console.log("Password before encrypt: "+Password);

    //encrypting
    bcrypt.hash(Password,5)
    .then(encryptedPwd=>{

        let user={
            user_name:req.body.user_name,
            email:req.body.email,
            password:encryptedPwd,
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


    }).catch(err=>{
        console.log("Error happens at Encryption");
        console.error(err)
    })
        
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
                    const token=jwt.sign({id:user},JWTKEY)
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

module.exports={
    createTable,
    signup,
    getAll,
    getUserByName
}