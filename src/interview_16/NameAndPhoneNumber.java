package interview_16;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class NameAndPhoneNumber {

    public static void main(String[] args) {
        String name = "", phoneNumber = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件保存路径...");
        String filePath = scanner.nextLine() + "//file.txt";
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            while (true) {
                System.out.println("请输入名字...或输入done退出");
                name = scanner.nextLine();
                if (name.trim().equalsIgnoreCase("done")) {
                    break;
                }
                System.out.println("请输入手机号码...或输入done退出");
                phoneNumber = scanner.nextLine();
                if (phoneNumber.trim().equalsIgnoreCase("done")) {
                    break;
                }
                bos.write((phoneNumber + "," + name + "\n").getBytes());
            }
            System.out.println("操作完成...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
