import fr.epita.assistants.mykitten.MyKitten;

import org.junit.jupiter.api.Test;

public class MyKittenTest {

    @Test
    public void SimpleKitten() {
        MyKitten.miaou("/home/marc.gallant/afs/input.txt", "/home/marc.gallant/afs/output.txt", "test");
    }
}
