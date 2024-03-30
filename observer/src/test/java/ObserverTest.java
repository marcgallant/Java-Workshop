import fr.epita.assistants.observer.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;

public class ObserverTest {

    @Test
    public void simpleRegisterTest() {
        final var swimmer = new Swimmer("Swimmer");
        final var lifeguard = new Lifeguard("Lifeguard");

        swimmer.register(lifeguard);

        final var expected = new HashSet<Observable.Observer<Swimmer>>();
        expected.add(lifeguard);

        final var actual = swimmer.getObservers();

        assertEquals(expected, actual);
    }
    @Test

    public void emptyRegisterTest() {
        final var swimmer = new Swimmer("Swimmer");

        final var expected = new HashSet<Observable.Observer<Swimmer>>();

        final var actual = swimmer.getObservers();

        swimmer.setStatus(SwimmerStatus.DROWNING);

        assertEquals(expected, actual);
    }

    @Test
    public void hardRegisterTest() {
        final var swimmer = new Swimmer("Swimmer");
        final var lifeguard = new Lifeguard("Lifeguard");

        swimmer.register(lifeguard);
        swimmer.register(lifeguard);

        final var expected = new HashSet<Observable.Observer<Swimmer>>();
        expected.add(lifeguard);

        final var actual = swimmer.getObservers();

        assertEquals(expected, actual);
    }

    @Test
    public void RegisterUnregisterTest() {
        final var swimmer = new Swimmer("Swimmer");
        final var lifeguard = new Lifeguard("Lifeguard");

        swimmer.register(lifeguard);
        swimmer.register(lifeguard);
        swimmer.unregister(lifeguard);

        final var expected = new HashSet<Observable.Observer<Swimmer>>();

        final var actual = swimmer.getObservers();

        assertEquals(expected, actual);
    }
}
