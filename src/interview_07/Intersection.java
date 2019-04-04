package interview_07;

import java.util.List;

class Interval {
    private final int start;
    private final int end;

    Interval(int start, int end) {
        if (start >= end) {
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
        return null;
    }
}
