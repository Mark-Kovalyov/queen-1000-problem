package mayton.chess;

import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayDeque;
import java.util.Deque;

import static mayton.chess.MtnQueensGenerator.isConsistent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MtnQueensGeneratorTest {
    @Test
    public void testConsistentCollection(){
        Deque<Integer> desk1 = new ArrayDeque<Integer>(){{
            addLast(2);
            addLast(4);
            addLast(1);
        }};
        assertTrue(isConsistent(desk1,3));

        Deque<Integer> desk2 = new ArrayDeque<Integer>(){{
            addLast(3);
            addLast(1);
            addLast(4);
        }};
        assertTrue(isConsistent(desk2,2));

        Deque<Integer> desk3 = new ArrayDeque<Integer>(){{
            addLast(1);
            addLast(2);
            addLast(3);
        }};
        assertFalse(isConsistent(desk3,2));

    }


    @Test
    public void testConsistentDeque(){
        Deque<Integer> desk1 = new ArrayDeque<Integer>(){{
            addLast(2);
            addLast(4);
            addLast(1);
        }};
        assertTrue(isConsistent(desk1));

        Deque<Integer> desk2 = new ArrayDeque<Integer>(){{
            addLast(3);
            addLast(1);
            addLast(4);
        }};
        assertTrue(isConsistent(desk2));
    }
}
