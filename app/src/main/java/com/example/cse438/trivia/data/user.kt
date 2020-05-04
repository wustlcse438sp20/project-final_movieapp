package com.example.cse438.trivia.data

data class user(
    var username : String,
    var email : String,
    var numberOfPlays : Int,
    var numberOfWins : Int,
    var winRate : Double,
    var chips : Int,
    var pic_url : String
){
    constructor() : this("", "", 0, 0,  0.0, 2000, "")

}