package notes;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Note implements Repeatable {
    private static int counter = 0;

    private final int id = counter++;

    private final String header;

    private final String description;

    private final NoteType noteType;

    protected LocalDateTime date;

    public Note(String header, String description, NoteType noteType, LocalDateTime date) {
        this.header = header;
        this.description = description;
        this.noteType = noteType;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && header.equals(note.header) && description.equals(note.description) && noteType == note.noteType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, description, noteType);
    }

    @Override
    public String toString() {
        return "Note{\n" +
                "id=" + id + "\n" +
                "header='" + header + '\'' + "\n" +
                "description='" + description + '\'' + "\n" +
                "noteType=" + noteType.getName() + "\n" +
                "time=" + date + "\n";
    }
}
