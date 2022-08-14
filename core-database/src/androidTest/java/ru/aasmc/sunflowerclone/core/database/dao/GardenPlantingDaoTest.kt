package ru.aasmc.sunflowerclone.core.database.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.aasmc.sunflowerclone.core.database.AppDatabase
import ru.aasmc.sunflowerclone.core.database.model.GardenPlantingEntity
import ru.aasmc.sunflowerclone.core.database.util.testCalendar
import ru.aasmc.sunflowerclone.core.database.util.testGardenPlanting
import ru.aasmc.sunflowerclone.core.database.util.testPlants
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import kotlinx.coroutines.flow.first
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import ru.aasmc.sunflowerclone.core.database.util.testPlant

class GardenPlantingDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var gardenPlantingDao: GardenPlantingDao
    private var testGardenPlantingId: Long = 0

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        gardenPlantingDao = database.gardenPlantingDao()
        database.plantDao().insertAll(testPlants)
        testGardenPlantingId = gardenPlantingDao.insertGardenPlanting(testGardenPlanting)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetGardenPlantings() = runTest {
        val gardenPlanting2 = GardenPlantingEntity(
            testPlants[1].plantId,
            testCalendar,
            testCalendar
        ).also { it.gardenPlantingId = 2 }

        gardenPlantingDao.insertGardenPlanting(gardenPlanting2)
        assertThat(gardenPlantingDao.getGardenPlantings().first().size, equalTo(2))
    }

    @Test
    fun testDeleteGardenPlanting() = runTest {
        val gardenPlanting2 = GardenPlantingEntity(
            testPlants[1].plantId,
            testCalendar,
            testCalendar
        ).also { it.gardenPlantingId = 2 }
        gardenPlantingDao.insertGardenPlanting(gardenPlanting2)
        assertThat(gardenPlantingDao.getGardenPlantings().first().size, equalTo(2))
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting2)
        assertThat(gardenPlantingDao.getGardenPlantings().first().size, equalTo(1))
    }

    @Test
    fun testGetGardenPlantingForPlant() = runTest {
        assertTrue(gardenPlantingDao.isPlanted(testPlant.plantId).first())
    }

    @Test
    fun testGetGardenPlantingForPlant_notFound() = runTest {
        assertFalse(gardenPlantingDao.isPlanted(testPlants[2].plantId).first())
    }

    @Test
    fun testGetPlantAndGardenPlantings() = runTest {
        val plantAndGardenPlantings = gardenPlantingDao.getPlantedGardens().first()
        assertThat(plantAndGardenPlantings.size, equalTo(1))

        /**
         * Only the [testPlant] has been planted, and thus has an associated [GardenPlantingEntity]
         */
        assertThat(plantAndGardenPlantings[0].plant, equalTo(testPlant))
        assertThat(plantAndGardenPlantings[0].gardenPlantings.size, equalTo(1))
        assertThat(plantAndGardenPlantings[0].gardenPlantings[0], equalTo(testGardenPlanting))
    }
}
