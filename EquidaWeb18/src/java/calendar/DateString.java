/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

/**
 *
 * @author slam
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateString {
    public static void main(String[]args){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String date=sdf.format(new Date());
    }
}
