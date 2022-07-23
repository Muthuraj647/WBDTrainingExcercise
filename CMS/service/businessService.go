package service

import (
	"fmt"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/model"
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/repo"
	"gorm.io/gorm"
)

var conn *gorm.DB

func ConnectWithDB() error {
	db, err := repo.ConnectWithDB()
	if err != nil {
		fmt.Println("Error Message")
		fmt.Println(err)
		return err
	}
	conn = db
	return nil

}

//insert
func InsertMovie(movie model.Movies) error {
	fmt.Println("Iserting")
	err := conn.Create(&movie)
	if err.Error != nil {
		fmt.Println("Error when creating...")
		fmt.Println(err.Error)
		//log.Fatalln(err)
		return err.Error
	}
	fmt.Println("Movies Added")
	return nil
}

//getall

func GetAllMovies() []model.Movies {
	movies := []model.Movies{}

	err := conn.Where("is_active=?", true).Find(&movies)
	if err.Error != nil {
		fmt.Println("Error when Querying...")
		fmt.Println(err.Error)
		//log.Fatalln(err)
		return nil
	}
	return movies
}

//getById

func GeByMovieID(movieID int) (model.Movies, error) {
	movie := model.Movies{}

	err := conn.First(&movie, movieID)

	if err.Error != nil {
		fmt.Println("Error with finding the movie")
		fmt.Println(err.Error)
		return movie, err.Error
	}
	return movie, nil
}

//deleteMovie

func DeleteMovie(movieID int) (error, model.Movies) {
	movie := model.Movies{}

	err := conn.Model(&movie).Where("movie_id=?", movieID).Update("is_active", false)

	if err.Error != nil {
		fmt.Println("Error With Deleting")
		fmt.Println(err.Error)
		return err.Error, model.Movies{}
	}

	fmt.Println("Deleted")

	return nil, movie
}

//update movie

func UpdateMovie(movie model.Movies) (error, model.Movies) {

	err := conn.Save(&movie)

	if err.Error != nil {
		fmt.Println("Can't Update")
		return err.Error, model.Movies{}
	}
	movies := model.Movies{}
	err = conn.First(&movies, movie.MovieID)
	if err.Error != nil {
		fmt.Println("Error with finding")
		return err.Error, movies
	}
	return nil, movies
}

//Show

//insertShow

func InsertShow(show model.Show) error {
	err := conn.Create(&show).Error

	if err != nil {
		fmt.Println("Problem with Creating Show " + show.ShowName)
		return err
	}

	fmt.Println(show.ShowName + " is Created ")
	return nil
}

//getAllShows

func GetaAllShows() ([]model.Show, error) {
	shows := []model.Show{}
	err := conn.Where("is_active=?", true).Preload("Screenplay").Find(&shows).Error

	if err != nil {
		fmt.Println("Problem with Querying")
		return shows, err
	}
	return shows, nil
}

//getById

func GetShowById(showID int) (model.Show, error) {
	show := model.Show{}

	err := conn.Preload("Screenplay").Preload("Seasons").First(&show, showID).Error

	if err != nil {
		fmt.Printf("Problem with Finding the Show with ID %d\n", showID)
		return show, err
	}

	fmt.Println("Found")
	return show, nil
}

//update

func UpdateShow(show model.Show) (model.Show, error) {

	showFromDB := model.Show{}
	conn.First(&showFromDB)
	showFromDB = show
	err := conn.Save(&showFromDB).Error
	if err != nil {
		fmt.Printf("Problem with Updaing the Show %s\n", show.ShowName)
		return show, err
	}

	fmt.Print("Updated")
	return showFromDB, nil

}

func DeleteShow(showID int) (error, model.Show) {
	show := model.Show{}

	err := conn.Model(&show).Where("show_id=?", showID).Update("is_active", false)

	if err.Error != nil {
		fmt.Println("Error With Deleting")
		fmt.Println(err.Error)
		return err.Error, model.Show{}
	}

	fmt.Println("Deleted")

	return nil, show
}

//Seasons

func InsertSeason(season model.Season) error {
	err := conn.Create(&season).Error

	if err != nil {
		fmt.Println("Problem with Creating Season " + season.SeasonName)
		return err
	}

	fmt.Println(season.SeasonName + " is Created ")
	return nil
}

//getAllShows

func GetaAllSeason() ([]model.Season, error) {
	season := []model.Season{}
	err := conn.Where("is_active=?", true).Find(&season).Error

	if err != nil {
		fmt.Println("Problem with Querying")
		return season, err
	}
	return season, nil
}

//getById

func GetSeasonById(seasonID int) (model.Season, error) {
	season := model.Season{}

	err := conn.Preload("Episodes").First(&season, seasonID).Error

	if err != nil {
		fmt.Printf("Problem with Finding the season with ID %d\n", seasonID)
		return season, err
	}

	fmt.Println("Found")
	return season, nil
}

//update

func UpdateSeason(season model.Season) (model.Season, error) {

	seasonFromDB := model.Season{}
	conn.First(&seasonFromDB)
	seasonFromDB = season
	err := conn.Save(&seasonFromDB).Error
	if err != nil {
		fmt.Printf("Problem with Updaing the season %s\n", season.SeasonName)
		return season, err
	}

	fmt.Print("Updated")
	return seasonFromDB, nil

}

func DeleteSeason(seasonID int) (error, model.Season) {
	season := model.Season{}

	err := conn.Model(&season).Where("season_id=?", seasonID).Update("is_active", false)

	if err.Error != nil {
		fmt.Println("Error With Deleting")
		fmt.Println(err.Error)
		return err.Error, model.Season{}
	}

	fmt.Println("Deleted")

	return nil, season
}

//episodes

func InsertEpisode(episode model.Episode) error {
	err := conn.Create(&episode).Error

	if err != nil {
		fmt.Println("Problem with Creating episode " + episode.EpisodeName)
		return err
	}

	fmt.Println(episode.EpisodeName + " is Created ")
	return nil
}

//getAllShows

func GetaAllEpisode() ([]model.Episode, error) {
	episode := []model.Episode{}
	err := conn.Where("is_active=?", true).Find(&episode).Error

	if err != nil {
		fmt.Println("Problem with Querying")
		return episode, err
	}
	return episode, nil
}

//getById

func GetEpisodeById(episodeID int) (model.Episode, error) {
	episode := model.Episode{}

	err := conn.First(&episode, episodeID).Error

	if err != nil {
		fmt.Printf("Problem with Finding the episode with ID %d\n", episodeID)
		return episode, err
	}

	fmt.Println("Found")
	return episode, nil
}

//update

func UpdateEpisodes(episode model.Episode) (model.Episode, error) {

	episodeFromDB := model.Episode{}
	conn.First(&episodeFromDB)
	episodeFromDB = episode
	err := conn.Save(&episodeFromDB).Error
	if err != nil {
		fmt.Printf("Problem with Updaing the season %s\n", episode.EpisodeName)
		return episode, err
	}

	fmt.Print("Updated")
	return episodeFromDB, nil

}

func DeleteEpisode(episodeID int) (error, model.Episode) {
	episode := model.Episode{}

	err := conn.Model(&episode).Where("episode_id=?", episodeID).Update("is_active", false)

	if err.Error != nil {
		fmt.Println("Error With Deleting")
		fmt.Println(err.Error)
		return err.Error, model.Episode{}
	}

	fmt.Println("Deleted")

	return nil, episode
}
