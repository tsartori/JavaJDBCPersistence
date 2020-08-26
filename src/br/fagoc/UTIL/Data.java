/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.UTIL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Thiago Sartori
 */
public class Data {
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public String SomaDate(Integer QntdDias){//Dia atual + quantide de dias
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        calendario.add(Calendar.DATE, QntdDias);
        
        return dateFormat.format(calendario.getTime());
    }
    
    public String SomaDate(Integer QntdDias, Date pData){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(pData);
        calendario.add(Calendar.DATE, QntdDias);
        
        return dateFormat.format(calendario.getTime());
    }
    
}
