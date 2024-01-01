package org.example;

import scala.concurrent.impl.FutureConvertersImpl;

import java.util.Scanner;

public class Practice {
     public static void main(String[] args) {
        Scanner p = new Scanner(System.in);
        int n = p.nextInt();
        String[] array = new String[n]; 
        for (int i =1; i <=n; i++)
        {
            if(i%3==0 && i%5==0)
            {
                array[i-1] = "FIZZBUZZ";
            } else if (i%3==0)
            {
              array[i-1]  = "FIZZ";
            }
            else if (i%5==0)
            {
                array[i-1] = "BUZZ";
            }
            else
            {
                array[i-1] = String.valueOf(i);
            }
        }

     System.out.println(java.util.Arrays.toString(array));

    }

}
