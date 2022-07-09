const aws=require('aws-sdk')
const express=require('express');

aws.config.update({
    region: "local",
    endpoint: "http://localhost:8000 "
});

const dynamoDB=new aws.DynamoDB();
const docClient=new aws.DynamoDB.DocumentClient();
const tableName="Users";

//create table

function createTable(res){

    let processTables=()=>{
        return new Promise((resolve,reject)=>{
            dynamoDB.listTables(function(err,data){
                if(err)
                {
                    //console.log("Error in listing");
                    reject('Error with listing..');
                }
                else{
                    if(data.TableNames.includes(tableName)){
                        //console.log("Table Already Exists...");
                        reject("Already there");
                    }
                    else
                        resolve(true);
                }
            })
        })
    }


    

    const params ={
        TableName: tableName,
        KeySchema: [
            { AttributeName: "user_name", KeyType:"HASH"}
        ],
        AttributeDefinitions: [
            { AttributeName: "user_name", AttributeType: "S"}
        ],
        ProvisionedThroughput: {
            ReadCapacityUnits: 10,
            WriteCapacityUnits: 10
        }
    };


    processTables().then(res=>{

        dynamoDB.createTable(params, function(err,data){
            if (err) {
                console.error("Unable to create table", err);
                res.json({
                    message:"Unable to create table"
                })
                
                } else {
                console.log("Created table", data);
                res.json({
                    message:"Table Created"
                })
                }
        });

    }).catch(reason=>{
        console.log(reason);
        res.json({
            message:reason
        })
        
    })
        
}


//adding values

function addUsers(user,res){

    const params={
        TableName: tableName,
        Item: user
    };

    docClient.put(params, function(err,data){
        if (err) {
            console.error("Unable to add User", err);
            res.json({
                message:"Unable to add User"
            });
            
          } else {
            console.log(`Added ${user.user_name}`);
            const details=data.Item;
            res.json({
                message: "User Added",
                UserDetails: details

            })
        }

    });
}


function getAllUser(res){
    const params={
        TableName: tableName
    };

    docClient.scan(params,function(err,data){
    if (err) {
        console.error("Unable to find Users", err);
        res.json({
            message:"Unable to List Users"
        });
        } else {
        console.log(`Found ${data.Count} Users`);
        console.log(data.Items);
        const Data=data.Items;
        res.json({
            message: "User List",
            UserDetails: Data

        })
        }
    });
}


module.exports={
    createTable,
    addUsers,
    getAllUser
}