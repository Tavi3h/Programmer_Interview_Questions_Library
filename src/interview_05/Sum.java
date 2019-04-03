package interview_05;

public class Sum {
    public void sum() {
        int sum = 0;
        for (int i = 1; i < 100; i += 2) {
            sum += i;
        }
        System.out.println("1+3+5+...+99=" + sum);
    }
}
