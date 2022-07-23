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

//delete

func DeleteMovie(w http.ResponseWriter, r *http.Request) {
	movieID, er := strconv.Atoi(r.URL.Query()["movieID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		return
	}
	err, movie := service.DeleteMovie(movieID)

	if err != nil {
		w.Write([]byte("Not Found"))
		w.WriteHeader(http.StatusNotFound)
	} else {
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(movie)
	}
}

//update movie

func UpdateMovie(w http.ResponseWriter, r *http.Request) {
	movie := model.Movies{}
	json.NewDecoder(r.Body).Decode(&movie)

	err, data := service.UpdateMovie(movie)

	if err != nil {

		w.Write([]byte("Already there"))
	} else {
		json.NewEncoder(w).Encode(data)
	}

}

//shows

func InsertShow(w http.ResponseWriter, r *http.Request) {
	show := model.Show{}

	json.NewDecoder(r.Body).Decode(&show)

	err := service.InsertShow(show)

	if err != nil {
		w.WriteHeader(http.StatusConflict)
		w.Write([]byte("Problem with Inserting Show"))
	} else {
		w.WriteHeader(http.StatusOK)
		w.Write([]byte("Added"))
	}
}

//getAll

func GetAllShows(w http.ResponseWriter, r *http.Request) {
	shows, err := service.GetaAllShows()
	if err != nil {
		w.Write([]byte("Problem with GetAll Shows"))
		w.WriteHeader(http.StatusNoContent)

	} else {
		w.WriteHeader(http.StatusFound)
		json.NewEncoder(w).Encode(shows)
	}
}

//getById

func GetShowByID(w http.ResponseWriter, r *http.Request) {
	showID, er := strconv.Atoi(r.URL.Query()["showID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		w.WriteHeader(http.StatusNotAcceptable)
		return
	}
	show, err := service.GetShowById(showID)

	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte("Not Found"))
		return
	}
	json.NewEncoder(w).Encode(show)
}

//update

func UpdateShow(w http.ResponseWriter, r *http.Request) {
	show := model.Show{}

	json.NewDecoder(r.Body).Decode(&show)

	data, err := service.UpdateShow(show)

	if err != nil {
		w.WriteHeader(http.StatusNotModified)
		w.Write([]byte("Not Updated"))
	} else {
		json.NewEncoder(w).Encode(data)
		w.WriteHeader(http.StatusOK)
	}
}

func DeleteShow(w http.ResponseWriter, r *http.Request) {
	showID, er := strconv.Atoi(r.URL.Query()["showID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		return
	}
	err, show := service.DeleteShow(showID)

	if err != nil {
		w.Write([]byte("Not Found"))
		w.WriteHeader(http.StatusNotFound)
	} else {
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(show)
	}
}

//Seasons

func InsertSeason(w http.ResponseWriter, r *http.Request) {
	season := model.Season{}

	json.NewDecoder(r.Body).Decode(&season)

	err := service.InsertSeason(season)

	if err != nil {
		w.WriteHeader(http.StatusConflict)
		w.Write([]byte("Problem with Inserting season"))
	} else {
		w.WriteHeader(http.StatusOK)
		w.Write([]byte("Added"))
	}
}

//getAll

func GetAllSeasons(w http.ResponseWriter, r *http.Request) {
	season, err := service.GetaAllSeason()
	if err != nil {
		w.Write([]byte("Problem with GetAll season"))
		w.WriteHeader(http.StatusNoContent)

	} else {
		w.WriteHeader(http.StatusFound)
		json.NewEncoder(w).Encode(season)
	}
}

//getById

func GetSeasonByID(w http.ResponseWriter, r *http.Request) {
	seasonID, er := strconv.Atoi(r.URL.Query()["seasonID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		w.WriteHeader(http.StatusNotAcceptable)
		return
	}
	season, err := service.GetSeasonById(seasonID)

	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte("Not Found"))
		return
	}
	json.NewEncoder(w).Encode(season)
}

//update

func UpdateSeason(w http.ResponseWriter, r *http.Request) {
	season := model.Season{}

	json.NewDecoder(r.Body).Decode(&season)

	data, err := service.UpdateSeason(season)

	if err != nil {
		w.WriteHeader(http.StatusNotModified)
		w.Write([]byte("Not Updated"))
	} else {
		json.NewEncoder(w).Encode(data)
		w.WriteHeader(http.StatusOK)
	}
}

func DeleteSeason(w http.ResponseWriter, r *http.Request) {
	seasonID, er := strconv.Atoi(r.URL.Query()["seasonID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		return
	}
	err, season := service.DeleteSeason(seasonID)

	if err != nil {
		w.Write([]byte("Not Found"))
		w.WriteHeader(http.StatusNotFound)
	} else {
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(season)
	}
}

//Episodes

func InsertEpisode(w http.ResponseWriter, r *http.Request) {
	episode := model.Episode{}

	json.NewDecoder(r.Body).Decode(&episode)

	err := service.InsertEpisode(episode)

	if err != nil {
		w.WriteHeader(http.StatusConflict)
		w.Write([]byte("Problem with Inserting episode"))
	} else {
		w.WriteHeader(http.StatusOK)
		w.Write([]byte("Added"))
	}
}

//getAll

func GetAllEpisode(w http.ResponseWriter, r *http.Request) {
	episode, err := service.GetaAllEpisode()
	if err != nil {
		w.Write([]byte("Problem with GetAll episode"))
		w.WriteHeader(http.StatusNoContent)

	} else {
		w.WriteHeader(http.StatusFound)
		json.NewEncoder(w).Encode(episode)
	}
}

//getById

func GetEpisodeByID(w http.ResponseWriter, r *http.Request) {
	episodeID, er := strconv.Atoi(r.URL.Query()["episodeID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		w.WriteHeader(http.StatusNotAcceptable)
		return
	}
	episode, err := service.GetEpisodeById(episodeID)

	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte("Not Found"))
		return
	}
	json.NewEncoder(w).Encode(episode)
}

//update

func UpdateEpisode(w http.ResponseWriter, r *http.Request) {
	episode := model.Episode{}

	json.NewDecoder(r.Body).Decode(&episode)

	data, err := service.UpdateEpisodes(episode)

	if err != nil {
		w.WriteHeader(http.StatusNotModified)
		w.Write([]byte("Not Updated"))
	} else {
		json.NewEncoder(w).Encode(data)
		w.WriteHeader(http.StatusOK)
	}
}

func DeleteEpisode(w http.ResponseWriter, r *http.Request) {
	episodeID, er := strconv.Atoi(r.URL.Query()["episodeID"][0])
	if er != nil {
		w.Write([]byte("Give Correct ID"))
		return
	}
	err, episode := service.DeleteEpisode(episodeID)

	if err != nil {
		w.Write([]byte("Not Found"))
		w.WriteHeader(http.StatusNotFound)
	} else {
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(episode)
	}
}
