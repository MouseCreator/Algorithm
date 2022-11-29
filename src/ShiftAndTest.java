import org.junit.jupiter.api.Test;

class ShiftAndTest {
    @Test
    void testComplexSequence() {
        ShiftAnd shiftAnd = new ShiftAnd("XABXABAAXA", "ABAAC");
        shiftAnd.execute();
    }
    @Test
    void testSimpleSequence() {
        ShiftAnd shiftAnd = new ShiftAnd("CALIFORNIA", "FOR");
        shiftAnd.execute();
    }
}