// Justis Smith
import java.util.Scanner;

public class CMSC401_A2 {
    static int leftv, rightv;
    public static void main(String[] args) {

        // initialize
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of houses
        int[] coords = new int[n];
        int[] houseSizes = new int[n];
        int totalSize = 0;
        
        // take in input
        for (int i = 0; i < n; i++){
            int y = sc.nextInt();
            int size = sc.nextInt();

            coords[i] = y;
            houseSizes[i] = size;

            totalSize = totalSize + size;
        }

        // create array with duplicated values per size of house
        int[] houses = new int[totalSize];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < houseSizes[i]; j++) {
                houses[k] = coords[i];
                //System.out.print(houses[k] + ", "); // Debug
                k++;
            }
        }

        sc.close();

        // find median
        int median;
        int length = houses.length;
        leftv = -1;
        rightv = -1;

        // if length is odd
        if(length % 2 == 1){
            Quicksort(houses, 0, length-1, length/2);
            median = rightv;
        }
        // if length is even
        else{
            Quicksort(houses, 0, length-1, length/2);
            median = (leftv+rightv) / 2;
        }

        System.out.println(median);
        //System.out.println("Debug: End of program");
    }

    // Quicksort function
    public static int Quicksort(int A[], int p, int r, int midpoint){
        if(p <= r){
            int q = Partition(A, p, r); //find q, move elements around

            if(q == midpoint){
                rightv = A[q];
                if(leftv != -1){
                    return A[p];
                }
            }

            else if(q == (midpoint - 1)){
                leftv = A[q];
                if(rightv != -1){
                    return A[p];
                }
            }

            //if median is in left part
            if(q >= midpoint){
                return Quicksort(A, p, q-1, midpoint); 
            }

            //if median is in right part
            else {
                return Quicksort(A, q+1, r, midpoint);
            }
        }
        return A[p];
    }

    public static int Partition(int A[], int p, int r) {
        int range = (r - p) + 1;
        int randindex = (int)(Math.random() * range) + p;
        
        Swap(A, randindex, r); // swaps with last element
        
        int pivot = A[r]; // use last (new) as pivot
        int i = p;
    
        for (int j = p; j < r; j++) {
            if (A[j] < pivot) {
                Swap(A, i, j);
                i++;
            }
        }
    
        Swap(A, i, r);
        
        return i;
    }

    // swap function
    public static void Swap(int[] A, int i, int j){
        int temp = A[j];

        A[j] = A[i];
        A[i] = temp;
    }
}
