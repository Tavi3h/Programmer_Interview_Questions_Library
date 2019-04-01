package interview_03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCut {
    
    public String cut(String s, int b) {
        if (b == 0) {
            return "";
        }
        String unicode = stringToUnicode(s);
        int pos = 0;
        for (char c : s.toCharArray()) {
            b = !(String.valueOf(c).getBytes().length > 1) ? b - 1 : b - 2;
            if (b < 0) {
                break;
            }
            pos += 6;
        }
        return unicodeToString(unicode.substring(0, pos));
    }

    // String -> unicode
    private String stringToUnicode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(String.format("\\u%04x", Integer.valueOf(c)));
        }
        return sb.toString();
    }

    // unicode -> String
    private String unicodeToString(String unicode) {
        StringBuilder sb = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            sb.append((char) Integer.parseInt(hex[i], 16));
        }
        return sb.toString();
    }

    @Test
    public void testCase() {
        
        // test String -> unicode
        assertEquals("\\u4e2d\\u534e\\u4eba\\u6c11\\u5171\\u548c\\u56fd", stringToUnicode("中华人民共和国"));
        assertEquals("\\u006c\\u0065\\u0065\\u0074\\u0063\\u006f\\u0064\\u0065", stringToUnicode("leetcode"));
        assertEquals("\\u0047\\u0069\\u0074\\u0068\\u0075\\u0062", stringToUnicode("Github"));
        
        // test unicode -> String
        assertEquals("Github", unicodeToString("\\u0047\\u0069\\u0074\\u0068\\u0075\\u0062"));
        assertEquals("leetcode", unicodeToString("\\u006c\\u0065\\u0065\\u0074\\u0063\\u006f\\u0064\\u0065"));
        assertEquals("中华人民共和国", unicodeToString("\\u4e2d\\u534e\\u4eba\\u6c11\\u5171\\u548c\\u56fd"));
        
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
