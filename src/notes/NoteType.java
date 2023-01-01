package notes;

public enum NoteType {
    PERSONAL("личная"),
    WORKING("рабочая");

    private final String name;

    NoteType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
