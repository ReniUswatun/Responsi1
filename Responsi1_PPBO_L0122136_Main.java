import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Responsi1_PPBO_L0122136_Main {
    // ini merupakan class yang menjadi point dalam setiap class yang ada disini
    public static final int MAX = 70;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Responsi1_PPBO_L0122136_Object_Task> taskList = new ArrayList<>();
        try {
            Responsi1_PPBO_L0122136_Logic.makeOrOpenFolder();
            Responsi1_PPBO_L0122136_Logic.readAllAgendaFolder(taskList, scanner);
            runProgram(scanner, taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
        }
    }

    private static void runProgram(Scanner scanner, List<Responsi1_PPBO_L0122136_Object_Task> taskList)
            throws Exception, AssertionError {
        while (true) {
            Responsi1_PPBO_L0122136_UI.topUIAssigment();
            Responsi1_PPBO_L0122136_Logic.readAllTasksByType(taskList, AssignmentTask.class);
            Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
            Responsi1_PPBO_L0122136_Utility.printMenu();
            Path filePath1 = Paths.get("My-Activity" + File.separator + "assignment.txt");
            Path filePath2 = Paths.get("My-Activity" + File.separator + "finish_done.txt");
            Path filePath3 = Paths.get("My-Activity" + File.separator + "finish_late.txt");
            int choice = Responsi1_PPBO_L0122136_Verify.checkInt(scanner);
            assert choice <= 4 && choice >= 1 : "Input Not Valid";
            switch (choice) {
                case 1:
                    // add dan update di assigment
                    Responsi1_PPBO_L0122136_Logic.addAgendaToListAssignment(taskList, filePath1, scanner);
                    break;
                case 2:
                    // delete dan update di assigment and if else donetask or late
                    Responsi1_PPBO_L0122136_Logic.deleteAndUpdateAssignment(taskList, scanner);
                    Responsi1_PPBO_L0122136_Logic.writeListToFile(taskList, AssignmentTask.class, filePath1);
                    Responsi1_PPBO_L0122136_Logic.writeListToFile(taskList, DoneTask.class, filePath2);
                    Responsi1_PPBO_L0122136_Logic.writeListToFile(taskList, DoneLateTask.class, filePath3);
                    break;
                case 3:
                    // lihat (done dan done late)
                    // lihat ist done dan late
                    System.out.printf("1. Lihat Tugas Selesai Tepat Waktu\n2. Lihat Tugas Selesai Terlambat\n=> ");
                    int choiceCase4 = Responsi1_PPBO_L0122136_Verify.checkInt(scanner);
                    assert choiceCase4 <= 1 && choiceCase4 >= 2 : "Input Not Valid";
                    if (choiceCase4 == 1) {
                        Responsi1_PPBO_L0122136_UI.topUIDone();
                        Responsi1_PPBO_L0122136_Logic.readAllTasksByType(taskList, DoneTask.class);
                        Responsi1_PPBO_L0122136_Utility.printLine((Responsi1_PPBO_L0122136_Main.MAX - 11), "=");
                        Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
                    } else if (choiceCase4 == 2) {
                        Responsi1_PPBO_L0122136_UI.topUILate();
                        Responsi1_PPBO_L0122136_Logic.readAllTasksByType(taskList, DoneLateTask.class);
                        Responsi1_PPBO_L0122136_Utility.printLine(Responsi1_PPBO_L0122136_Main.MAX, "=");
                        Responsi1_PPBO_L0122136_Utility.enterToContinue(scanner);
                    }
                    break;
                case 4:
                    System.out.println("Keluar dari program dan update data base");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan yang dimasukkan salah");
                    break;
            }
        }
    }
}
