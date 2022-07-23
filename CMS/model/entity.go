package model

import (
	"github.com/golang-jwt/jwt"
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
}

//show table

type Show struct {
	ShowID     uint         `gorm:"primaryKey"`
	ShowName   string       `gorm:"not null; unique"`
	Language   string       `json:"language"`
	Ratings    float32      `json:"ratings"`
	URL        string       `json:"url"`
	IsActive   bool         `gorm:"default:true"`
	Screenplay []ScreenPlay `gorm:"foreignKey:ShowID;references:ShowID"`
	Seasons    []Season     `gorm:"foreignKey:ShowID;references:ShowID"`
}

//cast table

type ScreenPlay struct {
	Name     string `gorm:"primaryKey"`
	Role     string `json:"role"`
	ImageURL string `json:"image"`
	ShowID   uint
}

//season table

type Season struct {
	ShowID     uint      `json:"showId"`
	SeasonID   uint      `gorm:"primaryKey"`
	SeasonName string    `gorm:"not null; unique"`
	URL        string    `json:"url"`
	IsActive   bool      `gorm:"default:true"`
	Episodes   []Episode `gorm:"foreignKey:SeasonID;references:SeasonID"`
}

type Episode struct {
	SeasonID    uint   `json:"SeasonID"`
	EpisodeID   uint   `gorm:"primaryKey"`
	EpisodeName string `gorm:"not null; unique"`
	Duration    string `json:"duration"`
	IsActive    bool   `gorm:"default:true"`
}

//flow
//Shows --> Seasons --> Episodes
