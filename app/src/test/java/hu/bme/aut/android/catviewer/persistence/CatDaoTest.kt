package hu.bme.aut.android.catviewer.persistence


import hu.bme.aut.android.catviewer.model.db.CatEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

fun mockCatList() = listOf(CatEntity.mock())

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class CatDaoTest : LocalDatabase() {

    private lateinit var  catDao: CatDao

    @Before
    fun init() {
        catDao = db.catDao()
    }

    @Test
    fun insertAndLoadCatListTest() = runBlocking {
        val mockDataList = mockCatList()
        catDao.insertCatList(mockDataList)

        val loadFromDB = catDao.getCatList()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = CatEntity.mock()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun addAndRemoveFavorite() = runBlocking {
        val mockDataList = mockCatList()
        catDao.insertCatList(mockDataList)

        catDao.updateFavorite(0, true)
        var loadFromDB = catDao.getCatList()
        assertThat(loadFromDB[0].favorite.toString(), `is`("true"))

        catDao.updateFavorite(0, false)
        loadFromDB = catDao.getCatList()
        assertThat(loadFromDB[0].favorite.toString(), `is`("false"))
    }

    @Test
    fun UploadAndDeleteImage() = runBlocking {
        val mockDataList = mockCatList()
        catDao.insertCatList(mockDataList)

        var loadFromDB = catDao.getCatList()
        assertThat(loadFromDB[0].uploaded.toString(), `is`("true"))

        catDao.deleteUploadedCat(0)
        loadFromDB = catDao.getCatList()
        assertThat(loadFromDB.isEmpty().toString(), `is`("true"))

    }

}