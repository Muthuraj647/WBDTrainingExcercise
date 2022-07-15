const express=require('express');
const app=express();
const bodyParser=require('body-parser');
const PORT=8080;
const route=require('./routes/router')

app.use(bodyParser.json());
app.use('/user',route);


//start server
app.listen(PORT,()=>{
    console.log("Application started and Running on Port "+PORT);
});

module.exports= app;