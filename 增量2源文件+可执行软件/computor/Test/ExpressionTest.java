import Expression.Expression;

import org.junit.Test;
import static org.junit.Assert.*;


public class ExpressionTest
{
    /**
     * Tests that assertions are enabled.
     */
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    /*
     * Tests for expression()
     * Testing strategy:
     *	测试expression()方法生成的表达式的值是否始终大于0
     */
    @Test
    public void expressionTest()
    {
        Expression e = new Expression();
        for (int i = 0; i < 5; i++)
        {
            assertEquals(true, e.result(e.expression()) > 0);
        }
    }

    /*
     * Tests for result()
     * Testing strategy:
     * 测试result()方法算出的表达式结果是否正确
     */
    @Test
    public void resultTest()
    {
        Expression e = new Expression();
        String str1 = "(45+50)/5+30*46-7";
        String str2 = "17*1/1*14";
        String str3 = "10/5+1";
        String str4="29+22+9+46";
        assertEquals(1392, e.result(str1));
        assertEquals(238, e.result(str2));
        assertEquals(3, e.result(str3));
        assertEquals(106, e.result(str4));
    }
}
