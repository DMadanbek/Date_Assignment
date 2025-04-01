import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

class DateOne implements Comparable<DateOne> {
    int day;
    int month;
    int year;

    public DateOne(int day, int month, int year){
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public boolean isValidDate() {
        try {
            LocalDate dt = LocalDate.of(year, month, day);
            return day > 0 && day <= dt.lengthOfMonth() && month > 0 && month <= 12;
        } catch (DateTimeException e) {
            System.out.println("Wrong numbers of date!");
            return false;
        }
    }

    public void updateDate(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    public String dayOfWeek() {
        String daydate = "";
        if (isValidDate()) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = day + "-" + month + "-" + year;

            try {
                Date date = dateFormatter.parse(dateString);
                daydate = "Day of week on " + dateString + " : " + new SimpleDateFormat("EEEE").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return daydate;
    }

    public long diffDays(DateOne startDate, DateOne endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String startDateString = startDate.day + "-" + startDate.month + "-" + startDate.year;
        String endDateString = endDate.day + "-" + endDate.month + "-" + endDate.year;

        Date d1 = sdf.parse(startDateString);
        Date d2 = sdf.parse(endDateString);

        long difference = d2.getTime() - d1.getTime();
        long difference_In_Days = (difference / (1000 * 60 * 60 * 24));
        return difference_In_Days;
    }

    public String printDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String date = String.format("%02d %s %d", day, getMonthName(month), year);
        return date;
    }

    private String getMonthName(int month) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return monthNames[month - 1];
    }

    @Override
    public int compareTo(DateOne date) {
        if (this.year != date.year) {
            return Integer.compare(this.year, date.year); // Сравнение по году
        }
        if (this.month != date.month) {
            return Integer.compare(this.month, date.month); // Сравнение по месяцу
        }
        return Integer.compare(this.day, date.day); // Сравнение по дню
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%d", day, month, year);
    }
}

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        ArrayList<DateOne> dates = new ArrayList<>();
        int inpDay = input.nextInt();
        int inpMonth = input.nextInt();
        int inpYear = input.nextInt();
        DateOne dateOne = new DateOne(inpDay, inpMonth, inpYear);
        System.out.println(dateOne.dayOfWeek());
        System.out.println(dateOne.printDate());

        // Добавляем объекты DateOne
        dates.add(new DateOne(11, 12, 2020));
        dates.add(new DateOne(20, 11, 1999));
        dates.add(new DateOne(19, 2, 1992));
        dates.add(new DateOne(10, 1, 2022));
        dates.add(new DateOne(23, 10, 2025));

        Collections.sort(dates);

        System.out.println(dates); // Выводим отсортированный список

        if (dateOne.isValidDate()) {

        } else {
            System.out.println("Do you want to change numbers of date? y/n");
            if (input.next().equals("y")) {
                dateOne.updateDate(input.nextInt(), input.nextInt(), input.nextInt());
            } else {
                System.exit(1);
            }
        }
    }
}
