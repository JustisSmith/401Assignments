// Justis Smith
import java.util.Scanner;

public class CMSC401_A1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] list = new int[N];

        int cand1 = sc.nextInt();
        int cand2 = -1;
        int cnt1 = 1;
        int cnt2 = 1;
        list[0] = cand1;

        //finding candidates
        for (int i = 1; i < N; i++){
           int num = sc.nextInt();
           list[i] = num;

           //finding second candidate
           if (cand2 == -1){
            if (cand1 != num){cand2 = num;}
           }

           if (cnt1 == 0){cand1 = num;}
           if (cnt2 == 0){cand2 = num;}
           if (num == cand1){cnt1++;}
           else if (num == cand2){cnt2++;}
           else{cnt1--;
            cnt2--;}
        }

        //checking array
        int cntf1 = 0;
        int cntf2 = 0;
        for (int i = 0; i < N; i++){
            int num = list[i];
            if (num == cand1){cntf1++;}
            else if (num == cand2){cntf2++;}
        }

        //printing output
        if (cntf1 > N/3 && cntf2 > N/3){
            if (cand1 > cand2){
                System.out.println(cand2 + " " + cand1);
            }
            else if (cand2 > cand1){
                System.out.println(cand1 + " " + cand2);
            }
        }

        else{
            if (cntf1 > N/3){
                System.out.println(cand1);
            }
            else if (cntf2 > N/3){
                System.out.println(cand2);
            }
            else {
                System.out.println(-1);
            }
        }

        sc.close();

        //System.out.println("Debug: End of program");

    }
}
