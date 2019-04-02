[TOC]

# 问答题与编程题

## 真题一

### 问答题

**问题：**

1. 接口和抽象类有什么区别？
2. 实现多线程的方法有哪几种？
3. 利用递归求6!。
4. 用Java语言实现一个观察者模式。
5. 一个有10亿条记录的文本文件，已按照关键字排好序存储，请设计一个算法，可从文件中快速查找指定关键字的记录。

**解答：**

1. 接口和抽象类是支持抽象类定义的两种机制。二者具有很大的相似性，甚至在某些时候可以互换。但二者也具有很大区别。
具体而言，接口是公开的，里面不能有私有的方法或变量，是用于让别人使用的，而抽象类是可以有私有方法或私有变量的。在Java语言中，可以通过把类或者类中的某些方法声明为`abstract`（`abstract`不能用于修饰属性）来表示一个类是抽象类。接口就是指一个方法的集合，接口中所有方法都没有方法体（1.8后可以定义方法的默认实现）。在Java中接口是通过关键字`interface`来实现的。
包含一个或多个抽象方法的类就必须被声明为抽象类，抽象类可以声明方法的存在而不去实现它，被声明为抽象的方法不能包含方法体。在抽象类的子类中，实现方法必须含有相同的或者更高的访问级别。抽象类在使用的过程中不能被实例化，但是可以创建一个对象使其指向具体子类的一个实例。抽象类的子类为父类中所有抽象方法提供具体的实现，否则，它们也是抽象类。接口可以被看做是抽象类的变体，接口中所有的方法都是抽象的（1.8后可以定义方法的默认实现，即不再抽象），可以通过接口来间接地实现多重继承。接口中的成员变量均为`static final`的，成员变量可以使用`public`修饰。
接口与抽象类的不同点如下：
    - 接口只有定义，不能有方法的实现（1.8后可以定义方法的默认实现），而抽象类中可以定义具有实现的方法。
    - 使用`implements`实现接口，使用`extends`继承抽象类。一个类可以实现多个接口，但一个类只能继承一个抽象类。
    - 接口强调特定功能的实现，其设计理念是“like-a”关系，而抽象类强调所属关系，其设计理念为“is-a”关系。
    - 接口中定义的成员变量默认为`public static final`，只能够有静态的不能被修改的数据成员，而且，必须给其赋初值。而抽象类的成员变量默认为`default`，当然也可以被定义为`private`、`protected`和`public`。
    - 接口被运用于实现比较常用的功能，便于日后维护或者添加删除方法，而抽象类更倾向于充当公共类的角色，不适用于日后重新对里面的代码进行修改。
2. 三种方法：
    - 实现`Runnable`接口，并实现接口中的`run()`方法。
    ```java
    public class ImplRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread body");
        }
        
        @Test
        public void testCase() {
            new Thread(new ImplRunnable()).start();
        }
    }
    ```
    - 继承`Thread`类，重写`run()`方法。
    ```java
    public class ExtendsThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread body");
        }

        @Test
        public void testCase() {
            new ExtendsThread().start();
        }
    }
    ```
    - 实现`Callable`接口，重写`call()`方法。
    ```java
    public class ImplCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Hello World";
        }
        
        @Test
        public void testCase() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(new ImplCallable());
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    ```
3. 答案如下：
```java
public int fac(int n) {
    return n == 1 ? 1 : n * fac(n - 1);
}
```
4. 实现如下：
```java
// Subject
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// ConcreteSubject
class Weather implements Subject {
    
    private List<Observer> observers = new ArrayList<>();
    
    private double temperature;

    public void weatherChange() {
        notifyObservers();
    }
    
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Observer
interface Observer {
    void update(double temperature);
}

// ConcreteObserver
class WeatherDisplay1 implements Observer {

    private double temperature;
    
    public WeatherDisplay1(Subject weather) {
        weather.registerObserver(this);
    }
    
    @Override
    public void update(double temperature) {
        this.temperature = temperature;
        display();
    }
    
    public void display() {
        System.out.println("display1***** : " + temperature);
    }
}

// ConcreteObserver
class WeatherDisplay2 implements Observer {

    private double temperature;
    
    public WeatherDisplay2(Subject weather) {
        weather.registerObserver(this);
    }
    
    @Override
    public void update(double temperature) {
        this.temperature = temperature;
        display();
    }
    
    public void display() {
        System.out.println("display1----- : " + temperature);
    }
}

public class ObserverPattern {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Weather weather = new Weather();
        WeatherDisplay1 display1 = new WeatherDisplay1(weather);
        WeatherDisplay2 display2 = new WeatherDisplay2(weather);
        weather.setTemperature(27);
        weather.setTemperature(20);
    }
}
```
5. 如果有足够大的内存，则可以一次性读入全部数据，然后进行查找。如果无法一次性将全部信息读入，则需要将数据进行分解。例如将数据分为100份，把每份的第一条记录关键字和此记录对应的文件偏移量先读入内存，然后根据指定关键字定位出指定关键字所在的记录块，将相应的记录块读入内存，最后进行二分查找。

## 真题二

### 问答题

**问题：**

1. `List<? extends T>`和`List<? super T>`之间有什么区别？
2. 给出两种单例模式的实现方法，并说明这两种方法的优缺点。
3. 描述Java语言中抽象基类和接口各自主要使用的场景。
4. `int`和`Integer`的区别是什么？
5. 已知和两个链表`head1`和`head2`各自有序，请把它们合并成一个依然有序的链表。结果链表要包含`head1`和`head2`的所有结点，即结点值相同。
6.给定a、b两个文件，各存放50亿个url，每个url各占64B，内存限制是4GB，请找出文件a与文件b中共同的url。

**解答：**

1. `List<? extends T>`只能读数据，不能存数据。`List<? super T>`可以存入数据，存入数据需要是类型`T`或者其子类类型。读取时只能使用`Object`类型进行接收。PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。即频繁往外读取内容的，适合用上界Extends，经常往里插入的，适合用下界Super。
```java
public static <T> void copy(List<? super T> dest, List<? extends T> src) {
    for (int i = 0; i < src.size(); i++) {
        dest.set(i, src.get(i));
    }
}
```
2. 四种方法：
    - 饿汉式，线程安全：
    ```java
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
    ```
    - 懒汉式I，线程安全，锁粒度较大：
    ```java
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
    ```
    - 懒汉式II，线程安全，锁粒度较小：
    ```java
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
    ```
    - 使用枚举，线程安全：
    ```java
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
    ```
3. 接口是一种特殊形式的抽象类，使用接口完全有可能实现与抽象类相同的操作，但一般而言，抽象类多用于在同类事物中有无法具体描述的方法的场景，所以，当子类和父类之间存在有逻辑上的层次结构时，推荐使用抽象类，而接口多用于不同类之间，定义不同类之间的通信规则。所以，当希望支持差别较大的两个或者更多对象之间的特定交互行为时，应该使用接口。使用接口能大大降低软件系统的耦合度。
4. `int`和`Integer`的区别如下：
    - `int`是基本数据类型，其默认值为0。`Integer`是`int`的包装类，默认值为`null`。`int`无法区分未赋值与赋值为0的情况，而`Integer`可以。
    - `int`类型在传递时是值传递，而`Integer`是引用传递。
    - `int`类型只能用于运算，而`Integer`类提供许多有用的方法。
    - 容器类无法存放`int`类型，只能存放`Integer`类型。
5. 两种答案：
    - 递归：
    ```java
    public ListNode merge(ListNode head1, ListNode head2) {

        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        if (head1.val < head2.val) {
            curr.next = head1;
            curr = curr.next;
            curr.next = merge(head1.next, head2);
        } else {
            curr.next = head2;
            curr = curr.next;
            curr.next = merge(head1, head2.next);
        }
        return dummy.next;
    }
    ```
    - 循环：
    ```java
    public ListNode merge(ListNode head1, ListNode head2) {

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        curr.next = head1 == null ? head2 : head1;
        return dummy.next;
    }
    ```
6. 两种方案：
    - Hash法：首先遍历文件a，对每个url求hash值并散列到1000个文件中，即`h = hash(url) % 1000`，然后将结果文件存放到文件fa中。通过散列，所有的url将会分布在fa0、fa1、...、fa999这1000个文件中。同理，将文件b中的url也进行同样的散列，所有的url将会分布在fb0、fb1、...、fb999这1000个文件中。显然，与fa0中相同的url只可能存在与fb0中，因此只需要分别找出文件$fa_i$和$fb_i$中相同的url即可。
    - Bloom filter法：该方法的接单具体参见原书P127页。

## 真题三

### 问答题

**问题：**

1. 接口能否继承接口？抽象类是否可以实现接口？抽象类是否可继承实体类？
2. 面向对象的特征有哪些方面？
3. `String`和`StringBuffer`有什么区别？
4. `final`、`finally`和`finalize`的区别是什么？
5. `ArrayList`、`Vector`和`LinkedList`有什么特点？`HashMap`和`Hashtable`有什么区别？

**解答：**

1. 接口可以继承接口；抽象类可以实现接口；抽象类可以继承实体类。
2. 四个特征：
    - 抽象：抽象就是忽略一个主题中与当前目标无关的那些方面，以便更充分地注意与当前目标有关的方面。抽象并不打算了解全部问题，而只是选择其中的一部分，暂时不用关注细节。抽象包含两个方面内容，一是过程抽象，二是数据抽象。
    - 继承：继承是一种联结类的层次模型，并且允许和鼓励类的重用，它提供了一种明确表述共性的方法。对象的一个新类可以从现有类中派生，这个过程称为类继承。新类继承了原始类的特性，新类称为原始类和派生类，而原始类称为新类的基类。派生类可以从它的基类哪里继承方法和实例变量，并且子类可以修改或增加新的方法使之更适合特殊的需要。
    - 封装：封装是指将客观事物抽象成类，每个类对自身的数据和方法实行保护。类可以把自己的数据和方法只让可信的类或者对象操作，对不可信的进行信息的隐藏。
    - 多态：封装是指允许不同类的对象对同一消息做出响应。多态包括参数化多态和包含多态。 
3. `String`是不可变类，而`StringBuffer`是可变类。`String`是不可变类，也就是说，`String`对象一旦被创建，其值将不能被改变，而`StringBuffer`是可变类，对象被创建后，仍然可以对其值进行修改。如果一个字符串经常需要被修改的时候，使用`StringBuffer`，使用`StringBuffer`有更高的效率。
4. 区别如下：
    - `final`：用于声明属性、方法和类
        + `final`属性：被`final`修饰的变量不可变，其不可变指的是引用的不可变性，即它只能指向初始时指向的那个对象，而不关心指向对象的内容的变化。所以，被`final`修饰的变量必须被初始化。
        + `final`方法：被`final`声明的方法无法被子类重写。
        + `final`参数：用来表示这个参数在这个方法内部不允许被修改。
        + `final`类：当一个类被声明为`final`时，表示此类不能被继承，所有方法都不能被重写。
    - `finally`：`finally`作为异常处理的一部分，它只能用在`try/catch`语句中，并且附带这一个语句块，表示这段语句最终一定会被执行。
    - `finalize`：`finalize`是`Object`类的一个方法，在垃圾收集器执行的时候会调用被回收对象的`finalize()`方法，可以覆盖此方法来实现对其他资源的回收。
5. 解答：
    - `ArrayList`、`Vector`和`LinkedList`的特点：
        + `ArrayList`：内部使用数组实现，支持使用下标进行检索。由于内部使用数组实现，所以查找数据的速度较快，但插入数据的速度较慢。动态扩充数组大小时会默认扩充为原来的1.5倍，即`int newCapacity = oldCapacity + (oldCapacity >> 1);`。
        + `Vector`：内部使用数组实现，同样支持使用下标进行检索。`Vector`是线程安全的，其绝大部分方法均被`synchronized`修饰，这也导致其效率比`ArrayList`略低。动态扩充数组大小时会默认扩充为原来的2倍，同时可以设置每次扩充的大小。`int newCapacity = oldCapacity + ((capacityIncrement > 0) ?                    capacityIncrement : oldCapacity);`
        + `LinkedList`：内部使用双向链表实现，不能使用索引进行数据的查找。查找数据的效率较低，插入数据的效率较高，因为在插入数据时不需要对数据进行移动。
    - `HashMap`和`Hashtable`区别：
        + `HashMap`是非线程安全的，`Hashtable`是线程安全的。
        + `HashMap`允许空键、空值，`Hashtable`不允许空键、空值。
        + `HashMap`将`Hashtable`的`contains`方法去掉了，改成了`containsValue`和`containKey`。
        + `HashMap`继承自`AbstractMap`，`Hashtable`继承自`Dictionary`。
        + `HashMap`使用`Iterator`遍历，`Hashtable`使用`Enumeration`遍历。
        + `HashMap`的默认大小是16，`Hashtable`的默认大小是11。

### 附加题

**问题：**

1. 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节街区的字符串。但是要保证汉字不被截半个，例如“人ABC” 4，应该截为“人AB”，输入“人ABC们DEF” 6，应该输出为“人ABC”而不是“人ABC+们的半个”。
2. 排序有哪几种方法？用Java语言实现一个插入排序？

**解答：**

1. 代码如下：
```java
// 对Java而言，用char类型表示汉字时需要两个字节，表示英文字母时需要一个字节。
public String cut(String s, int b) {
    
    if (b == 0) {
        return "";
    }

    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (char c : s.toCharArray()) {
        if (count < b) {
            if (String.valueOf(c).getBytes().length > 1) {
                if (count + 1 == b) {
                    return sb.toString();
                }
                count += 2;
            } else {
                ++count;
            }
            sb.append(c);
        } else {
            break;
        }
    }
    return sb.toString();
}
```
2. 排序方法主要有：插入排序、选择排序、希尔排序、快速排序、堆排序、归并排序、基数排序、桶排序。
插入排序实现：
```java
public static <T extends Comparable<? super T>> void sort(T[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
        for (int j = i; j > 0; j--) {
            if (less(a[j], a[j - 1])) {
                exch(a, j, j - 1);
            } else {
                break;
            }
        }
    }
}

private static <T extends Comparable<? super T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
}

private static <T> void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
}
```

## 真题四

### 简答题

**问题：**

1. `HashMap`和`Hashtable`的区别是什么？
2. `&`和`&&`的区别是什么？
3. `Collection`和`Collections`的区别是什么？
4. `abstract class`和`interface`的区别是什么？
5. `final`、`finally`和`finalize`的区别是什么？

**解答：**

1. 参见真题三问答题中问题5的答案。
2. `&`是按位与运算符，将运算的双方转为二进制后，再按位进行与运算。`&&`为逻辑与运算符，当且仅当运算的双方均为`true`时，运算的结果为`true`。`&&`具有短路功能。
3. `Collection`是一个集合接口。它提供了对集合对象进行基本操作的通用接口方法。该接口的设计目标是为各种具体的集合提供最大化的统一的操作方法。`Collections`是针对集合类的一个工具类，它提供一系列静态方法来实现对各种集合的搜索、排序以及线程安全化等操作。
4. 参见真题一中问题1的答案。
5. 参见真题三问答题中问题4的答案。

### 加分题

**问题：**

1. 什么是设计模式？有哪些常见的设计模式？
2. 请简要介绍Spring MVC、IoC和AOP。

**解答：**
