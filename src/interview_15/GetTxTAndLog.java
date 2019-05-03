package interview_15;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

public class GetTxTAndLog {
    public void getTxTandLog() {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        getTxTandLog(path);
        scanner.close();
    }

    private void getTxTandLog(String path) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                getTxTandLog(path + "\\" + fileName);
            } else {
                if (fileName.endsWith(".txt") || fileName.endsWith(".log")) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
    
    @Test
    public void testCase() {
        getTxTandLog();
    }
}
