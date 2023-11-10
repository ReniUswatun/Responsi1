import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // ini merupakan class yang menjadi point dalam setiap class yang ada disini
    public static final int MAX = 70;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Object_Task> taskList = new ArrayList<>();
        try {
            Logic.makeOrOpenFolder();
            Logic.readAllAgendaFolder(taskList, scanner);
            runProgram(scanner, taskList);
        } catch (IOException e) {
            handleException(e, scanner);
        } catch (Exception e) {
            handleException(e, scanner);
        }
    }

    private static void runProgram(Scanner scanner, List<Object_Task> taskList) throws Exception {
        while (true) {
            try {
                UI.topUIAssigment();
                Logic.readAllTasksByType(taskList, AssignmentTask.class);
                Utility.printLine(Main.MAX, "=");
                Utility.printMenu();
                Path filePath1 = Paths.get("My-Activity" + File.separator + "assignment.txt");
                Path filePath2 = Paths.get("My-Activity" + File.separator + "finish_done.txt");
                Path filePath3 = Paths.get("My-Activity" + File.separator + "finish_late.txt");
                int choice = Verify.checkInt(scanner);
                switch (choice) {
                    case 1:
                        // add dan update di assigment

                        Logic.addAgendaToListAssignment(taskList, filePath1, scanner);
                        break;
                    case 2:
                        // delete dan update di assigment and if else donetask or late
                        Logic.deleteAndUpdateAssignment(taskList, scanner);
                        Logic.writeListToFile(taskList, AssignmentTask.class, filePath1);
                        Logic.writeListToFile(taskList, DoneTask.class, filePath2);
                        Logic.writeListToFile(taskList, DoneLateTask.class, filePath3);
                        break;
                    case 3:
                        // lihat (done dan done late)
                        // lihat ist done dan late
                        System.out.printf("1. Lihat Tugas Selesai Tepat Waktu\n2. Lihat Tugas Selesai Terlambat\n=> ");
                        int choiceCase4 = Verify.checkInt(scanner);
                        if (choiceCase4 == 1) {
                            UI.topUIDone();
                            Logic.readAllTasksByType(taskList, DoneTask.class);
                            Utility.printLine((Main.MAX - 11), "=");
                            Utility.enterToContinue(scanner);
                        } else if (choiceCase4 == 2) {
                            UI.topUILate();
                            Logic.readAllTasksByType(taskList, DoneLateTask.class);
                            Utility.printLine(Main.MAX, "=");
                            Utility.enterToContinue(scanner);
                        } else {
                            System.out.println("Masukkan pilihan yang benar");
                            Utility.enterToContinue(scanner);
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
            } catch (Exception e) {
                throw new Exception();
            }
        }
    }

    private static void handleException(Exception e, Scanner scanner) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        Utility.enterToContinue(scanner);
    }
}
