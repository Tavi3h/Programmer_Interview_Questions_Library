package interview_09;

import org.junit.Test;

public class ProcessTime {
    public int[] estimate_process_time(int[] t, int n) {
        int[] processTime = new int[t.length];
        for (int i = 0; i < n; i++) {
            int minTime = processTime[0] + t[0];
            int minIndex = 0;
            for (int j = 1; j < t.length; j++) {
                if (minTime > processTime[j] + t[j]) {
                    minTime = processTime[j] + t[j];
                    minIndex = j;
                }
            }
            processTime[minIndex] += t[minIndex];
        }
        int totalTime = 0;
        for (int i = 0; i < processTime.length; i++) {
            System.out.println("第" + i + "个服务器上任务数为：" + processTime[i] / t[i]);
            totalTime = Math.max(totalTime, processTime[i]);
        }
        System.out.println("总耗时为：" + totalTime);
        return processTime;
    }

    @Test
    public void testCase() {
        int[] t = { 5, 2, 4, 10 };
        estimate_process_time(t, 6);
    }
}
