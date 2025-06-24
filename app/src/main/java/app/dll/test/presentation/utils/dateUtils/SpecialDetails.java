package app.dll.test.presentation.utils.dateUtils;

import android.util.Log;

import com.kosherjava.zmanim.hebrewcalendar.JewishCalendar;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class SpecialDetails {

    private static final Map<Integer, String> GEMATRIA_VALUES = new LinkedHashMap<>();

    static {

        GEMATRIA_VALUES.put(400, "ת");
        GEMATRIA_VALUES.put(300, "ש");
        GEMATRIA_VALUES.put(200, "ר");
        GEMATRIA_VALUES.put(100, "ק");

        GEMATRIA_VALUES.put(90, "צ");
        GEMATRIA_VALUES.put(80, "פ");
        GEMATRIA_VALUES.put(70, "ע");
        GEMATRIA_VALUES.put(60, "ס");
        GEMATRIA_VALUES.put(50, "נ");
        GEMATRIA_VALUES.put(40, "מ");
        GEMATRIA_VALUES.put(30, "ל");
        GEMATRIA_VALUES.put(20, "כ");
        GEMATRIA_VALUES.put(10, "י");

        GEMATRIA_VALUES.put(9, "ט");
        GEMATRIA_VALUES.put(8, "ח");
        GEMATRIA_VALUES.put(7, "ז");
        GEMATRIA_VALUES.put(6, "ו");
        GEMATRIA_VALUES.put(5, "ה");
        GEMATRIA_VALUES.put(4, "ד");
        GEMATRIA_VALUES.put(3, "ג");
        GEMATRIA_VALUES.put(2, "ב");
        GEMATRIA_VALUES.put(1, "א");
    }

    private static final Map<String, Integer> GEMATRIA_VALUES_REVERSED = new LinkedHashMap<>();

    static {
        GEMATRIA_VALUES_REVERSED.put("א", 1);
        GEMATRIA_VALUES_REVERSED.put("ב", 2);
        GEMATRIA_VALUES_REVERSED.put("ג", 3);
        GEMATRIA_VALUES_REVERSED.put("ד", 4);
        GEMATRIA_VALUES_REVERSED.put("ה", 5);
        GEMATRIA_VALUES_REVERSED.put("ו", 6);
        GEMATRIA_VALUES_REVERSED.put("ז", 7);
        GEMATRIA_VALUES_REVERSED.put("ח", 8);
        GEMATRIA_VALUES_REVERSED.put("ט", 9);
        GEMATRIA_VALUES_REVERSED.put("י", 10);
        GEMATRIA_VALUES_REVERSED.put("כ", 20);
        GEMATRIA_VALUES_REVERSED.put("ל", 30);
        GEMATRIA_VALUES_REVERSED.put("מ", 40);
        GEMATRIA_VALUES_REVERSED.put("נ", 50);
        GEMATRIA_VALUES_REVERSED.put("ס", 60);
        GEMATRIA_VALUES_REVERSED.put("ע", 70);
        GEMATRIA_VALUES_REVERSED.put("פ", 80);
        GEMATRIA_VALUES_REVERSED.put("צ", 90);
        GEMATRIA_VALUES_REVERSED.put("ק", 100);
        GEMATRIA_VALUES_REVERSED.put("ר", 200);
        GEMATRIA_VALUES_REVERSED.put("ש", 300);
        GEMATRIA_VALUES_REVERSED.put("ת", 400);
        GEMATRIA_VALUES_REVERSED.put("ך", 20);
        GEMATRIA_VALUES_REVERSED.put("ם", 40);
        GEMATRIA_VALUES_REVERSED.put("ן", 50);
        GEMATRIA_VALUES_REVERSED.put("ף", 80);
        GEMATRIA_VALUES_REVERSED.put("ץ", 90);
    }
    
    
    static JewishCalendar jewishCalendar = new JewishCalendar();
    //TODO make a transliteration
    static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Return as is if null or empty
        }
        // Capitalize the first letter and keep the rest unchanged
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String getTorahPortion(){
       return capitalizeFirstLetter(String.valueOf(jewishCalendar.getUpcomingParshah()).toLowerCase(Locale.ROOT)) ;
    }

//TODO add special dates to main header!

//    public static boolean isYomTovThisWeek() {
//        JewishCalendar jewishCalendar = new JewishCalendar();
//
//
//        // Loop through the next 7 days to check for Yom Tov
//        for (int i = 0; i < 7; i++) {
//            jewishCalendar.add(today, 1);
//            if (jewishCalendar.isYomTov()) {
//                return true; // Found a Yom Tov
//            }
//        }
//
//        return false; // No Yom Tov found in the next 7 days
//    }



    private static String getYomTovName(JewishCalendar jewishCalendar, String language) {
        if (jewishCalendar.isYomTov()) {
            String name;
            switch (jewishCalendar.getYomTovIndex()) {
                case JewishCalendar.ROSH_HASHANA:
                    if ("he".equals(language)) {
                        name = "ראש השנה";
                    } else if ("ru".equals(language)) {
                        name = "Рош Ха-Шана";
                    } else {
                        name = "Rosh Hashanah";
                    }
                    break;
                case JewishCalendar.YOM_KIPPUR:
                    if ("he".equals(language)) {
                        name = "יום כיפור";
                    } else if ("ru".equals(language)) {
                        name = "Йом Кипур";
                    } else {
                        name = "Yom Kippur";
                    }
                    break;
                case JewishCalendar.SUCCOS:
                    if ("he".equals(language)) {
                        name = "סוכות";
                    } else if ("ru".equals(language)) {
                        name = "Суккот";
                    } else {
                        name = "Sukkot";
                    }
                    break;
                case JewishCalendar.CHOL_HAMOED_SUCCOS:
                    if ("he".equals(language)) {
                        name = "חול המועד סוכות";
                    } else if ("ru".equals(language)) {
                        name = "Холь Ха-Моэд Суккот";
                    } else {
                        name = "Chol Hamoed Sukkot";
                    }
                    break;
                case JewishCalendar.SHEMINI_ATZERES:
                    if ("he".equals(language)) {
                        name = "שמיני עצרת";
                    } else if ("ru".equals(language)) {
                        name = "Шмини Ацерет";
                    } else {
                        name = "Shemini Atzeret";
                    }
                    break;
                case JewishCalendar.SIMCHAS_TORAH:
                    if ("he".equals(language)) {
                        name = "שמחת תורה";
                    } else if ("ru".equals(language)) {
                        name = "Симхат Тора";
                    } else {
                        name = "Simchat Torah";
                    }
                    break;
                case JewishCalendar.PESACH:
                    if ("he".equals(language)) {
                        name = "פסח";
                    } else if ("ru".equals(language)) {
                        name = "Песах";
                    } else {
                        name = "Pesach (Passover)";
                    }
                    break;
                case JewishCalendar.CHOL_HAMOED_PESACH:
                    if ("he".equals(language)) {
                        name = "חול המועד פסח";
                    } else if ("ru".equals(language)) {
                        name = "Холь Ха-Моэд Песах";
                    } else {
                        name = "Chol Hamoed Pesach";
                    }
                    break;
                case JewishCalendar.SHAVUOS:
                    if ("he".equals(language)) {
                        name = "שבועות";
                    } else if ("ru".equals(language)) {
                        name = "Шавуот";
                    } else {
                        name = "Shavuot";
                    }
                    break;
                case JewishCalendar.PURIM:
                    if ("he".equals(language)) {
                        name = "פורים";
                    } else if ("ru".equals(language)) {
                        name = "Пурим";
                    } else {
                        name = "Purim";
                    }
                    break;
                case JewishCalendar.SHUSHAN_PURIM:
                    if ("he".equals(language)) {
                        name = "שושן פורים";
                    } else if ("ru".equals(language)) {
                        name = "Шушан Пурим";
                    } else {
                        name = "Shushan Purim";
                    }
                    break;
                case JewishCalendar.CHANUKAH:
                    if ("he".equals(language)) {
                        name = "חנוכה";
                    } else if ("ru".equals(language)) {
                        name = "Ханука";
                    } else {
                        name = "Chanukah";
                    }
                    break;
                case JewishCalendar.TISHA_BEAV:
                    if ("he".equals(language)) {
                        name = "תשעה באב";
                    } else if ("ru".equals(language)) {
                        name = "Тиша Бе-Ав";
                    } else {
                        name = "Tisha BeAv";
                    }
                    break;
                case JewishCalendar.TU_BESHVAT:
                    if ("he".equals(language)) {
                        name = "ט\"ו בשבט";
                    } else if ("ru".equals(language)) {
                        name = "Ту би-Шват";
                    } else {
                        name = "Tu BiShvat";
                    }
                    break;
                default:
                    if ("he".equals(language)) {
                        name = "חג אחר";
                    } else if ("ru".equals(language)) {
                        name = "Другой праздник";
                    } else {
                        name = "Other holiday";
                    }
                    break;
            }
            return name;
        }

        if ("he".equals(language)) {
            return "אין חג";
        } else if ("ru".equals(language)) {
            return "Нет праздника";
        } else {
            return "No holiday";
        }
    }


    public static int hebrewNumbersToGematria(String number){
        Log.d("TAG", "hebrewNumbersToGematria: " + number);
        String[] splittedNumber = number.split("");
        int result = 0;
        for(String letter : splittedNumber){result += Integer.parseInt(String.valueOf(GEMATRIA_VALUES_REVERSED.get(letter)));}
        return result;
    }
    //TODO Make reverse func
}
