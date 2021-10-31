import Delete.delete;
import org.junit.Test;
import static org.junit.Assert.*;
import Delete.delete.*;
public class deleteTest {
    /**
     * Tests that assertions are enabled.
     */
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void mydeleteTest(){
        assertEquals("1+2+3=",delete.mydelete("1+2+3=123"));
    }

    @Test
    public void mybackspaceTest(){
        assertEquals("1234",delete.mybackspace("1234"));
        assertEquals("123456=456",delete.mybackspace("123456=4567"));
        assertEquals("1234=",delete.mybackspace("1234="));
    }

}
