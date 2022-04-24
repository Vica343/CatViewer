package hu.bme.aut.android.catviewer.network
import hu.bme.aut.android.catviewer.MainCoroutinesRule
import com.skydoves.sandwich.ApiResponse
import hu.bme.aut.android.catviewer.model.Cat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class DisneyServiceTest : ApiAbstract<CatService>() {

    private lateinit var service: CatService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(CatService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchDisneyPosterListTest() = runBlocking {
        enqueueResponse("/CatList.json")
        val response = service.fetchCatList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockWebServer.takeRequest()

        assertThat(responseBody[0].id, `is`("6ft"))
        assertThat(responseBody[0].url, `is`("https://cdn2.thecatapi.com/images/6ft.jpg"))
        assertThat(responseBody[0].width, `is`("500"))
        assertThat(responseBody[0].height, `is`("332"))
    }
}