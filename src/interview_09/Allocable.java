package interview_09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Allocable {
    public boolean is_allocable(int[] disk, int[] partition) {
        int i = 0, j = 0;
        while (i < disk.length && j < partition.length) {
            int size = disk[i++];
            while (partition[j] <= size) {
                size -= partition[j++];
                if (j == partition.length) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testCase() {
        int[] disk1 = { 120, 120, 120 };
        int[] disk2 = { 120, 50 };
        int[] disk3 = { 20, 100, 120 };
        int[] disk4 = { 1200 };
        int[] partition1 = { 60, 60, 80, 20, 20 };
        int[] partition2 = { 60, 80, 80, 20, 80 };
        int[] partition3 = { 110, 20 };
        int[] partition4 = { 100, 200, 300, 400, 100, 50, 60 };
        int[] partition5 = { 100, 200, 300, 400, 100, 50, 50 };
        assertEquals(true, is_allocable(disk1, partition1));
        assertEquals(false, is_allocable(disk1, partition2));
        assertEquals(true, is_allocable(disk2, partition3));
        assertEquals(false, is_allocable(disk3, partition3));
        assertEquals(false, is_allocable(disk4, partition4));
        assertEquals(true, is_allocable(disk4, partition5));
    }
}
