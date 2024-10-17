// Justis Smith
// Minimum Cost Rod Cutting
import java.util.Scanner;

public class CMSC401_A4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // size of rod
        int M = sc.nextInt(); // number of cutting points
        int[] locations = new int[M+2]; // locations of cutting points plus end points
        locations[0] = 0;
        locations[M+1] = N;

        for (int i = 1; i <= M; i++){
            locations[i] = sc.nextInt();
        }

        int[][] mem = new int[M + 2][M + 2];

        for (int len = 3; len <= M + 2; len++){
            for (int i = 0; i <= M + 2 - len; i++){
                int j = i + len - 1;
                mem[i][j] = Integer.MAX_VALUE;
                
                for (int k = i + 1; k < j; k++){
                    int currentRod = locations[j] - locations[i];
                    int subRod1 = mem[i][k];
                    int subRod2 = mem[k][j];
                    int cost = currentRod + subRod1 + subRod2;
                    mem[i][j] = Math.min(mem[i][j], cost);
                }
            }
        }

        System.out.println(mem[0][M+1]);


        sc.close();
    }

}



