package br.com.app.burg.burger.utils;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.app.burg.burger.MainActivity;


public class Util {

    //region GLOBAL VARIABLES
    private static MainActivity activity;
    private static FragmentManager fm;
    private static FragmentTransaction fgmTrans;
    private static Fragment fgmNew;
    //endregion

    //region PUBLIC CLASSES
    public static class Name {
        private String FirstName;
        private String LastName;

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }
    }
    //endregion

    //region PUBLIC METHODS
    public static String removeNonDigits(String text) {
        return text.replaceAll("[^\\d.]", "");
    }

    public static int stringToHexadecimal(String hex) {
        return Color.parseColor(hex);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = MyApplication.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MyApplication.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return MyApplication.getContext().getResources().getDisplayMetrics();
    }

    public static int dipToPixels(float dipValue) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, getDisplayMetrics()));
    }

    public static String formatMoney(double amount, boolean showCurrency) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        if (showCurrency) {
            return format.format(amount).replace("R$", "R$ ");
        } else {
            return format.format(amount).replace("R$", "");
        }
    }

    public static String formatMoney(BigDecimal amount, boolean showCurrency) {
        return (showCurrency ? "R$ " : "") + String.format("%.2f", amount);
    }

    public static Name splitName(String name) {
        Name objName = new Name();
        if (name.contains(" ")) {
            objName.setFirstName(name.substring(0, name.indexOf(" ")));
            objName.setLastName(name.substring(name.indexOf(" ") + 1));
        } else {
            objName.setFirstName(name);
            objName.setLastName("");
        }
        return objName;
    }



    public static String formatPhone(String phone) {
        phone = phone.replaceAll("[^0-9]", "");
        if (phone.length() > 11) {
            phone = phone.substring(2);
        }
        return String.format("(%s) %s-%s", phone.substring(0, 2), phone.substring(2, 7), phone.substring(7, 11));
    }

    public static String formatDateToSend(String date) {
        String[] tmpDateBirth = date.split("/");
        String dateBirth = String.format("%s-%s-%s", tmpDateBirth[2], tmpDateBirth[1], tmpDateBirth[0]);
        return dateBirth;
    }

    public static String formatDateToShow(Date date, boolean showHour) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy" + (showHour ? " HH:mm" : ""));
        String strDate = fmt.format(date);
        return strDate;
    }

    public static String formatDateToShow(String date, boolean showHour) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy" + (showHour ? " HH:mm" : ""));

        Date newDate;
        String finalDate = "";
        try {
            newDate = spf.parse(date);
            finalDate = fmt.format(newDate);
        } catch (Exception ex) {
        }

        return finalDate;
    }

    public static void showDefaultMessage(String message) {
        showDefaultMessage(message, Toast.LENGTH_SHORT, true);
    }

    public static void showDefaultMessage(String message, int Length) {
        showDefaultMessage(message, Length, true);
    }

    public static void showDefaultMessage(String message, int Length, Boolean center) {
        Toast toast = Toast.makeText(MyApplication.getContext(), message, Length);
        if (center) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }

    public static String getStringResource(String resName) {
        String result = "";
        try {
            result = MyApplication.getContext().getResources().getString(MyApplication.getContext().getResources().getIdentifier(resName, "string", MyApplication.getContext().getPackageName()));
        } catch (Exception ex) {
            result = "RES NOT FOUND";
        }
        return result;
    }

    public static int getColorResource(String resName) {
        int result = 0;
        try {
            result = MyApplication.getContext().getResources().getColor(MyApplication.getContext().getResources().getIdentifier(resName, "color", MyApplication.getContext().getPackageName()));
        } catch (Exception ex) {

        }
        return result;
    }



    public static String padZeros(int number, int zeros) {
        return String.format("%0" + String.valueOf(zeros) + "d", number);
    }

    public static String getFragmentName(Fragment fgm) {
        String name = fgm.getClass().getName().substring(fgm.getClass().getName().lastIndexOf(".") + 1);
        name = name.replace("Fragment", "");
        return name;
    }
    //endregion
}