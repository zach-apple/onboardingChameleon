package com.orasi.utils;

/**
 * Created by Adam on 12/22/2015.
 */
public class Sandbox3 {

    public static void main(String[] args) {
        Excel MyExcel = new Excel("C:\\Temp\\MyExcel.xlsx");
        String res = MyExcel.GetCellString(0, 0);
        int i = 0;
    }
}