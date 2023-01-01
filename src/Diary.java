import notes.Note;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diary {
    private Map<Integer, Note> map = new HashMap<>();

    public void addNote(Note note) {
        map.put(note.getId(), note);
    }

    public void removeNote(int id) {
        map.remove(id);
    }

    private List<Note> getNotesOnDay(LocalDate date) {
        List<Note> notes = new ArrayList<>();
        for (Note note : map.values()) {
            LocalDate localDate = note.getTime(date);
            if (localDate.isEqual(date)) {
                notes.add(note);
            }
        }

        return notes;
    }

    public void printNotesOnDay(LocalDate date) {
        List<Note> notes = getNotesOnDay(date);
        StringBuilder sb = new StringBuilder();
        for (Note note : notes) {
            sb.append(note.toString()).append("\n");
        }

        System.out.println(sb);
    }

    public void printDiary() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Note> entry : map.entrySet()) {
            sb.append("id: ").append(entry.getKey()).append(" , header: ").append(entry.getValue().getHeader()).append("\n");
        }

        System.out.println(sb);
    }
}
