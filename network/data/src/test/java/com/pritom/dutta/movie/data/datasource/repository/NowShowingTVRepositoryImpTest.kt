package com.pritom.dutta.movie.data.datasource.repository

import com.pritom.dutta.movie.data.repositoryImp.StockRepositoryImp
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NowShowingTVRepositoryImpTest {

    @Mock
    lateinit var sutApi: StockRepositoryImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

//    @Test
//    fun getTopRatedMovies_EmptyList() = runTest {
//        emptyList()
//        val response = sutApi.fetchStockData().first()
//        assertEquals(true, response is NetworkResult.Success)
//        assertEquals(0, response.data?.items?.size)
//    }
//
//    @Test
//    fun getTopRatedMovies_4_Items() = runTest {
//        success()
//        val response = sutApi.fetchNowShowingMovies().first()
//        assertEquals(true, response is NetworkResult.Success)
//        assertEquals(4, response.data?.items?.size)
//    }
//
//    @Test
//    fun getTopRatedMovies_get_error() = runTest {
//        failure()
//        val response = sutApi.fetchNowShowingMovies().first()
//        assertEquals(true, response is NetworkResult.Error)
//        assertEquals(401, response.code)
//        assertEquals("Unauthorized", response.message)
//    }
//
//    private suspend fun success() {
//        val testData = Helper.getTestData("api_response_data.json")
//        Mockito.`when`(sutApi.fetchNowShowingMovies()).thenReturn(
//            flow { emit(NetworkResult.Success(testData)) }
//        )
//    }
//
//    private suspend fun failure() {
//        Mockito.`when`(sutApi.fetchStockData(skip = 1, limit = 10, sortBy = "price%2Ctitle", order = "asc")).thenReturn(
//            flow { emit(NetworkResult.Error(401,"Unauthorized")) }
//        )
//    }
//
//    private suspend fun emptyList() {
//        val testData = Helper.getTestData("movie_empty_list.json")
//        Mockito.`when`(sutApi.fetchStockData()).thenReturn(
//            flow { emit(NetworkResult.Success(testData)) }
//        )
//    }
}