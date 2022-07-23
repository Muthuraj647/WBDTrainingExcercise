package repo

import (
	"log"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/model"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

var connectionURL = "root:Admin@123@tcp(127.0.0.1:3306)/WBDContents"
var err error
var conn *gorm.DB

func ConnectWithDB() (*gorm.DB, error) {
	conn, err = gorm.Open(mysql.Open(connectionURL), &gorm.Config{})
	if err != nil {
		log.Fatal("Cannot Connect with DB")
		return conn, err
	}
	log.Println("Connected With DB...")
	movies := model.Movies{}
	shows := model.Show{}
	screenPlay := model.ScreenPlay{}
	seasons := model.Season{}
	episodes := model.Episode{}
	conn.AutoMigrate(&movies, &shows, &screenPlay, &seasons, &episodes)
	return conn, err
}
