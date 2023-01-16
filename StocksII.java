/* You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k... Find the maximum profit you can achieve. You may complete at most k transactions... Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again)...
 * Eg 1: prices = [2,4,1]                            k = 2                Output = 2 -> 4-2
 * Eg 2: prices = [3,2,6,5,0,3]                      k = 2                Output = 7 -> (6-2)+(3-0)
 * Eg 3: prices = [4,1,7,2,3,4,5,6,8,2,10]           k = 3                Output = 20 
 * Eg 4: prices = [2]                                k = 1                Output = 0
 */
import java.util.*;
public class StocksII
{
    public int MaxProfitKTransactions(int prices[], int k)
    {
        if(prices.length < 2)    // If there is only one day available...
            return 0;
        int i = prices.length-1, j = prices.length-2, sell = 0, buy = 0, profit = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());    // Priority Queue with reversed Priority...
        while(j >= 0)
        {
            if(prices[i] < prices[j])
            {   // If cannot attain profit at this instant...
                i--;
                j--;
            }
            if(prices[i] > prices[j])    // If we have a chance to get profit, we do a transaction...
            {
                sell = prices[i];    // Updating the selling price...
                while(j != 0 && prices[j-1] < prices[j])
                    j--;          // We evaluate the minimum cost price...
                buy = prices[j];
                i = j - 1;         // Decrementing the value...
                System.out.println("Buy : "+buy);
                System.out.println("Sell : "+sell);
                queue.add(sell-buy);       // Adding the profit to the Queue...
            }
            j--;
        }
        while(k != 0)
        {
            if(!queue.isEmpty())    // If the Queue is not empty and transaction state is valid...
                profit = profit + queue.poll();    // Adding the maximum profit achieved...
            k--;
        }
        return profit;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x, k;
        System.out.print("Enter the number of days : ");
        x = sc.nextInt();
        int array[] = new int[x];
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the price of Stock on "+(i+1)+" th day : ");
            array[i] = sc.nextInt();
        }
        System.out.print("Enter the number of transactions allowed : ");
        k = sc.nextInt();
        StocksII stocksII = new StocksII();       // Object creation...
        System.out.println("Maximum Profit : "+stocksII.MaxProfitKTransactions(array, k));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n/2) space...

/* DEDUCTIONS :- 
 * 1. We evaluate all the possible transactions iterating from right to left, since we are following the bottom-up approach...
 * 2. We store the profit at each transaction into a Reversed Priority Queue to get the profits of k transactions...
*/