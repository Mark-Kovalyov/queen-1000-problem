package mayton.chess;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ReportUtilsTest {

    List<Integer> list = Arrays.asList(1,2,3,4,5);

    @Test
    public void testFormatQueens() {
        assertEquals("[]", ReportUtils.formatQueens(Collections.emptyList()));
        assertEquals("[1, 2, 3, 4, 5]", ReportUtils.formatQueens(list));
    }

    @Test
    public void testFormatQueensStream(){
        assertEquals("[]",ReportUtils.formatQueens(Utils.iteratorToStream(Collections.emptyIterator())));
        assertEquals("[1, 2, 3, 4, 5]",ReportUtils.formatQueens(Utils.iteratorToStream(list.iterator())));
    }

    @Test
    public void testFormatOffset() {
        assertEquals("", ReportUtils.formatOffset(-1,'*'));
        assertEquals("", ReportUtils.formatOffset(0,'*'));
        assertEquals("*", ReportUtils.formatOffset(1,'*'));
        assertEquals("**", ReportUtils.formatOffset(2,'*'));
    }

}
