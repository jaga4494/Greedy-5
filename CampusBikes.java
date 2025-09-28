/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. 
 * Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with 
the shortest Manhattan distance between each other, and assign the bike to that worker. 
(If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with 
the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). 
We repeat this process until there are no available workers.

https://leetcode.ca/all/1057.html
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
workers = [[0,0], [1,1], [2,0]]
bikes = [[1,0] [2,2], [2,1]]
 */

// TC: nm log(mn)
// SC: nm
import java.util.Arrays;
import java.util.PriorityQueue;
// "static void main" must be defined in a public class.
class Main {
    
    public static void main(String[] args) {
        
        
        int[][] workers = new int[][] {{0,0},{1,1},{2,0}};
        int[][] bikes = new int[][] {{1,0},{2,2},{2,1}};
        int [] result = assignBikes(workers, bikes);
        
        for (int a : result) {
            System.out.println(a);
        }
    }
    
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0) {
            return new int[] {};
        }

        int n = workers.length;
        int m = bikes.length;

        // instead of PQ, to reduce logn to n,
        // we can use a map of dist to list to (w,b) and maintain a min and max key for the map (to avoid TreeMap - which again takes log time)
        // no need to sort them internally based on w and b index because of the for loop that we will do like i = 0 to n and j = 0 to m. 
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            if (x[1] != y[1]) return x[1] - y[1];
            return x[2] - y[2];
        });


        for (int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);

                pq.add(new int[] {dist, i, j});

            }
        }

        int result[] = new int[n];
        Arrays.fill(result, -1);
        boolean assignedBikes[] = new boolean[m];

        int i = 0;
        while(i < n && !pq.isEmpty()) {
            int[] min = pq.poll();
            int dist = min[0];
            int w = min[1];
            int b = min[2];
            if (result[w] == -1 && assignedBikes[b] == false) {
                result[w] = b;
                assignedBikes[b] = true;
                i++;
            }
        }

        return result;


    }
}
