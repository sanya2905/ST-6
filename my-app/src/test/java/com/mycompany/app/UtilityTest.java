package com.mycompany.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    public void testPrintEmptyArrayList() {
        Utility.print(new ArrayList<>());
        assertEquals(lineSeparator + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintEmptyArray() {
        Utility.print(new int[] {});
        assertEquals(lineSeparator + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintEmptyCharArray() {
        Utility.print(new char[] {});
        assertEquals(lineSeparator + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintSingleChar() {
        Utility.print(new char[] { 'A' });
        assertEquals(lineSeparator + "A" + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintMultipleChars() {
        Utility.print(new char[] { 'A', 'B', 'C' });
        assertEquals(lineSeparator + "A-B-C" + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintArrayListWithMultipleElements() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Utility.print(list);
        assertEquals(lineSeparator + "1-2-3" + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintArrayWithMultipleElements() {
        Utility.print(new int[] { 1, 2, 3 });
        assertEquals(lineSeparator + "1-2-3" + lineSeparator, outContent.toString());
    }

    @Test
    public void testPrintNullArray() {
        assertThrows(NullPointerException.class, () -> Utility.print((int[]) null));
    }

    @Test
    public void testPrintNullArrayList() {
        assertThrows(NullPointerException.class, () -> Utility.print((ArrayList<Integer>) null));
    }
}