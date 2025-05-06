package com.pritom.dutta.movie.data.helper

import java.io.InputStreamReader

/**
 * Created by Pritom Dutta on 11/5/24.
 */
object Helper {
    fun readFileResource(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val stringBuilder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }

//    fun getTestData(fileName: String): TmdbWrapperModel<Movie> {
//        val gson = Gson()
//        val jsonString = readFileResource( "/$fileName")
//        val typeToken = object : TypeToken<TmdbWrapperModel<Movie>>() {}.type
//        return gson.fromJson(jsonString, typeToken)
//
////        val moshi = Moshi.Builder().build()
////        val typeToken = object : TypeToken<TmdbWrapperModel<Movie>>() {}.type
////        val jsonAdapter= moshi.adapter(typeToken)
////        val jsonString = readFileResource( "/$fileName")
////        return jsonAdapter.fromJson(jsonString)!!
//    }
}