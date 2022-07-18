const aws=require('aws-sdk')
aws.config.update({
    region: "local",
    endpoint: "http://localhost:8000 "
});

const dynamoDB=new aws.DynamoDB();
const docClient=new aws.DynamoDB.DocumentClient();
const tableName= "Users";

//create table

function createTable(callback){

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
                callback(err,data);
                
                } else {
                console.log("Created table", data);
                callback(err,data);
                }
        });

    }).catch(reason=>{
        console.log(reason);
        callback(null,reason);
        
    })
        
}


//adding values

function addUsers(user, callback){

    const params={
        TableName: tableName,
        Item: user
    };
    try {
        docClient.put(params, function(err,data){
            if (err) {
                console.log("Unable to add User");
                callback(err,data);
                
              } else {
                console.log(`Added ${user.user_name}`);
                callback(err,params.Item);
            }
    
        });

    } catch (error) {
        console.error("Unable to add User");
        callback(err,data);
    }
    
}


function getAllUser(callback){
    const params={
        TableName: tableName
    };

    docClient.scan(params,function(err,data){
    if (err) {
        console.error("Unable to find Users", err);
        callback(err,data);
        } else {
        console.log(`Found ${data.Count} Users`);
        console.log(data.Items);
        const Data=data.Items;
        callback(err,Data);
        }
    });
}


//getUser

function getUserByName(user, callback){

    const params={
        TableName: tableName,
        KeyConditionExpression: "user_name =:name",
        ExpressionAttributeValues:{
            ":name":user.user_name
        },
        ProjectionExpression: "user_name, password, user_roles"
    };

    docClient.query(params, function(err,data){
        if(err){
            console.log("Error with finding the user");
            callback(err,data);
        }
        else if(data.Count!=0){
            console.log("Found the User ")
            console.log("Roles")
            console.log(data.Items[0].user_roles)
            callback(err,data.Items[0]);  

            //callback(401,null);
        }
        else{
            callback(404,null);
        }
    });

}

function changePwd(user,callback){
    const params={
        TableName: tableName,
        Item: user
    };

    docClient.put(params, function(err,data){
        if (err) {
            console.error("Unable to Update User", err);
            callback(err,data);
            
          } else {
            console.log(`Password Changed for ${user.user_name}`);
            const details=data.Item;
            callback(err,details);
        }

    });
}

module.exports={
    createTable,
    addUsers,
    getAllUser,
    getUserByName,
    changePwd
}