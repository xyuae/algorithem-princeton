/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 10/11/2016
 *  The purpose of this program is to check if three sum is available in N square run time
 *  Usage: java ThreeSum
 *---------------------------------------------------------*/

import java.util.Arrays;


public class ThreeSum{
    /**
     *
     * @param a, input integer array
     * @param key, int the target of three-sum
     * @return boolean, true if there is a combination that adds up to 0, false otherwise
     */
    public boolean GetThreeSum (int[] a, int key)
    {
        if (a == null || a.length <= 2) return false;
        Arrays.sort(a);

        int head = 0;
        int tail = a.length - 1;
        int i = head + 1;
        while (head < tail - 1)
        {
            int sum = a[head] + a[i] + a[tail];
            if (sum > key)
            {
                tail--;
                i = head + 1;
            } else if (sum < key)
            {
                if (i == tail - 1)
                {
                    head++;
                    i = head + 1;
                }
                else {
                    i++;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        ThreeSum test = new ThreeSum();
        assert test.GetThreeSum(new int[]{-1, 1, 0}, 0) == true;
        System.out.println("Passed");
    }
}




