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
	router.HandleFunc("/delete-movie", middleware.VerifyLogin(controller.DeleteMovie)).Methods("DELETE")
	router.HandleFunc("/update-movie", middleware.VerifyLogin(controller.UpdateMovie)).Methods("PUT")

	//shows

	router.HandleFunc("/insert-show", middleware.VerifyLogin(controller.InsertShow)).Methods("POST")
	router.HandleFunc("/get-all-shows", middleware.VerifyLogin(controller.GetAllShows)).Methods("GET")
	router.HandleFunc("/get-show", middleware.VerifyLogin(controller.GetShowByID)).Methods("GET")
	router.HandleFunc("/update-show", middleware.VerifyLogin(controller.UpdateShow)).Methods("POST")
	router.HandleFunc("/delete-show", middleware.VerifyLogin(controller.DeleteShow)).Methods("DELETE")

	//Seasons

	router.HandleFunc("/insert-season", middleware.VerifyLogin(controller.InsertSeason)).Methods("POST")
	router.HandleFunc("/get-all-seasons", middleware.VerifyLogin(controller.GetAllSeasons)).Methods("GET")
	router.HandleFunc("/get-season", middleware.VerifyLogin(controller.GetSeasonByID)).Methods("GET")
	router.HandleFunc("/update-season", middleware.VerifyLogin(controller.UpdateSeason)).Methods("POST")
	router.HandleFunc("/delete-season", middleware.VerifyLogin(controller.DeleteSeason)).Methods("DELETE")

	//Episodes
	router.HandleFunc("/insert-episode", middleware.VerifyLogin(controller.InsertEpisode)).Methods("POST")
	router.HandleFunc("/get-all-episode", middleware.VerifyLogin(controller.GetAllEpisode)).Methods("GET")
	router.HandleFunc("/get-episode", middleware.VerifyLogin(controller.GetEpisodeByID)).Methods("GET")
	router.HandleFunc("/update-episode", middleware.VerifyLogin(controller.UpdateEpisode)).Methods("POST")
	router.HandleFunc("/delete-episode", middleware.VerifyLogin(controller.DeleteEpisode)).Methods("DELETE")
	return router
}
