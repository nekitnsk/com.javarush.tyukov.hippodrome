import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled
    void mainExecuteWithin22Seconds(){

//        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            Main.main(new String[]{});
        }catch (Exception e){

        }finally {
//            System.setOut(System.out);
        }

    }
}