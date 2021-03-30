package id.asad.tugas2.api

import id.asad.tugas2.model.ResponseGame
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    fun getDataGames() : Call<ResponseGame>

}