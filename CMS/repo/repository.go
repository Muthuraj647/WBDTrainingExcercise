package repo

import (
	"log"
	"math"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/model"
	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/pagination"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

var connectionURL = "root:Admin@123@tcp(cms-mysql:3306)/WBDContents"
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

//pagination

func Paginate(value interface{}, pagination *pagination.Pagination, db *gorm.DB) func(db *gorm.DB) *gorm.DB {
	var totalRows int64
	db.Model(value).Count(&totalRows)
	pagination.TotalRows = totalRows
	totalPages := int(math.Ceil(float64(totalRows) / float64(pagination.Limit)))
	pagination.TotalPages = totalPages
	return func(db *gorm.DB) *gorm.DB {
		return db.Offset(pagination.GetOffset()).Limit(pagination.GetLimit())
	}
}
