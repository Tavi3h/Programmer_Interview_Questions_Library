package interview_07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumberMultiThreading {

    private static final int COUNT = 10000;
    private static final int THREAD_GROUP_COUNT = 5;
    private static final String INPUT = "testInput.txt";

    // 生成测试文件
    public static void generateTestFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(INPUT)), true);
        Random random = new Random();
        for (int i = 0; i < COUNT; i++) {
            pw.write(Math.abs(random.nextInt()) % COUNT + ",");
        }
        pw.flush();
        pw.close();
    }

    public static void main(String[] args) {
        try {
            generateTestFile();
            BufferedReader reader = new BufferedReader(new FileReader(INPUT));
            String str = reader.readLine();
            reader.close();
            String[] strs = str.split(",");
            int index = 0;
            int countForEachFile = COUNT / THREAD_GROUP_COUNT;

            for (int i = 0; i < THREAD_GROUP_COUNT; i++) {
                int[] records = new int[countForEachFile];
                for (int j = 0; j < countForEachFile; j++) {
                    records[j] = Integer.parseInt(strs[index++]);
                }
                PrintGroup printGroup = new PrintGroup(records, i);
                printGroup.startPrint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PrintGroup {
    // 这个线程组输出数字的个数
    private static volatile int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition oddLock = lock.newCondition();
    private Condition evenLock = lock.newCondition();

    // 这个线程需要输出的数字数组
    private int records[];
    // 这个线程组需要把数字输出到同一个文件，所以共享一个writer
    // 由于任意时刻只会有一个线程写文件，因此不需要同步
    private PrintWriter writer;

    // 记录输出奇数所在的下标
    private volatile int oddIndex = 0;
    // 记录输出偶数所在下标
    private volatile int evenIndex = 0;

    // 输出奇数的线程
    private OddPrintThread oddPrintThread;
    // 输出偶数的线程
    private EvenPrintThread evenPrintThread;

    private volatile boolean first = true;

    /**
     * 开启打印线程
     */
    public void startPrint() {
        oddPrintThread = new OddPrintThread();
        evenPrintThread = new EvenPrintThread();
        oddPrintThread.start();
        evenPrintThread.start();
    }

    public PrintGroup(int[] records, int id) throws IOException {
        this.records = records;
        this.writer = new PrintWriter(new FileWriter(new File("output" + id + ".txt")));
    }

    /**
     * 增加count计数
     */
    private synchronized static void addCount() {
        count++;
        if (count % 1000 == 0) {
            System.out.println("已完成：" + count);
            if (count == 10000) {
                System.out.println("Done");
            }
        }
    }

    /**
     * 奇数线程
     */
    private class OddPrintThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    lock.lock();

                    // 第一次执行先让偶数的线程先执行
                    if (first) {
                        first = false;
                        // 等待偶数线程执行完毕
                        evenLock.await();
                    }

                    for (int i = 0; i < 10;) {
                        // 数组中的偶数和奇数都输出完毕
                        if (oddIndex >= records.length && evenIndex >= records.length) {
                            writer.flush();
                            writer.close();
                            return;
                        }
                        // 如果所有的奇数都打印完毕了，则不打印奇数，让打印偶数的线程有机会
                        if (oddIndex >= records.length) {
                            break;
                        }

                        // 把奇数输出到文件，并计数
                        if (records[oddIndex] % 2 == 1) {
                            // i累加，目的是连续打印10个奇数，直到打印完成即可
                            i++;
                            writer.print(records[oddIndex] + " ");
                            writer.flush();
                            addCount();
                        }
                        // 让奇数游标继续往后
                        oddIndex++;
                    }
                    // 打印完10个数字后通知偶数线程继续打印
                    oddLock.signal();
                    // 接着等待打印偶数的线程结束
                    evenLock.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                oddLock.signal();
                lock.unlock();
            }
        }
    }

    /**
     * 偶数线程
     */
    private class EvenPrintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 让奇数线程先执行
                    while (first) {
                        Thread.sleep(1);
                    }
                    lock.lock();
                    for (int i = 0; i < 10;) {

                        if (oddIndex >= records.length && evenIndex >= records.length) {
                            writer.flush();
                            return;
                        }
                        // 如果遍历偶数的下标(evenIndex)已经到边际了
                        if (evenIndex >= records.length) {
                            break;
                        }
                        // 如果找到偶数
                        if (records[evenIndex] % 2 == 0) {
                            i++;
                            writer.print(records[evenIndex] + " ");
                            writer.flush();
                            addCount();
                        }
                        // 偶数游标继续往后
                        evenIndex++;
                    }
                    // 让奇数线程开始执行
                    evenLock.signal();
                    // 等待奇数数线程执行结束
                    oddLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    evenLock.signal();
                    lock.unlock();
                }
            }
        }
    }
}
