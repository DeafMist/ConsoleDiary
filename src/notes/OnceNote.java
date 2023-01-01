package notes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OnceNote extends Note {
    private static final String REPEAT_TYPE = "однократная";

    public OnceNote(String header, String description, NoteType noteType, LocalDateTime date) {
        super(header, description, noteType, date);
    }

    @Override
    public LocalDate getTime(LocalDate date) {
        return getDate().toLocalDate();
    }

    @Override
    public String toString() {
        return super.toString() +
                "repeatType=" + REPEAT_TYPE + "\n" +
                '}';
    }
}
