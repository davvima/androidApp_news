package com.example.myapplication

import com.example.myapplication.provider.NewsProvider
import com.example.myapplication.repository.ApiKeyInvalidException
import com.example.myapplication.repository.MissingApiKeyException
import com.example.myapplication.repository.NewsRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets


class NewsRepositoryTest {
   private val mockWebServer = MockWebServer()
    private val newsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsProvider::class.java)

    private val newsRepository = NewsRepositoryImp(newsProvider)

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `Top headLines response is correct`(){
        mockWebServer.enqueueResponse("top_headlines.json")

        runBlocking {
            val articles = newsRepository.getNews("US")
            assertEquals(10, articles.size)
            assertEquals("Daniel Zlatev", articles[0].author)
            assertEquals("Efe Udin", articles[5].author)
        }

    }

    @Test
    fun `Api key missing excepction`(){
        mockWebServer.enqueueResponse("api_key_missing.json")
        assertThrows(MissingApiKeyException::class.java) {
            runBlocking{
                newsRepository.getNews("US")

            }
        }
    }

    @Test
    fun `Invalid Api Key exception`(){
        mockWebServer.enqueueResponse("api_key_invalid.json")
        assertThrows(ApiKeyInvalidException::class.java) {
            runBlocking{
                newsRepository.getNews("US")

            }
        }
    }

}

fun MockWebServer.enqueueResponse(filePath:String){
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let{
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}