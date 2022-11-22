import notes.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Diary diary = new Diary();
        try (scanner) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    scanner.nextLine();
                    switch (menu) {
                        case 1:
                            inputTask(diary);
                            break;
                        case 2:
                            deleteTask(diary);
                            break;
                        case 3:
                            getTaskOnDay(diary);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void getTaskOnDay(Diary diary) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;
        System.out.print("Введите дату, на которую вы хотите получить список задач(например, 22.11.2022): ");
        while (true) {
            try {
                date = LocalDate.parse(scanner.nextLine(), formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Введите дату в формате, который указан в примере!");
                continue;
            }

            break;
        }

        diary.printNotesOnDay(date);
    }

    private static void deleteTask(Diary diary) {
        System.out.println("Список задач: ");
        diary.printDiary();

        System.out.println("Введите id задачи, которую хотите удалить: ");
        while (true) {
            int id;
            try {
                id = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Введите id задачи!");
                continue;
            }
            finally {
                scanner.nextLine();
            }

            diary.removeNote(id);
            return;
        }
    }

    private static void inputTask(Diary diary) {
        System.out.print("Введите название задачи: ");
        String taskName = correctString("Название задачи должно быть непустым!");

        System.out.print("Введите описание задачи: ");
        String taskDescription = correctString("Описание задачи должно быть непустым!");

        System.out.print("Выберите тип задачи: \n" + "1. Личная\n" + "2. Рабочая\n" + "> ");
        NoteType noteType = getNoteType();

        System.out.print("Введите дату и время задачи(например, 22.11.2022 18:55): ");
        LocalDateTime dateTime = getNoteDate();

        System.out.print("Выберите тип повторяемости задачи: \n" +
                "1. Однократная\n" + "2. Ежедневная\n" + "3. Еженедельная\n" + "4. Ежемесячная\n" + "5. Ежегодная\n" + "> ");
        Note note = getTask(taskName, taskDescription, noteType, dateTime);

        diary.addNote(note);
    }

    private static Note getTask(String taskName, String taskDescription, NoteType noteType, LocalDateTime dateTime) {
        while (true) {
            int taskRepeatType;
            try {
                taskRepeatType = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Введите цифру от 1 до 5!");
                continue;
            }
            finally {
                scanner.nextLine();
            }

            switch (taskRepeatType) {
                case 1:
                    return new OnceNote(taskName, taskDescription, noteType, dateTime);
                case 2:
                    return new DailyNote(taskName, taskDescription, noteType, dateTime);
                case 3:
                    return new WeeklyNote(taskName, taskDescription, noteType, dateTime);
                case 4:
                    return new MonthlyNote(taskName, taskDescription, noteType, dateTime);
                case 5:
                    return new AnnuallyNote(taskName, taskDescription, noteType, dateTime);
                default:
                    System.out.println("Введите цифру от 1 до 5!");
            }
        }
    }

    private static LocalDateTime getNoteDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime;
        while (true) {
            try {
                String timeString = scanner.nextLine();
                dateTime = LocalDateTime.parse(timeString, formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Введите дату и время в формате, который указан в примере!");
                continue;
            }

            return dateTime;
        }
    }

    private static NoteType getNoteType() {
        while (true) {
            int taskNoteType;
            try {
                taskNoteType = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Введите цифру 1 или 2!");
                continue;
            }
            finally {
                scanner.nextLine();
            }

            switch (taskNoteType) {
                case 1:
                    return NoteType.PERSONAL;
                case 2:
                    return NoteType.WORKING;
                default:
                    System.out.println("Введите цифру 1 или 2!");
            }
        }
    }

    private static String correctString(String message) {
        String str = "";
        while (true) {
            str = scanner.nextLine();
            if (str.isBlank() || str.isEmpty()) {
                try {
                    throw new IOException(message);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                break;
            }
        }

        return str;
    }

    private static void printMenu() {
        System.out.println(
                        "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "0. Выход"
        );
    }
}