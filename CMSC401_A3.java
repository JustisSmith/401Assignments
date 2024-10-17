// Justis Smith
// program selects the second best route based on database of
// gas and motel costs
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CMSC401_A3 {
    public static void main(String[] args) { 	
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // number of cities
        int M = sc.nextInt(); // number of direct highways between cities
        int[] mprices = new int[N];
        int[][] matrix = new int[N][N];
    
        // take in motel prices
        mprices[0] = 0;
        mprices[1] = 0;
        for (int i = 0; i < N - 2; i++){
            int motelnum = sc.nextInt(); // don't need to use
            int motelprice = sc.nextInt();
    
            mprices[i + 2] = motelprice;
        }
    
        // initializes gas price matrix
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        // take in given gas prices into adjacency matrix
        for (int i = 0; i < M; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
    
            matrix[x - 1][y - 1] = z;
            matrix[y - 1][x - 1] = z;
        }
    
        dijkstra(matrix, mprices, N); 
        findSecondBest(matrix, mprices, N); 
        
        List<Integer> list = new ArrayList<>(TotalPrices);

        //System.out.println("Best Route:");    // for debugging
        //System.out.println(list.get(0));      // for debugging

        //System.out.println("Second Best Route:"); // for debugging
        System.out.println(list.get(1));
        
        sc.close();
    } 

    static List<Integer> TotalPrices = new ArrayList<>();

    // Dijkstra's algorithm
    static void dijkstra(int[][] matrix, int[] mprices, int N) {
        boolean[] visited = new boolean[N];
        int[] prices = new int[N]; 
        int[] previousCities = new int[N]; 

        // initialize prices array
        for (int i = 0; i < N; i++){
            prices[i] = Integer.MAX_VALUE; 
        }

        prices[0] = 0; 
        previousCities[0] = -1; 

        List<Integer> queue = new ArrayList<>();
        queue.add(0);

        while (queue.isEmpty() == false) {
            int currentCity = findMinIndex(prices, visited);

            if (currentCity == -1){
                break; // all cities have been visited
            }

            visited[currentCity] = true;

            for (int i = 0; i < N; i++) {
                int price = matrix[currentCity][i];

                if (visited[i] == false && // if city has not been visited
                price != Integer.MAX_VALUE && // if no edge
                (prices[currentCity] != Integer.MAX_VALUE) && // if no path
                (prices[currentCity] + price + mprices[i] < prices[i])){ // if new path is cheaper
                        prices[i] = prices[currentCity] + price + mprices[i];
                        previousCities[i] = currentCity;
                        queue.add(i);
                }
            }
        }

        TotalPrices.add(prices[1]);
        addToPath(1, previousCities); 
    } 
    
    //creates cheapest path
    static List<Integer> path = new ArrayList<>();
    static void addToPath(int i, int[] parents) {
        if (i == -1) {     	
            return;
        }

        addToPath(parents[i], parents);
        //System.out.println(parents[i]);   // for debugging 
        path.add(i);
    }
    
    // remove edges in the best path and compare
    static void findSecondBest(int[][] matrix, int[] mprices, int N) {
        int previousCity = -1;
        int previousCityDest = -1;
        int previousCitySource = -1;
        
        // makes a copy of the best path
        List<Integer> path2 = new ArrayList<>(path);

        for (int i = 0; i < path2.size() - 1; i++){
            int currentCity = path2.get(i);
            int nextCity = path2.get(i + 1);
    
            if (previousCity != -1){
                matrix[previousCitySource][previousCityDest] = previousCity;
                matrix[previousCityDest][previousCitySource] = previousCity;
            }
    
            previousCity = matrix[currentCity][nextCity];
            previousCitySource = currentCity;
            previousCityDest = nextCity;
    
            // removes edge
            matrix[currentCity][nextCity] = Integer.MAX_VALUE;
            matrix[nextCity][currentCity] = Integer.MAX_VALUE;
        }
        
        // run Dijkstra's again to find second best path
        dijkstra(matrix, mprices, N);
    }

    // finds minimum value index in prices array
    static int findMinIndex(int[] prices, boolean[] visited){
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++){
            if (visited[i] == false && prices[i] < minValue){
                minValue = prices[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
