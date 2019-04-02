package interview_01.multithreading;

import org.junit.Test;

// 实现Runnable接口
public class ImplRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread body");
    }
    
    @Test
    public void testCase() {
        new Thread(new ImplRunnable()).start();
    }

}
