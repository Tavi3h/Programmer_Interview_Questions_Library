package interview_12;

public class Ex {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Fx f = new Fx(5);
    }

    Ex() {
        System.out.println("Ex,no-args");
    }

    Ex(int i) {
        System.out.println("Ex,int");
    }
}

class Fx extends Ex {

    Fx() {
        super();
        System.out.println("Fx,no-args");
    }

    Fx(int i) {
        this();
        System.out.println("Fx,int");
    }
}