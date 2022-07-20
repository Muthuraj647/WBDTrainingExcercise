package routes

import (
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/controller"
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/middleware"
	"github.com/gorilla/mux"
)

func RegisterRoutes() *mux.Router {
	var router = mux.NewRouter()
	router.HandleFunc("/home", middleware.VerifyLogin(controller.Home)).Methods("GET")
	router.HandleFunc("/insert-movie", middleware.VerifyLogin(controller.InsertMovie)).Methods("POST")
	router.HandleFunc("/get-all-movies", middleware.VerifyLogin(controller.GetAllMovies)).Methods("GET")
	router.HandleFunc("/get-movie", middleware.VerifyLogin(controller.GetByMovieId)).Methods("GET")

	return router
}
