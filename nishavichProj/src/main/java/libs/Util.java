package libs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     * @return
     */
    public static String getDateAndTimeFormatted(){

        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime
     * @return
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String dateFormated = dateFormat.format(date);
//        System.out.println(dateFormated);
        return dateFormated;
    }

}
