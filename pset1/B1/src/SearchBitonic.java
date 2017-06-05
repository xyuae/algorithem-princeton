/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 05/07/2017
 *  The purpose of this program is to search if a key is in a bitonic array
 * An array is bitonic if it is comprised of an increasing sequence of integers followed
 * immediately by a decreasing sequence of integers.
 *  Usage: java ThreeSum
 *---------------------------------------------------------*/

import java.util.Arrays;


public class SearchBitonic{
    /**
     *
     * @param a, input integer array
     * @param key, int the target of three-sum
     * @return boolean, true if there is a element equal to key in the array a, false otherwise
     */
    public boolean SearchBitonic (int[] a, int key)
    {
        if (a == null) return false;
        int len = a.length;
        int mid = (len - 1) / 2;

        // decline
        if (a[mid] > a[mid + 1])
        {
            if (a[])
        }
        if (a[mid] > key) {
            if (a[mid] > a[mid + 1]) {
                return SearchDecline(Arrays.copyOfRange(a, mid + 1, a.length), key) || SearchBitonic(Arrays.copyOfRange(a, 0, mid), key);
            }
            // if the array is climbing
            else {
                return SearchClimb(Arrays.copyOfRange(a, 0, mid), key) || SearchBitonic(Arrays.copyOfRange(a, mid + 1, a.length), key);
            }
        } else if (a[mid] < key) {
            // the array is declining
            if (a[mid] > a[mid + 1]) {
                return SearchBitonic(Arrays.copyOfRange(a, 0, mid), key);
            } else {
                // the array is climbing
                return
            }
        }

    }

    private boolean SearchClimb(int[] a, int key) {
        if (a == null || a.length == 0) return false;
        return Arrays.binarySearch(a, key) != -1;
    }

    private boolean SearchDecline(int[] a, int key) {
        if (a == null || a.length == 0) return false;
        int len = a.length;
        int mid = (len - 1)/2;
        if (a[mid] > key && mid != len - 1) {
            return SearchDecline(Arrays.copyOfRange(a, mid + 1, a.length), key);
        } else if (a[mid] < key) {
            return SearchDecline(Arrays.copyOfRange(a, 0, mid), key);
        } else {
            return true;
        }
    }

    public static void main(String args[]) {
        ThreeSum test = new ThreeSum();
        assert test.GetThreeSum(new int[]{-1, 1, 0}, 0) == true;
        System.out.println("Passed");
    }
}




