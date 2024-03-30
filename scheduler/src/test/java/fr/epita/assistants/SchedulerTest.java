package fr.epita.assistants;

import fr.epita.assistants.scheduler.MyTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class SchedulerTest {

    @Test
    public void simpleTest() {
        var bestShell = MyTask.of(() -> 42)
                .andThenWait(1L, TimeUnit.SECONDS)
                .andThenDo(value -> value + "sh")
                .execute();
        System.out.println(bestShell);
    }
}