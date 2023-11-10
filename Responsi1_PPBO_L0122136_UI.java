public class Responsi1_PPBO_L0122136_UI {
    // untuk tampilan interface di console
    public static void topUIAssigment() {
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
        System.out.printf("|%39s%29s|\n", "Manage Tugas", " ");
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
        System.out.printf("|%2s%3s%2s|%13s%3s|%9s%1s|%9s%1s|%9s%1s|%9s%1s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Remainder", " ", "Overdue", " ");
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "-");
    }

    public static void topUIDone() {
        Responsi1_PPBO_L0122136_Utility.printLine((Responsi1_PPBO_L0122136_Main.MAX - 11), "=");
        System.out.printf("|%37s%20s|\n", "Finish-Done Tugas", " ");
        Responsi1_PPBO_L0122136_Utility.printLine((Responsi1_PPBO_L0122136_Main.MAX - 11), "=");
        System.out.printf("|%2s%-3s%2s|%13s%3s|%9s%1s|%9s%1s|%-9s%1s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Done", " ");
        Responsi1_PPBO_L0122136_Utility.printLine((Responsi1_PPBO_L0122136_Main.MAX - 11), "-");
    }

    public static void topUILate() {
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
        System.out.printf("|%39s%29s|\n", "Manage Tugas", " ");
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
        System.out.printf("|%2s%3s%2s|%13s%3s|%9s%1s|%9s%1s|%7s%3s|%7s%3s|\n", " ", "No", " ", "Nama Tugas", " ",
                "Due Out", " ", "Due Date", " ", "Done", " ", "Late", " ");
        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "-");
    }

}
