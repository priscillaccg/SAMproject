/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Priscilla
 */
public class Util {

    public static Date formataData(String data) throws ParseException {
        String dateString = data;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        Date convertedDate = dateFormat.parse(dateString);
        
        return convertedDate;
    }
}
