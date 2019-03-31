package interview_02.singleton;

// 单例模式
// 线程安全，锁粒度大
public class SingletonLazyI {

    private static SingletonLazyI INSTANCE;
    private SingletonLazyI () {}
    public static synchronized SingletonLazyI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonLazyI();
        }
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        SingletonLazyI s1 = getInstance();
        SingletonLazyI s2 = getInstance();
        System.out.println(s1 == s2);
    }
}
