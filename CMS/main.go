package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/routes"
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/service"
)

func main() {
	fmt.Println("CMS Module")
	log.Println("Connecting With DB")
	err := service.ConnectWithDB()
	if err == nil {
		router := routes.RegisterRoutes()
		fmt.Println("CMS ready to Serve...")
		http.ListenAndServe(":8082", router)
	}

}
