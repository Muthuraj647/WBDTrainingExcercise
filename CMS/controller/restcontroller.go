package controller

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/model"
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/service"
)

func Home(w http.ResponseWriter, r *http.Request) {
	//fmt.Println("Hello CMS User")
	fmt.Println("Hello User you're entry Verified")
	w.Write([]byte("Hello User you're entry Verified"))
}

func InsertMovie(w http.ResponseWriter, r *http.Request) {
	// movie := model.Movies{
	// 	MovieID:      1,
	// 	MovieName:    "MiB",
	// 	ActorName:    "Will Smith",
	// 	DirectorName: "Don't know",
	// 	ReleaseYear:  2002,
	// 	Language:     "English",
	// 	Ratings:      8.5,
	// 	IsActive:     true,
	// }
	movie := model.Movies{}
	json.NewDecoder(r.Body).Decode(&movie)

	err := service.InsertMovie(movie)

	if err != nil {

		w.Write([]byte("Already there"))
	} else {
		json.NewEncoder(w).Encode(movie)
	}

}

func GetAllMovies(w http.ResponseWriter, r *http.Request) {
	movies := service.GetAllMovies()
	if movies == nil {
		w.Write([]byte("No movies found or Error Happened"))
	}
	json.NewEncoder(w).Encode(movies)
}

func GetByMovieId(w http.ResponseWriter, r *http.Request) {

	movieID, er := strconv.Atoi(r.URL.Query()["movieID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		return
	}
	movies, err := service.GeByMovieID(movieID)

	if err != nil {
		w.Write([]byte("Internal Server Error"))
		w.WriteHeader(http.StatusInternalServerError)
	} else {
		json.NewEncoder(w).Encode(movies)
	}
}
