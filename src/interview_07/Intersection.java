package interview_07;

import java.util.ArrayList;
import java.util.List;

class Interval {
    private final int start;
    private final int end;

    Interval(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

}

public class Intersection {
    public static List<Interval> intersect(List<Interval> coll1, List<Interval> coll2) {
        List<Interval> res = new ArrayList<>();
        int m = 0, n = 0;
        while (m < coll1.size() && n < coll2.size()) {
            Interval i1 = coll1.get(m);
            Interval i2 = coll2.get(n);
            if (i1.getStart() < i2.getStart()) {
                if (i1.getEnd() < i2.getStart()) {
                    ++m;
                } else if (i1.getEnd() < i2.getEnd()) {
                    res.add(new Interval(i2.getStart(), i1.getEnd()));
                    ++m;
                } else {
                    res.add(new Interval(i2.getStart(), i2.getEnd()));
                    ++n;
                }
            } else if (i1.getStart() <= i2.getEnd()) {
                if (i1.getEnd() <= i2.getEnd()) {
                    res.add(new Interval(i1.getStart(), i1.getEnd()));
                    ++m;
                } else {
                    res.add(new Interval(i1.getStart(), i2.getEnd()));
                    ++n;
                }
            } else {
                ++n;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Interval m1 = new Interval(1, 4);
        Interval m2 = new Interval(5, 7);
        Interval m3 = new Interval(9, 11);
        List<Interval> coll1 = new ArrayList<>();
        coll1.add(m1);
        coll1.add(m2);
        coll1.add(m3);

        Interval n1 = new Interval(2, 6);
        Interval n2 = new Interval(6, 8);
        Interval n3 = new Interval(11, 12);
        List<Interval> coll2 = new ArrayList<>();
        coll2.add(n1);
        coll2.add(n2);
        coll2.add(n3);
    }
}
