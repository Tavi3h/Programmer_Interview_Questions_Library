package interview_02;

import java.util.List;

// <? extends T>：不能存，其中存的元素均为T或T的子类。取出时需要T引用。
// <? super T>：可以存，存入的元素需均为T或T的子类。取出时需要使用Object引用。
public class Collections {
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }
}