package interview_02.singleton;

// 单例模式
// 线程安全，锁粒度小
public class SingletonLazyII {
    private static SingletonLazyII INSTANCE;
    private SingletonLazyII() {}
    public static SingletonLazyII getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonLazyII.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonLazyII();
                }
            }
        }
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        SingletonLazyII s1 = getInstance();
        SingletonLazyII s2 = getInstance();
        System.out.println(s1 == s2);
    }
}
