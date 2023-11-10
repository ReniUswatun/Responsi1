import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Logic {
    // merupakan kelas untuk mengatur logika didalam codingan ini
    public static void makeOrOpenFolder() throws IOException {
        File data = new File("My-Activity");
        if (data.exists()) {
            // fungsi untuk membaca semua isi file dan memasukkan kedalam array list
        } else {
            data.mkdirs();
            String[] NamaFile = { "My-Activity/assignment.txt", "My-Activity/finish_done.txt",
                    "My-Activity/finish_late.txt" };
            for (int i = 0; i < NamaFile.length; i++) {
                File file = new File(NamaFile[i]);
                file.createNewFile();
            }
        }
    }

    public static void readAllAgendaFolder(List<Object_Task> taskList, Scanner scanner) throws IOException, Exception {
        Path directory = Paths.get("My-Activity");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, "*.txt");
        for (Path filePath : directoryStream) {
            try {
                if (Files.size(filePath) > 0) {
                    List<String> lines = Files.readAllLines(filePath);
                    for (String line : lines) {
                        if (!line.trim().isEmpty()) { // Tambahkan pengecekan apakah baris kosong
                            String[] fieldTask = line.split("\\|del\\|");
                            Object_Task task = null;
                            switch (filePath.getFileName().toString()) {
                                case "assignment.txt":
                                    task = new AssignmentTask(fieldTask[0], fieldTask[1], fieldTask[2]);
                                    break;
                                case "finish_done.txt":
                                    task = new DoneTask(fieldTask[0], fieldTask[1], fieldTask[2], fieldTask[3]);
                                    break;
                                case "finish_late.txt":
                                    task = new DoneLateTask(fieldTask[0], fieldTask[1], fieldTask[2], fieldTask[3]);
                                    break;
                            }
                            if (task != null) {
                                taskList.add(task);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new Exception("Error processing file: " + filePath + ": " + e.getMessage());
            }
        }
    }

    public static void readAllTasksByType(List<Object_Task> taskList, Class<? extends Object_Task> taskType) {
        boolean taskfound = false;
        if (taskList != null && !taskList.isEmpty()) {
            int index = 0;
            for (Object_Task task : taskList) {
                if (taskType.isInstance(task)) {
                    index++;
                    System.out.printf("|  %-3s  %s", index, task.toString());
                    taskfound = true;
                }
            }
        }
        if (!taskfound) {
            String taskTypeName = taskType.getSimpleName();
            System.out.printf("%-43s\n", " Data " + taskTypeName + " Belum Ada", " ");
        }
    }

    public static void addAgendaToListAssignment(List<Object_Task> taskList, Path filePath,
            Scanner scanner) throws IOException {
        boolean succesMakeTask = false;
        while (!succesMakeTask) {
            System.out.println("Masukkan Nama Tugas tidak lebih dari 15 kata");
            String nameTask = Verify.checkNameTask(scanner);
            System.out.println("Masukkan tanggal tugas diberikan");
            String dateOutTask = Verify.checkDate(scanner);
            System.out.println("Masukkan tanggal tugas dikumpulkan");
            String dueDateTask = Verify.checkDate(scanner);
            AssignmentTask assignmentTask = new AssignmentTask(nameTask, dateOutTask, dueDateTask);
            taskList.add(assignmentTask);
            addTextToFile(nameTask, dateOutTask, dueDateTask, filePath, scanner);
            succesMakeTask = true;
        }
    }

    public static void addTextToFile(String nameTask, String dateOutTask, String dueDateTask, Path filePath,
            Scanner scanner) throws IOException {
        FileWriter writer = new FileWriter(filePath.toString(), true);
        writer.write(nameTask + "|del|" + dateOutTask + "|del|" + dueDateTask + "\n");
        writer.close();
        System.out.println("Task telah masuk ke data base");
    }

    public static void writeListToFile(List<Object_Task> taskList, Class<? extends Object_Task> taskType, Path filePath)
            throws IOException {
        // Iterate through the taskList and write each task to the file
        for (Object_Task task : taskList) {
            // Check if the task is an instance of the specified taskType
            if (taskType.isInstance(task)) {
                // Write the task to the file
                Files.write(filePath, task.toFileString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }

    public static void deleteAndUpdateAssignment(List<Object_Task> taskList, Scanner scanner) throws IOException {
        boolean trueDelAndUp = false;
        while (!trueDelAndUp) {
            displayAssignmentIndexes(taskList);
            System.out.println("Lihat List Diatas Masukkan Indeks Sesuai Indeks Diatas");
            System.out.println("Jika Tidak Ada Jangan Masukkan Enter\nMasukkan Angka Apa Saja");
            System.out.print("Index tugas yang sudah diselesaikan\n=> ");
            int indexDelete = Verify.checkInt(scanner);
            if (indexDelete >= 0 && indexDelete < taskList.size()) {
                Object_Task task = taskList.get(indexDelete);
                if (task instanceof AssignmentTask) {
                    taskList.remove(task);
                    if (!taskList.isEmpty()) {
                        // Pastikan taskList tidak kosong sebelum mengakses elemen
                        AssignmentTask assignmentTask = (AssignmentTask) task;
                        if ((assignmentTask.getRemainingDays() == 0 && assignmentTask.getDaysLate() == 0)
                                || assignmentTask.getDaysLate() == 0) {
                            DoneTask doneTask = new DoneTask(
                                    assignmentTask.getNameOfTask(),
                                    assignmentTask.getDateOut().toString(),
                                    assignmentTask.getDueDate().toString(),
                                    LocalDate.now().toString());
                            taskList.add(doneTask);
                            trueDelAndUp = true;
                        } else {
                            DoneLateTask doneLateTask = new DoneLateTask(assignmentTask.getNameOfTask(),
                                    assignmentTask.getDateOut().toString(),
                                    assignmentTask.getDueDate().toString(),
                                    LocalDate.now().toString());
                            taskList.add(doneLateTask);
                            trueDelAndUp = true;
                        }
                    } else {
                        System.out.println("Index ditemukan di tipe lain. Mencari index lain...");
                    }
                }
            } else {
                System.out.println("Data Assignment tidak ada Kembali Ke main Menu");
                Utility.enterToContinue(scanner);
                trueDelAndUp = true;
            }
        }
    }

    public static void displayAssignmentIndexes(List<Object_Task> taskList) {
        System.out.println("Daftar Index AssignmentTask dalam taskList:");
        for (int i = 0; i < taskList.size(); i++) {
            Object_Task task = taskList.get(i);
            if (task instanceof AssignmentTask) {
                System.out.println("Index: " + i + ", Task: " + task);
            }
        }
    }

}
