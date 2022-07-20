package model

import (
	"github.com/golang-jwt/jwt"
	"gorm.io/gorm"
)

//jwt token
type Claims struct {
	Name string `json:"name"`
	Role string `json:"role"`
	jwt.StandardClaims
}

//Movies table

type Movies struct {
	MovieID      uint    `gorm:"primaryKey;autoIncrement"`
	MovieName    string  `gorm:"not null; unique"`
	ActorName    string  `json:"actorName"`
	DirectorName string  `json:"directorName"`
	ReleaseYear  int     `json:"releaseYear"`
	Language     string  `json:"language"`
	Ratings      float32 `json:"ratings"`
	URL          string  `json:"url"`
	IsActive     bool    `gorm:"default:true"`
	Deleted      gorm.DeletedAt
}
