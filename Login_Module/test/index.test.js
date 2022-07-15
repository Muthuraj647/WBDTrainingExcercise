const app=require('../index');
const supertest = require('supertest');

//signup
describe("signup API", ()=>{
    //positive result
    test("POST /user/signup", async ()=>{
        let user={
            user_name:"Muthuraj R",
            email:"muthu@gmail.com",
            password:"Admin@123",
            roles: ["Admin","User"]
        };
        await supertest(app).post("/user/signup")
        .send(user)
        .expect(201)
        .then((res)=>{
            expect(res.body.message).toMatch("User Added");
            expect(res.body.Details.password).not.toMatch("Admin@123")
        })
    })
    //-ve
    test("POST /user/signup", async ()=>{
         let user={
             username:"Muthuraj R",
             email:"muthu@gmail.com",
             password:"Admin@123",
             roles: ["Admin","User"]
         };
         await supertest(app).post("/user/signup")
         .send(user)
         .expect(500)
     })


})


//login
 describe("Login API", ()=>{
     //+ve
     
     test("POST user/login", async ()=>{
         let user={
             user_name:"Muthuraj R",
             password:"Admin@123"
         };
         await supertest(app).post("/user/login")
         .send(user)
         .expect(200)
         .then((res)=>{
            let regex=/Bearer.*/
            expect(res.body.Token).toMatch(regex);
            token=res.body.Token
         })
     })

     //-ve

     test("POST user/login", async()=>{
        let user={
            user_name:"Muthuraj",
            password:"Admin@123"
        };
        await supertest(app).post("/user/login")
        .send(user)
        .expect(404)
        .then((res)=>{
            expect(res.body.message).toMatch("Invalid Username")
        })
     })
     //password
     test("POST user/login", async()=>{
        let user={
            user_name:"Muthuraj R",
            password:"Admin@12"
        };
        await supertest(app).post("/user/login")
        .send(user)
        .expect(401)
        .then((res)=>{
            expect(res.body.message).toMatch("Invalid Password")
        })
     })

 })

 //changePassword

 