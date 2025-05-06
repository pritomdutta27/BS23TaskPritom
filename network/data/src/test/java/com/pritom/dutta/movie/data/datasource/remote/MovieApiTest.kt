package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.helper.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Pritom Dutta on 11/5/24.
 */

class MovieApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

    }

    @Test
    fun test_get_top_ratedMovies_IsEmpty() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{\"results\": []}")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.fetchTopRatedMovie(1)
        mockWebServer.takeRequest()
        Assert.assertEquals(true, response.body()?.items?.isEmpty())
    }


    @Test
    fun test_get_top_rated_movies_return_results_list() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/api_response_data.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = apiService.fetchTopRatedMovie(1)
        mockWebServer.takeRequest()
        Assert.assertEquals(false, response.body()?.items?.isEmpty())
        Assert.assertEquals(true, response.isSuccessful)
        Assert.assertEquals(200, response.code())
        Assert.assertEquals(4, response.body()?.items?.size)
    }

    @Test
    fun test_get_top_rated_movies_return_error() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.fetchTopRatedMovie(1)
        mockWebServer.takeRequest()
        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}