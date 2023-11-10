import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Responsi1_PPBO_L0122136_Object_Task {
    private String NameOfTask;
    private LocalDate DateOut;
    private LocalDate DueDate;

    public Responsi1_PPBO_L0122136_Object_Task(String NameOfTask, String DateOut, String DueDate) {
        this.NameOfTask = NameOfTask;
        this.DateOut = LocalDate.parse(DateOut); // Konversi String ke LocalDate
        this.DueDate = LocalDate.parse(DueDate); // Konversi String ke LocalDate
    }

    public String getNameOfTask() {
        return NameOfTask;
    }

    public LocalDate getDateOut() {
        return DateOut;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    @Override
    public String toString() {
        return String.format("|%-16s|%10s|%10s|", NameOfTask, DateOut, DueDate);
    }

    public String toFileString() {
        return String.format("%s|del|%s|del|%s|del|", NameOfTask, DateOut, DueDate);
    }
}

class AssignmentTask extends Responsi1_PPBO_L0122136_Object_Task {
    public AssignmentTask(String nameOfTask, String dateOut, String dueDate) {
        super(nameOfTask, dateOut, dueDate);
    }

    public long getDaysLate() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(getDueDate())) {
            return ChronoUnit.DAYS.between(getDueDate(), currentDate);
        } else {
            return 0;
        }
    }

    public long getRemainingDays() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(getDueDate())) {
            return ChronoUnit.DAYS.between(currentDate, getDueDate());
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%-39s%-5s Days|%-5s Days|\n", super.toString(), getRemainingDays(), getDaysLate());
    }

    @Override
    public String toFileString() {
        return super.toFileString();
    }
}

class DoneTask extends Responsi1_PPBO_L0122136_Object_Task {
    private LocalDate dateDone;

    public DoneTask(String nameOfTask, String dateOut, String dueDate, String dateDone) {
        super(nameOfTask, dateOut, dueDate);
        this.dateDone = LocalDate.parse(dateDone);
        ;
    }

    public LocalDate getDateDone() {
        return dateDone;
    }

    public void setDateDone(LocalDate dateDone) {
        this.dateDone = dateDone;
    }

    @Override
    public String toString() {
        return String.format("%-39s%-10s|\n", super.toString(), getDateDone());
    }

    @Override
    public String toFileString() {
        return String.format("%s%s", super.toFileString(), dateDone);
    }
}

class DoneLateTask extends Responsi1_PPBO_L0122136_Object_Task {
    private LocalDate dateDone;

    public DoneLateTask(String nameOfTask, String dateOut, String dueDate, String dateDone) {
        super(nameOfTask, dateOut, dueDate);
        this.dateDone = LocalDate.parse(dateDone); // Konversi String ke LocalDate
    }

    public LocalDate getDateDone() {
        return dateDone;
    }

    public void setDateDone(LocalDate dateDone) {
        this.dateDone = dateDone;
    }

    public long getDaysLate() {
        if (dateDone.isAfter(getDueDate())) {
            return ChronoUnit.DAYS.between(getDueDate(), dateDone);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%-39s%-10s|%-5s Days|\n", super.toString(), getDateDone(), getDaysLate());
    }

    @Override
    public String toFileString() {
        return String.format("%s%s", super.toFileString(), dateDone);
    }
}
