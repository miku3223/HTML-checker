import java.util.*;

public class HTMLManager {
    private Queue<HTMLTag> tags;

    // Constructor
    public HTMLManager(Queue<HTMLTag> html) {
        if (html == null) {
            throw new IllegalArgumentException();
        }
        tags = new LinkedList<>();
        for (HTMLTag tag : html) {
            tags.add(tag);
        }
    }    
    
}
