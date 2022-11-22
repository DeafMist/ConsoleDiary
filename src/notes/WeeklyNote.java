package notes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyNote extends Note {
    private static final String REPEAT_TYPE = "еженедельная";

    public WeeklyNote(String header, String description, NoteType noteType, LocalDateTime date) {
        super(header, description, noteType, date);
    }

    @Override
    public LocalDate getTime(LocalDate date) {
        LocalDate localDate = getDate().toLocalDate();
        while (localDate.isBefore(date)) {
            localDate = localDate.plusWeeks(1);
        }

        return localDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatType=" + REPEAT_TYPE + "\n" +
                '}';
    }
}
