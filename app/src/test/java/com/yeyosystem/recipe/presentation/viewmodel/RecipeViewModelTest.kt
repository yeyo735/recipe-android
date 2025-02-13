import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yeyosystem.recipe.domain.model.Recipe
import com.yeyosystem.recipe.domain.model.RecipeDetail
import com.yeyosystem.recipe.domain.usecase.GetRecipeDetailUseCase
import com.yeyosystem.recipe.domain.usecase.GetRecipesUseCase
import com.yeyosystem.recipe.presentation.viewmodel.RecipeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val getRecipesUseCase: GetRecipesUseCase = mock()
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase = mock()
    private lateinit var viewModel: RecipeViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    private val mockRecipe1 = Recipe(
        id = "1",
        name = "Spaghetti Carbonara",
        picture = "https://example.com/spaghetti.jpg",
        location = "Rome, Italy",
        latitude = 41.9029,
        longitude = 12.4964
    )

    private val mockRecipe2 = Recipe(
        id = "2",
        name = "Pad Thai",
        picture = "https://example.com/padthai.jpg",
        location = "Bangkok, Thailand",
        latitude = 13.7563,
        longitude = 100.5018
    )

    private val mockRecipe3 = Recipe(
        id = "3",
        name = "Tacos al Pastor",
        picture = "https://example.com/tacos.jpg",
        location = "Mexico City, Mexico",
        latitude = 19.4326,
        longitude = -99.1332
    )

    private val mockRecipe4 = Recipe(
        id = "4",
        name = "Sushi",
        picture = "https://example.com/sushi.jpg",
        location = "Tokyo, Japan",
        latitude = 35.6895,
        longitude = 139.6917
    )

    private val mockRecipe5 = Recipe(
        id = "5",
        name = "Pizza Margherita",
        picture = "https://example.com/pizza.jpg",
        location = "Naples, Italy",
        latitude = 40.8529,
        longitude = 14.2681
    )

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = RecipeViewModel(getRecipesUseCase, getRecipeDetailUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getRecipes - success`() = runTest {
        // Arrange
        val mockRecipes = listOf(mockRecipe1, mockRecipe2, mockRecipe3, mockRecipe4, mockRecipe5)
        whenever(getRecipesUseCase()).thenReturn(mockRecipes)

        // Act
        val recipesLiveData = viewModel.recipes

        // Assert
        val emittedRecipes = recipesLiveData.getOrAwaitValue()
        assertEquals(mockRecipes, emittedRecipes)
    }

    @Test
    fun `getRecipeDetail - success`() = runTest {
        // Arrange
        val recipeId = "1"
        val mockRecipeDetail = RecipeDetail(
            id = "1",
            description = "",
            preparation = "",
            ingredients = emptyList(),
        )
        whenever(getRecipeDetailUseCase(recipeId)).thenReturn(mockRecipeDetail) // Compares RecipeDetail

        // Act
        val recipeDetailLiveData = viewModel.getRecipeDetail(recipeId)

        // Assert
        val emittedRecipeDetail = recipeDetailLiveData.getOrAwaitValue()
        assertEquals(mockRecipeDetail, emittedRecipeDetail) // Compares RecipeDetail
    }


    // Helper function to get the value from a LiveData.
    private fun <T> androidx.lifecycle.LiveData<T>.getOrAwaitValue(
        time: Long = 5_000,
        timeUnit: java.util.concurrent.TimeUnit = java.util.concurrent.TimeUnit.MILLISECONDS,
        afterObserve: () -> Unit = {}
    ): T? {
        var data: T? = null
        val latch = java.util.concurrent.CountDownLatch(1)
        val observer = Observer<T> { value ->
            data = value
            latch.countDown()
        }
        observeForever(observer)

        try {
            afterObserve()
            if (!latch.await(time, timeUnit)) {
                throw java.util.concurrent.TimeoutException("LiveData value was not set in $timeUnit $time")
            }
        } finally {
            removeObserver(observer)
        }
        return data
    }
}