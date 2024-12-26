import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
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
    @ValueSource(strings = {"   ", " ", "", "\t"})
    public void testConstructorWithEmptyName(String word){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(word, 25, 3);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @DisplayName("Тест конструктора Horse со вторым(speed) параметром отрицательным числом")
    @ParameterizedTest
    @ValueSource(doubles = {-1, -100, -0.001, -Double.MAX_VALUE})
    public void testConstructorWithNegativeSpeed(double speed){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", speed, 3);
        });

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @DisplayName("Тест конструктора Horse с третьим(distance) параметром отрицательным числом")
    @ParameterizedTest
    @ValueSource(doubles = {-1, -100, -0.001, -Double.MAX_VALUE})
    public void testConstructorWithNegativeDistance(double distance){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", 2, distance);
        });

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    @DisplayName("метод возвращает строку, которая была передана первым параметром в конструктор;")
    void getName() {
        Horse horse = new Horse("NameHorse", 25);

        assertEquals("NameHorse", horse.getName());

    }

    @Test
    @DisplayName("метод возвращает число, которое было передано вторым параметром в конструктор;")
    void getSpeed() {

        Horse horse = new Horse("NameHorse", 25);

        assertEquals(25, horse.getSpeed());
    }

    @Test
    @DisplayName("метод возвращает число, которое было передано вторым параметром в конструктор;")
    void getDistance() {
        Horse horse = new Horse("NameHorse", 25, 30);

        assertEquals(30, horse.getDistance());

        Horse horse2 = new Horse("NameHorse", 25);

        assertEquals(0, horse2.getDistance());


    }


    @DisplayName("метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9).  ")
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void moveCheckDistance(double random) {

        try(MockedStatic<Horse> mockStatic = mockStatic(Horse.class)){


            mockStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            Horse horse = Mockito.spy(new Horse("Name", 1, 1));
            horse.move();

            assertEquals(1+1*random, horse.getDistance());

        }
    }

    @Test
    @DisplayName("метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9. ")
    public void moveCallGetRandomDouble(){

        Horse horse = Mockito.spy(new Horse("Name", 0.2, 0.9));

        try(MockedStatic<Horse> mockStatic = mockStatic(Horse.class)){

            horse.move();

            mockStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));

        }


    }
}