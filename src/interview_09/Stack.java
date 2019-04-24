package interview_09;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

// 使用两个队列构造栈
public class Stack<T> {
    
    // queue1提供压栈功能，queue2提供弹栈功能。
    private Queue<T> queue1 = new LinkedList<>();
    private Queue<T> queue2 = new LinkedList<>();
    
    public void push(T t) {
        queue1.add(t);
    }
    
    public T pop() {
        if (queue1.isEmpty()) {
            return null;
        } else if (queue1.size() == 1) {
            return queue1.poll();
        } else {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            T res = queue1.poll();
            while (!queue2.isEmpty()) {
                queue1.add(queue2.poll());
            }
            return res;
        }
    }
    
    public boolean isEmpty() {
        return queue1.isEmpty();
    }
    
    @Test
    public void testCase() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
        assertEquals(null, stack.pop()); // 通过
    }
}
