package interview_03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCutII {

    public String cut(String s, int b) {
        
        if (b == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (count < b) {
                if (String.valueOf(c).getBytes().length > 1) {
                    if (count + 1 == b) {
                        return sb.toString();
                    }
                    count += 2;
                } else {
                    ++count;
                }
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    @Test
    public void testCase() {

        // test result
        assertEquals("人ABC", cut("人ABC们DEF", 5));
        assertEquals("人ABC", cut("人ABC们DEF", 6));
        assertEquals("人ABC们D", cut("人ABC们DEF", 8));
        assertEquals("人ABC们DEF", cut("人ABC们DEF", 20));
        assertEquals("人AB", cut("人ABC", 4));
        assertEquals("", cut("人ABC们DEF", 0));
        assertEquals("中华", cut("中华人民共和国", 5));
    }
}
