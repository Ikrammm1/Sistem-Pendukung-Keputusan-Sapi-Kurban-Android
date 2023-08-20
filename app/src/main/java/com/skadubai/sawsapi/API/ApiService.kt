package com.skadubai.sawsapi.API

import com.skadubai.sawsapi.Login.ResponeLogin
import com.skadubai.sawsapi.Model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<ResponeLogin>

    @FormUrlEncoded
    @POST("CekUser.php")
    fun CekUser(
        @Field("username") username : String,

    ) : Call<ModelRespon>

    @FormUrlEncoded
    @POST("EditPass.php")
    fun EditPass(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<ModelRespon>

    @FormUrlEncoded
    @POST("register.php")
    fun Register(
        @Field("nama_user") nama_user:String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ModelRespon>

    @GET("kriteria.php")
    fun Kriteria():Call<ModelKriteria>

    @GET("about.php")
    fun About():Call<ModelAbout>

    @FormUrlEncoded
    @POST("ranking.php")
    fun Ranking(
        @Field("id_user") id_user : String
    ):Call<ModelRanking>

    @FormUrlEncoded
    @POST("InputSapi.php")
    fun InputSapi(
        @Field("id_user") id_user:String,
        @Field("alternatif") alternatif:String,
        @Field("berat") berat:String,
        @Field("kecacatan") kecacatan: String,
        @Field("perilaku") perilaku: String,
        @Field("umur") umur: String,
        @Field("jenis_kelamin") jenis_kelamin: String
    ): Call<ModelRespon>

    @FormUrlEncoded
    @POST("datasapi.php")
    fun DataSapi(
        @Field("id_user") id_user : String
    ) : Call<ModelSapi>

    @FormUrlEncoded
    @POST("HitungHasil.php")
    fun HitungHasil(
        @Field("id_user") id_user : String
    ) : Call<ModelRespon>

    @FormUrlEncoded
    @POST("hapusSapi.php")
    fun HapusSapi(
        @Field("id_datasapi") id_datasapi : String
    ) : Call<ModelRespon>

    @FormUrlEncoded
    @POST("hapusSapiAll.php")
    fun HapusAll(
        @Field("id_user") id_user : String
    ) : Call<ModelRespon>

    @FormUrlEncoded
    @POST("UpdateSapi.php")
    fun UpdateSapi(
        @Field("id_datasapi") id_datasapi:String,
        @Field("alternatif") alternatif:String,
        @Field("berat") berat:String,
        @Field("kecacatan") kecacatan: String,
        @Field("perilaku") perilaku: String,
        @Field("umur") umur: String,
        @Field("jenis_kelamin") jenis_kelamin: String
    ): Call<ModelRespon>
}