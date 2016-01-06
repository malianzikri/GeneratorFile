
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kamal Bakri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int loop = 0;
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int tmp = sc.nextInt();
        if (tmp > Math.pow(10, 5)) {
            loop = (int) Math.pow(10, 5);
        } else if (tmp <= 0) {
            loop = 1;
        } else {
            loop = tmp;
        }

        for (int x = 0; x < loop; x++) {
            int a = sc.nextInt();
            if (a >= 1 && a <= Math.pow(10, 9)) {
                list.add(a);
            }
        }

        for (int j : list) {
            float s3 = (33/ 2f) * (2 * 3 + (33 - 1) * 3);
//            float s5 = (((j - 1) / 5) / 2f) * (2 * 5 + (((j - 1) / 5) - 1) * 5);
//            float s15 = (((j - 1) / 15) / 2f) * (2 * 15 + (((j - 1) / 15) - 1) * 15);
            float result = s3 ;
            System.out.println((int) result);
        }

    }

}
