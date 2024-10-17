// Justis Smith
import java.util.Scanner;

public class CMSC401_A0 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        int[] output = new int[lines];

        for (int i = 0; i <= lines - 1; i++){
            int nints = sc.nextInt();
            int[] nums = new int[nints-2];

            for (int j = 0; j <= (nints - 3); j++){
                int num = sc.nextInt();
                nums[j] = num;
            }

            int x = sc.nextInt();
            int y = sc.nextInt();

            if (nums[x-1] > nums[y-1]){
                output[i] = nums[x-1];
            } else {
                output[i] = nums[y-1];
            }

        }

        for (int k = 0; k <= lines-1; k++){
            System.out.println(output[k]);
        }

        sc.close();

        //System.out.println("Debug: End of Program");


    }
}
