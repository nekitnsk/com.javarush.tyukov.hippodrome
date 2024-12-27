import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HippodromeTest {

    @Test
    @DisplayName("Тест конструктора Hippodrome с первым параметром Null")
    public void testConstructorWithNullList(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение Horses cannot be empty")
    public void testConstructorWithEmptyList(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(List.of());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.")
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse "+i, i, i / 2));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertIterableEquals(horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("Проверить, что метод вызывает метод move у всех лошадей. ")
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.spy(new Horse("Name", 25)));
        }

        Hippodrome hippodrome = Mockito.spy(new Hippodrome(horses));

        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }


    }

    @Test
    void getWinner() {
    }
}