package app.dll.test.presentation.utils.dateUtils;

import com.kosherjava.zmanim.hebrewcalendar.JewishCalendar;

import java.util.HashMap;
import java.util.Map;

public class TehillimDetails {
    public static Map<Integer, int[]> dailyTehillim = new HashMap<>();
    public static void Initialize(){

        dailyTehillim.put(1,  new int[]{1,2,3,4,5,6,7,8,9});
        dailyTehillim.put(2,  new int[]{10,11,12,13,14,15,16,17});
        dailyTehillim.put(3,  new int[]{18,19,20,21,22,23,24,25,26,27,28,29,30});
        dailyTehillim.put(4,  new int[]{31,32,33,34,35,36,37});
        dailyTehillim.put(5,  new int[]{38,39,40,41,42,43});
        dailyTehillim.put(6,  new int[]{44,45,46,47,48,49,50});
        dailyTehillim.put(7,  new int[]{51,52,53,54,55,56,57});
        dailyTehillim.put(8,  new int[]{58,59,60,61,62,63,64,65});
        dailyTehillim.put(9,  new int[]{66,67,68,69,70,71});
        dailyTehillim.put(10, new int[]{72,73,74,75,76,77,78});
        dailyTehillim.put(11, new int[]{79,80,81,82,83,84,85});
        dailyTehillim.put(12, new int[]{86,87,88,89,90});
        dailyTehillim.put(13, new int[]{91,92,93,94,95});
        dailyTehillim.put(14, new int[]{96,97,98,99,100,101,102});
        dailyTehillim.put(15, new int[]{103,104,105});
        dailyTehillim.put(16, new int[]{106,107});
        dailyTehillim.put(17, new int[]{108,109,110,111,112,113,114,115});
        dailyTehillim.put(18, new int[]{116,117,118});
        dailyTehillim.put(19, new int[]{119}); // Longest chapter
        dailyTehillim.put(20, new int[]{120,121,122,123,124,125,126,127,128,129,130,131,132,133,134});
        dailyTehillim.put(21, new int[]{135,136,137,138,139});
        dailyTehillim.put(22, new int[]{140,141,142,143,144});
        dailyTehillim.put(23, new int[]{145,146,147});
        dailyTehillim.put(24, new int[]{148,149,150});
        dailyTehillim.put(25, new int[]{1,2,3,4,5,6,7,8});
        dailyTehillim.put(26, new int[]{9,10,11,12,13,14,15});
        dailyTehillim.put(27, new int[]{16,17,18,19,20,21});
        dailyTehillim.put(28, new int[]{22,23,24,25,26,27,28});
        dailyTehillim.put(29, new int[]{29,30,31,32,33,34,35});
        dailyTehillim.put(30, new int[]{36,37,38,39,40,41,42,43,44,45});

    }
    public static int[] getDailyTehillim(){
        Initialize();
        JewishCalendar jewishCalendar = new JewishCalendar();
        int hebrewDay = jewishCalendar.getJewishDayOfMonth();
        return dailyTehillim.get(hebrewDay);
    }
}
