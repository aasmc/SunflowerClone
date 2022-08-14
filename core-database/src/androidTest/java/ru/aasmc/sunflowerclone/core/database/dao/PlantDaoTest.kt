package ru.aasmc.sunflowerclone.core.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.aasmc.sunflowerclone.core.database.AppDatabase
import ru.aasmc.sunflowerclone.core.database.model.PlantEntity

@RunWith(AndroidJUnit4::class)
class PlantDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var plantDao: PlantDao
    private val plantA = PlantEntity("1", "A", "", 1, 1, "")
    private val plantB = PlantEntity("2", "B", "", 1, 1, "")
    private val plantC = PlantEntity("3", "C", "", 2, 2, "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        plantDao = database.plantDao()

        // Insert plants in non-alphabetical order to test that results are sorted by name
        plantDao.insertAll(listOf(plantB, plantC, plantA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetPlants() = runTest {
        val plantList = plantDao.getPlants().first()
        assertThat(plantList.size, equalTo(3))

        // Ensure plant list is sorted by name
        assertThat(plantList[0], equalTo(plantA))
        assertThat(plantList[1], equalTo(plantB))
        assertThat(plantList[2], equalTo(plantC))
    }

    @Test
    fun testGetPlantsWithGrowZoneNumber() = runTest {
        val plantList = plantDao.getPlantsWithGrowZoneNumber(1).first()
        assertThat(plantList.size, equalTo(2))
        assertThat(
            plantDao.getPlantsWithGrowZoneNumber(2).first().size,
            equalTo(1)
        )
        assertThat(
            plantDao.getPlantsWithGrowZoneNumber(3).first().size,
            equalTo(0)
        )

        // Ensure plant list is sorted by name
        assertThat(plantList[0], equalTo(plantA))
        assertThat(plantList[1], equalTo(plantB))
    }

    @Test
    fun testGetPlant() = runTest {
        assertThat(
            plantDao.getPlant(plantA.plantId).first(),
            equalTo(plantA)
        )
    }
}