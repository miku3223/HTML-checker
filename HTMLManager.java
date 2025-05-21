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
    
    // Returns the queue of HTMLTags being managed
    public Queue<HTMLTag> getTags() {
        return tags;
    }
    
    // Returns a string representation of the tags in the queue
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            HTMLTag tag = tags.remove();
            sb.append(tag.toString().trim());
            tags.add(tag);        
        }
        return sb.toString();

    
    }
    
    // Fixes the HTML structure
    public void fixHTML(){
        Stack<HTMLTag> stack = new Stack<>();
        Queue<HTMLTag> fixedQueue = new LinkedList<>();
        
        while (!tags.isEmpty()) {
            HTMLTag current = tags.remove();
            //check Selfclosing
            if (current.isSelfClosing()) {
                fixedQueue.add(current);
            //check opening
            } else if (current.isOpening()) {
                fixedQueue.add(current);
                stack.push(current);
            
            //check closing
            } else if(current.isClosing()) {
                //add matching close
                if (!stack.isEmpty() && stack.peek().matches(current)) {
                    fixedQueue.add(current);
                    stack.pop();
                } else if (!stack.isEmpty()) {
                    // mismatched closing tag
                    HTMLTag correctClose = stack.pop().getMatching();
                    fixedQueue.add(correctClose);
                } else {
                    // unmatched closing tag, discard it
                }
            }    
        }
        
        
        

        tags = fixedQueue;
    }    
    
}
