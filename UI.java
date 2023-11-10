public class UI {
    // untuk tampilan interface di console
    public static void topUIAssigment() {
        Utility.printLine(Main.MAX, "=");
        System.out.printf("|%39s%29s|\n", "Manage Tugas", " ");
        Utility.printLine(Main.MAX, "=");
        System.out.printf("|%2s%3s%2s|%13s%3s|%9s%1s|%9s%1s|%9s%1s|%9s%1s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Remainder", " ", "Overdue", " ");
        Utility.printLine(Main.MAX, "-");
    }

    public static void topUIDone() {
        Utility.printLine((Main.MAX - 11), "=");
        System.out.printf("|%37s%20s|\n", "Finish-Done Tugas", " ");
        Utility.printLine((Main.MAX - 11), "=");
        System.out.printf("|%2s%-3s%2s|%13s%3s|%9s%1s|%9s%1s|%-9s%1s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Done", " ");
        Utility.printLine((Main.MAX - 11), "-");
    }

    public static void topUILate() {
        Utility.printLine(Main.MAX, "=");
        System.out.printf("|%39s%29s|\n", "Manage Tugas", " ");
        Utility.printLine(Main.MAX, "=");
        System.out.printf("|%2s%3s%2s|%13s%3s|%9s%1s|%9s%1s|%7s%3s|%7s%3s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Done", " ", "Late", " ");
        Utility.printLine(Main.MAX, "-");
    }

}
