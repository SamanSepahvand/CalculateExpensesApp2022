package com.samansepahvand.calculateexpensesapp2022.infrastructure;

import android.animation.ObjectAnimator;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import com.samansepahvand.calculateexpensesapp2022.R;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.CalculateDate;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.DateModel;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.DateSingle;
import com.samansepahvand.calculateexpensesapp2022.bussines.metaModel.InfoMetaModel;
import com.samansepahvand.calculateexpensesapp2022.helper.CalendarTool;
import com.samansepahvand.calculateexpensesapp2022.helper.DateConverter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utility {


    public static void RotateImage(ImageView imageView) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 90f);
        animator.setDuration(200);
        animator.start();
    }


    public static int ChangeIconAddExpenses(int count) {
        if (count == 1) return R.drawable.ic_baseline_check_24;
        return R.drawable.ic_baseline_arrow_back_ios_24;

    }


    public static String getIranianDateInt() {
        CalendarTool tool = new CalendarTool();


        String date = String.format(Locale.US, "%d/%02d/%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay());
        return date;
    }


    public static String getIranianDateCustom(Calendar calendar) {
        Calendar cNew = Calendar.getInstance();
        CalendarTool tool = new CalendarTool(calendar);
        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cNew.get(Calendar.HOUR_OF_DAY),
                cNew.get(Calendar.MINUTE), cNew.get(Calendar.SECOND));
        return date;
    }

    public static String getIranianDate() {

        CalendarTool tool = new CalendarTool();
        Calendar cal = Calendar.getInstance();

        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return date;
    }


    public static int GetActionDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar mDate = Calendar.getInstance();
        return Integer.parseInt(dateFormat.format(mDate.getTime()));
    }

    public static int GetPrice(String priceString) {
        try {
            String split = priceString.replace(",", "");
            return Integer.parseInt(split);
        } catch (Exception e) {
            return 0;
        }
    }


    public static DateModel GetFirstLastDayMonthFarsi() {


        DateModel result = new DateModel();

        List<CalculateDate> dateListFinall = new ArrayList<>();

        dateListFinall.addAll(getCalculateDate(1));     /// curent month
        dateListFinall.addAll(getCalculateDate(-1));   // prev month
        dateListFinall.addAll(getCalculateDate(2));   // +1 month next


        Collections.sort(dateListFinall, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                CalculateDate p1 = (CalculateDate) o1;
                CalculateDate p2 = (CalculateDate) o2;
                return p1.getActionDate().toString().compareToIgnoreCase(p2.getActionDate().toString());
            }
        });

        List<CalculateDate> newDate = new ArrayList<>();

        String monthFarsi = getIranianDateInt().split("/")[1];

        for (CalculateDate item : dateListFinall) {
            CalculateDate calculateDate = new CalculateDate();

            String[] data = item.getFarsiDate().split("/");

            if (data[1].equals(monthFarsi)) {
                calculateDate.setActionDate(item.getActionDate());
                calculateDate.setFarsiDate(item.getFarsiDate());
                calculateDate.setEngDate(item.getEngDate());

                newDate.add(calculateDate);
            }
        }


        result.setFromDate(newDate.get(0).getActionDate());
        result.setFromDateFarsi(newDate.get(0).getFarsiDate());

        result.setToDate(newDate.get(newDate.size() - 1).getActionDate());
        result.setToDateFarsi(newDate.get(newDate.size() - 1).getFarsiDate());

        return result;


    }

    private static List<CalculateDate> getCalculateDate(int monthNumber) {

        List<CalculateDate> dateList1111 = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());


        switch (monthNumber) {

            case -1:
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DAY_OF_MONTH, 1);

                break;
            case 1:
                // cal.set(Calendar.MONTH, 1);
                break;

            case 2:
                cal.add(Calendar.MONTH, 1);
                break;

        }

        int maxDay1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        DateSingle dateSingle1 = getIranianDateCustomNew(cal);
        CalculateDate calculateDate11 = new CalculateDate();
        calculateDate11.setActionDate(Integer.parseInt(df1.format(cal.getTime())));
        calculateDate11.setFarsiDate(dateSingle1.getFarsiDate());
        calculateDate11.setEngDate(dateSingle1.getEngDate());
        dateList1111.add(calculateDate11);
        for (int i = 1; i < maxDay1; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            DateSingle dateSingle11 = getIranianDateCustomNew(cal);
            CalculateDate calculateDate121 = new CalculateDate();
            calculateDate121.setActionDate(Integer.parseInt(df1.format(cal.getTime())));
            calculateDate121.setFarsiDate(dateSingle11.getFarsiDate());
            calculateDate121.setEngDate(dateSingle11.getEngDate());
            dateList1111.add(calculateDate121);
        }

        return dateList1111;

    }

    public static DateSingle getIranianDateCustomNew(Calendar cal1) {
        CalendarTool tool = new CalendarTool(cal1);
        DateSingle dd = new DateSingle();
        dd.setFarsiDate(tool.getIranianFormattedDate());
        dd.setEngDate(tool.getGregorianDate());
        return dd;
    }


    public static String ShowTimeFarsiMeta(InfoMetaModel config) {
        //GetFarsiDate
        String[] farsiDate = config.getFarsiDate().split(" ");
        //Remove sec in Time
        String[] time = config.getFarsiDate().split(" ")[1].split(":");

        Spanned strHtml = Html.fromHtml((config.getEnglishDate() != null ? " <font color=#2e9699> <b> "
                + Utility.getDayName(config.getEnglishDate()) + farsiDate[0] +
                " ساعت:  " + time[0] + ":" + time[1] + " </b></font>" : "زمان نامشخص می باشد ! "));
        return strHtml.toString();
    }

    public static String getDayName(String date) {
        try {

            Date date1 = new Date(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            Calendar c = Calendar.getInstance();

            for (int i = 0; i < 7; i++) {
                c.add(Calendar.DATE, 1);
            }
            DateConverter converter = new DateConverter();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            String farsiDay = converter.FarsiDay(day);
            return farsiDay;
        } catch (Exception e) {
            return "";
        }
    }


    public static String SplitDigits(int number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols();
            decimalFormatSymbol.setGroupingSeparator(',');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbol);
            return decimalFormat.format(number);
        } catch (Exception ex) {
            return String.valueOf(number);
        }
    }


    public static String splitDigits(int number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols();
            decimalFormatSymbol.setGroupingSeparator(',');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbol);
            return decimalFormat.format(number);
        } catch (Exception ex) {
            return String.valueOf(number);
        }
    }


}
