Packages :

Gorilla mux:
    go get -u github.com/gorilla/mux

GORM:    
    go get -u gorm.io/gorm

MySQL Driver:    
    go get -u gorm.io/driver/mysql

JWT:    
    go get -u github.com/golang-jwt/jwt/v4


To build the Application:

I used custom build MySQL image that's running along with Main Application but actually it should be replaced with RDS

Commands to Build:

    docker-compose up

This will build application, mysql and run both containers

To run in detach mode

    docker-compose up -d

To stop running containers and to remove the volumes 

    docker-compose down --remove-orphans --volumes 

Incase need to update the application means after editing try to build again or else it will take already created image, our new update will not be taken

    docker-compose up --build