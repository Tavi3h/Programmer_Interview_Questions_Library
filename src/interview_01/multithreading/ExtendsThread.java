package interview_01.multithreading;

import org.junit.Test;

// 继承Thread类
public class ExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread body");
    }

    @Test
    public void testCase() {
        new ExtendsThread().start();
    }
    
}
