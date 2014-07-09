package ps1.web;


/*
 * Match is a library of simple string pattern matching methods.
 */
public class Match {
    /*
     * Finds the first match to pattern in string, and
     * returns the rest of the string.  e.g., 
     * after("Your Name: Ben", "Name: ") => "Ben"
     * Returns null if pattern never occurs in string.
     */
    public static String after(String string, String pattern) {
        int start = string.indexOf(pattern);
        if (start == -1) return null;
        return string.substring(start + pattern.length());        
    }
    
    /*
     * Finds the first match to pattern in string, and
     * returns the part of the string before it.  e.g., 
     * before("hello/there", "/") => "hello"
     * Returns null if pattern never occurs in string.
     */
    public static String before(String content, String pattern) {
        int end = content.indexOf(pattern);
        if (end == -1) return null;
        return content.substring(0, end);
    }    
    
    /*
     * Finds the first match to leftPattern in string, and
     * the first match to rightPattern after that, and
     * returns the substring between the two matches. 
     * e.g. between("a <b>bold</b> word", "<b>", "</b>") => "bold"
     * Returns null if leftPattern...rightPattern never occurs in string.
     */
    public static String between(String content, String leftPattern, String rightPattern) {
        String rest = after(content, leftPattern);
        if (rest == null) return null;
        else return before(rest, rightPattern);
    }
    
    public static void main(String[] args) {
        boolean assertionsEnabled = false; 
        assert assertionsEnabled = true; // note: assignment, not equality test!
        if (!assertionsEnabled) {
            System.out.println("Must enable assertions with -ea");
            System.exit(0);
        }
        
        assert after("abcde", "c").equals("de");
        assert after("abcde", "f") == null;
        
        assert before("abcde", "c").equals("ab");
        assert after("abcde", "f") == null;
        
        assert between("abcde", "a", "d").equals("bc");
        assert between("abcde", "f", "d") == null;
        assert between("abcde", "a", "f") == null;
        assert between("abcde", "d", "a") == null;
    }
}
