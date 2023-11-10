import java.util.Scanner;
import java.util.regex.Pattern;

public class Responsi1_PPBO_L0122136_Verify {

    public static int checkInt(Scanner scanner) throws IllegalArgumentException {
        String inputTemp = scanner.nextLine();
        int inputInt = -1;
        try {
            inputInt = Integer.parseInt(inputTemp);
            if (inputInt < 0) {
                throw new IllegalArgumentException("Input harus merupakan bilangan bulat positif.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input tidak valid. Harus berupa bilangan bulat.");
        }
        return inputInt;
    }
    // class ini digunakan untuk mengecek inputan user agar sesuai yang diiinginkan
    // nantinya kelas ini akan melempar exception

    public static String checkNameTask(Scanner scanner) {
        String agenda = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Nama Agenda => ");
            agenda = scanner.nextLine().trim();
            if (!agenda.isEmpty() && agenda.length() <= 15) {
                validInput = true;
            } else {
                System.out.println("Nama Agenda diantara 0-15 kata.");
                Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
            }
        }
        return agenda;
    }

    public static String checkDate(Scanner scanner) {
        boolean inputValid = false;
        String inputValidDate = null;
        while (!inputValid) {
            System.out.print("format(yyyy-mm-dd) => ");
            inputValidDate = scanner.nextLine().trim();
            if (Pattern.compile("^\\d{4}-(0\\d|1[0-2])-(0\\d|[12]\\d|3[01])$").matcher(inputValidDate).matches())
                inputValid = true;
            else {
                System.out.println("Tanggal tidak valid, harap masukkan sesuai format");
                Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
            }
        }
        return inputValidDate;
    }

}
