package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import library.Recipe;
import item.Item;

public class LibraryTest {
    private Recipe recipe;
    private Item[] ingredients;

    @Before
    public void setUp() {
        ingredients = new Item[] {
                new Item("Malt", 3),
                new Item("Hops", 3),
                new Item("Yeast", 1)
        };

        recipe = new Recipe("Beer", ingredients);
    }

    @Test
    public void testGetName() {
        assertEquals("Beer", recipe.getName());
    }

    @Test
    public void testGetIngredients() {
        Item[] recipeIngredients = recipe.getIngredients();
        assertEquals(ingredients.length, recipeIngredients.length);
        assertEquals("Malt", recipeIngredients[0].getName());
        assertEquals(3, recipeIngredients[0].getCount());
    }
}
