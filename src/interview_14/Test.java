package interview_14;

class Base {

    int num = 1;
    public Base() {
        this.print();
        num = 2;
    }

    public void print() {
        System.out.println("Base.num = " + num);
    }
}

class Sub extends Base {

    int num = 3;

    public Sub() {
        this.print();
        num = 4;
    }

    public void print() {
        System.out.println("Sub.num = " + num);
    }
}

public class Test {
    public static void main(String[] args) {
        Base b = new Sub();
        System.out.println(b.num);
    }
}
