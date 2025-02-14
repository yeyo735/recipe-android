import com.yeyosystem.recipe.data.model.IngredientDto
import com.yeyosystem.recipe.data.model.RecipeDetailDto
import com.yeyosystem.recipe.data.model.RecipeDto
import com.yeyosystem.recipe.data.remote.RecipeApiService
import com.yeyosystem.recipe.data.repository.RecipeRepositoryImpl
import com.yeyosystem.recipe.domain.model.RecipeDetail
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeRepositoryTest {

    private lateinit var repository: RecipeRepositoryImpl
    private val apiService: RecipeApiService = mock()
    private val dispatcher = UnconfinedTestDispatcher()

    private val mockRecipeDto1 = RecipeDto(
        id = "1",
        name = "Spaghetti Carbonara",
        picture = "https://example.com/spaghetti.jpg",
        location = "Rome, Italy",
        latitude = "41.9029",
        longitude = "12.4964"
    )

    private val mockIngredientDto1 = IngredientDto(
        name = "Pasta",
        quantity = "200g"
    )

    private val mockIngredientDto2 = IngredientDto(
        name = "Eggs",
        quantity = "3"
    )

    private val mockRecipeDetailDto1 = RecipeDetailDto(
        id = 1,
        description = "Delicious pasta dish",
        preparation = "Cook pasta and add sauce",
        ingredients = listOf(mockIngredientDto1, mockIngredientDto2)
    )

    private val mockRecipe1 = mockRecipeDto1.toDomain()
    private val mockRecipeDetail1 = mockRecipeDetailDto1.toDomain()

    @Before
    fun setup() {
        repository = RecipeRepositoryImpl(apiService)
    }

    @After
    fun teardown() {
        // No specific teardown needed for this test
    }

    @Test
    fun `getRecipes - success`() = runTest {
        // Arrange
        val mockRecipes = listOf(mockRecipe1)
        whenever(apiService.getRecipes()).thenReturn(listOf(mockRecipeDto1))

        // Act
        val recipes = repository.getRecipes()

        // Assert
        assertEquals(mockRecipes, recipes)
    }

    @Test
    fun `getRecipeDetail - success`() = runTest {
        // Arrange
        val recipeId = "1"
        whenever(apiService.getRecipeDetail(recipeId)).thenReturn(mockRecipeDetailDto1)

        // Act
        val recipeDetail = repository.getRecipeDetail(recipeId)

        // Assert
        assertEquals(mockRecipeDetail1, recipeDetail)
    }

    @Test
    fun `getRecipeDetail - null response - returns default`() = runTest {
        // Arrange
        val recipeId = "1"
        whenever(apiService.getRecipeDetail(recipeId)).thenReturn(null)

        // Act
        val recipeDetail = repository.getRecipeDetail(recipeId)

        // Assert
        val expected = RecipeDetail("0", "", "", emptyList())
        assertEquals(expected, recipeDetail)
    }
}