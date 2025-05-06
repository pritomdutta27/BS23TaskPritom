package com.pritom.dutta.movie.data.datasource.remote

import app.cash.turbine.test
import com.pritom.dutta.movie.data.helper.Helper
import com.pritom.dutta.movie.data.utils.ApiException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Pritom Dutta on 11/5/24.
 */

class ApiRemoteDataSourceTest{

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiService: MovieApiService
    private lateinit var sutApiRemoteDataSource: ApiRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        movieApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieApiService::class.java)
        sutApiRemoteDataSource = ApiRemoteDataSource(movieApiService)
    }

    @Test
    fun test_get_top_rated_movies_empty_list() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{\"results\": []}")
        mockWebServer.enqueue(mockResponse)

        val response = sutApiRemoteDataSource.invoke().test {
            val result = awaitItem()
            assertEquals(true, result.items.isEmpty())
            awaitComplete()
        }
    }


    @Test
    fun test_get_top_rated_movies_return_results_list() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/api_response_data.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)
//        mockWebServer.takeRequest()
        val response = sutApiRemoteDataSource.invoke().test {
            val result = awaitItem()
            assertEquals(false, result.items.isEmpty())
            assertEquals(4, result.items.size)
            awaitComplete()
        }
        mockWebServer.takeRequest()
    }


    @Test(expected = ApiException::class)
    fun test_get_top_rated_movies_return_error() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(401)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        sutApiRemoteDataSource.invoke().first()
        mockWebServer.takeRequest()
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}