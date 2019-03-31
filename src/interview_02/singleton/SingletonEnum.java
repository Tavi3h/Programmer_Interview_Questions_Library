package interview_02.singleton;

public class SingletonEnum {
    
    private SingletonEnum() {}
    
    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    
    private static enum Singleton {
        INSTANCE;
        private SingletonEnum singleton;
        private Singleton() {
            singleton = new SingletonEnum();
        }
        public SingletonEnum getInstance() {
            return singleton;
        }
    }
    
    public static void main(String[] args) {
        SingletonEnum s1 = getInstance();
        SingletonEnum s2 = getInstance();
        System.out.println(s1 == s2);
    }

}
