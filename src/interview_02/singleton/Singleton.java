package interview_02.singleton;

// 单例模式
// 线程安全
public class Singleton {
    private static Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        Singleton s1 = getInstance();
        Singleton s2 = getInstance();
        System.out.println(s1 == s2);
    }
}
