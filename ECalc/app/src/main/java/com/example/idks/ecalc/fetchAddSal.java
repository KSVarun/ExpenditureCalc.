package com.example.idks.ecalc;


import android.icu.text.DecimalFormat;

/**
 * Created by idks on 3/14/2018.
 */

public class fetchAddSal {
    static int finalSalary ;
    static double exp ;
    static double percentage;

    public fetchAddSal(int finalSal){
       this.finalSalary = finalSal;
   }

    public static double getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public static void finalExp(int rExp){
        exp = exp+rExp;
    }

    public static double calcPer(){
        percentage = (exp/finalSalary)*100;
        percentage = (double) Math.round(percentage * 100) / 100;
        return percentage;
    }
}
