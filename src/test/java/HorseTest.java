import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    @DisplayName("Тест конструктора Horse с первым параметром Null")
    public void testConstructorWithNullName(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 25, 3);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @DisplayName("Тест конструктора Horse с первым параметром пробелы, табуляция...")
    @ParameterizedTest
    @ValueSource(strings = {"   ", "\\t", " "})
    public void testConstructorWithEmptyName(String word){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(word, 25, 3);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }



    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }
}