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

	err := conn.Find(&movies)
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
