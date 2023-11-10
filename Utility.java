import java.util.Scanner;

public class Utility {
    // class ini nantinya akan banyak digunakan untuk memanggil secara berkala
    // baik itu dalam UI
    // error
    // atau yang lainnya
    public static void printLine(int loop, String line) {
        for (int i = 0; i < loop; i++) {
            System.out.print(line);
        }
        System.out.println();
    }

    public static void enterToContinue(Scanner scanner) {
        System.out.print("Press [ENTER] to Continue ");
        String enter = scanner.nextLine();
    }

    public static void printMenu() {
        String[] menu = { "Masukkan Tugas Baru", "Check Tugas (Selesai)", "Report Aktivitas Tugas",
                "Keluar Program" };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(" " + (i + 1) + ". " + menu[i]);
        }
        System.out.print("Masukkan Pilihan => ");
    }
}
