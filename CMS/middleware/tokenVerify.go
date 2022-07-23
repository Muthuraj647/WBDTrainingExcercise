package middleware

import (
	"net/http"
	"strings"

	"github.com/Muthuraj647/WBDTrainingExcercise/CMS/model"
	"github.com/golang-jwt/jwt"
)

func VerifyLogin(f http.HandlerFunc) http.HandlerFunc {

	return func(w http.ResponseWriter, r *http.Request) {
		token := r.URL.Query()["token"]

		tokenstr := strings.Split(token[0], " ")

		if len(tokenstr) == 2 && tokenstr[0] == "Bearer" && tokenstr[1] != "" {

			claims := &model.Claims{}
			jwtKey := []byte("secretKey")
			tkn, err := jwt.ParseWithClaims(tokenstr[1], claims, func(t *jwt.Token) (interface{}, error) {
				return jwtKey, nil
			})

			if err != nil {
				if err == jwt.ErrSignatureInvalid {
					w.WriteHeader(http.StatusUnauthorized)
					w.Write([]byte("Invalid token or token expires,Invalid Signature"))
					return
				}
				w.WriteHeader(http.StatusBadRequest)

				w.Write([]byte("Error Occured"))
				return
			}
			if !tkn.Valid {
				w.WriteHeader(http.StatusUnauthorized)
				w.Write([]byte("Invalid token or token expires"))
				return
			}

			//fmt.Println("Username")
			//fmt.Println(claims.Name)

			if claims.Role == "CMS" {
				f(w, r)
				return
			} else {
				w.Write([]byte("You're not Authorized person to access this API"))
			}

		}
		w.Write([]byte("Token Missing or Invalid Token"))

	}

}
