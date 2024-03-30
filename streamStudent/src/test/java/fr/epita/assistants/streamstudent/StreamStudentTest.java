package fr.epita.assistants.streamstudent;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamStudentTest {
    static void assertStreamEquals(Stream<Pair<Integer, String>> expectedStream,
                                   Stream<Pair<Integer, String>> actualStream) {
        // Get iterators from stream
        Iterator<Pair<Integer, String>> iterator1 = expectedStream.iterator();
        Iterator<Pair<Integer, String>> iterator2 = actualStream.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            // Get next objects
            Pair<Integer, String> login1 = iterator1.next();
            Pair<Integer, String> login2 = iterator2.next();

            // Check if pairs are equal
            assertEquals(login1, login2);
        }

        assertTrue(!iterator1.hasNext() && !iterator2.hasNext(),
                "Streams do not have the same length");

    }

    @Test
    public void validatorLoginContainsTwoOrMoreUnderscore() {
        Pair<Integer, String> loginTwoUnderscore = new Pair<>(50, "xavier_login_");
        Pair<Integer, String> loginValid = new Pair<>(90, "xavier_login");
        Pair<Integer, String> loginMultipleUnderscord = new Pair<>(10, "_login__x");
        Streamer streamer = new Streamer();

        var loginList = List.of(loginTwoUnderscore, loginValid, loginMultipleUnderscord);

        var expected = List.of(loginValid).stream();
        var actual = streamer.validator(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void validatorWrongNotes() {
        Pair<Integer, String> loginUnder = new Pair<>(-1, "marc.gallant");
        Pair<Integer, String> loginValid = new Pair<>(90, "xavier_login");
        Pair<Integer, String> loginOver = new Pair<>(101, "login.evariste");
        Streamer streamer = new Streamer();

        var loginList = List.of(loginUnder, loginValid, loginOver);

        var expected = List.of(loginValid).stream();
        var actual = streamer.validator(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void validatorAll() {
        Pair<Integer, String> login1 = new Pair<>(-1, "abc_z");
        Pair<Integer, String> login2 = new Pair<>(10, "loginx");
        Pair<Integer, String> login3 = new Pair<>(10, "login_x");
        Pair<Integer, String> login4 = new Pair<>(95, "asdf.x_poi");
        Streamer streamer = new Streamer();

        var loginList = List.of(login1, login2, login3, login4);

        var expected = List.of(login3).stream();
        var actual = streamer.validator(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void orderGradeSimpleTest() {
        Pair<Integer, String> loginOne = new Pair<>(50, "evariste.mercier");
        Pair<Integer, String> loginTwo = new Pair<>(90, "marc.gallant");
        Pair<Integer, String> loginThree = new Pair<>(10, "malo.garnier");
        Streamer streamer = new Streamer();

        var loginList = List.of(loginOne, loginTwo, loginThree);

        var expected = List.of(loginThree, loginOne, loginTwo).stream();

        var actual = streamer.orderGrade(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void lowerCaseSimple() {
        Pair<Integer, String> loginOne = new Pair<>(10, "abc_a");
        Pair<Integer, String> loginTwo = new Pair<>(11, "aBc_z");
        Streamer streamer = new Streamer();

        var loginList = List.of(loginOne, loginTwo);

        Pair<Integer, String> loginTwoCorrect = new Pair<>(5, "abc_z");
        var expected = List.of(loginOne, loginTwoCorrect).stream();

        var actual = streamer.lowercase(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void orderGradeHard() {
        Pair<Integer, String> login1 = new Pair<>(2, "zzz_z");
        Pair<Integer, String> login2 = new Pair<>(2, "aaaa_z");
        Pair<Integer, String> login3 = new Pair<>(1, "aaa_z");
        Streamer streamer = new Streamer();

        var loginList = List.of(login1, login2, login3);

        var expected = List.of(login3, login2, login1).stream();

        var actual = streamer.orderGrade(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void quickFixSimple() {
        Pair<Integer, String> login1 = new Pair<>(20, "mArc");
        Pair<Integer, String> login2 = new Pair<>(51, "Lewfewfx");
        Streamer streamer = new Streamer();

        var loginList = List.of(login1, login2);

        Pair<Integer, String> login1Correct = new Pair<>(40, "mArc");
        Pair<Integer, String> login2Correct = new Pair<>(100, "Lewfewfx");

        var expected = List.of(login1Correct, login2Correct).stream();

        var actual = streamer.quickFix(loginList.stream());

        assertStreamEquals(expected, actual);
    }

    @Test
    public void HeadofClass() {
        Pair<Integer, String> login1 = new Pair<>(5, "abc_a");
        Pair<Integer, String> login2 = new Pair<>(15, "abc_b");
        Pair<Integer, String> login3 = new Pair<>(15, "abc_z");
        Streamer streamer = new Streamer();

        var loginList = List.of(login1, login2, login3);

        Optional<Pair<Integer, String>> expected = Optional.of(login2);
        Optional<Pair<Integer, String>> actual = streamer.headOfTheClass(loginList.stream());

        assertEquals(expected, actual);
    }

    @Test
    public void encryptionSimple() {
        Pair<Integer, String> login1 = new Pair<>(10, "tigrou_c");
        Pair<Integer, String> login2 = new Pair<>(15, "login_x");
        Streamer streamer = new Streamer();

        var loginList = List.of(login1, login2);

        Pair<Integer, String> login1Correct = new Pair<>(10, "ou_ctigr");
        Pair<Integer, String> login2Correct = new Pair<>(15, "in_xlog");

        var expected = List.of(login1Correct, login2Correct).stream();

        var actual = streamer.encryption(loginList.stream());

        assertStreamEquals(expected, actual);

    }
    // Add your own tests here
}
