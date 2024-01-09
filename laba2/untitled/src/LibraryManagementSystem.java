import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Оберіть опцію:");
            System.out.println("1. Додати новий предмет в бібліотеку");
            System.out.println("2. Видалити предмет з бібліотеки");
            System.out.println("3. Зареєструвати читача");
            System.out.println("4. Видати предмет читачеві");
            System.out.println("5. Повернути предмет у бібліотеку");
            System.out.println("6. Показати список доступних предметів");
            System.out.println("7. Показати список взятих предметів та їхніх читачів");
            System.out.println("8. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Обробка переносу рядка

            switch (choice) {
                case 1:
                    // Додати новий предмет в бібліотеку
                    System.out.print("Введіть назву предмета: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть унікальний ID: ");
                    String uniqueID = scanner.nextLine();
                    System.out.print("Введіть тип (book або dvd): ");
                    String type = scanner.nextLine();

                    if (type.equalsIgnoreCase("book")) {
                        System.out.print("Введіть автора: ");
                        String author = scanner.nextLine();
                        library.add(new Book(title, uniqueID, author));
                    } else if (type.equalsIgnoreCase("dvd")) {
                        System.out.print("Введіть тривалість (у хвилинах): ");
                        int duration = scanner.nextInt();
                        library.add(new DVD(title, uniqueID, duration));
                    } else {
                        System.out.println("Непідтримуваний тип предмету.");
                    }
                    break;

                case 2:
                    // Видалити предмет з бібліотеки
                    System.out.print("Введіть унікальний ID предмету для видалення: ");
                    String idToDelete = scanner.nextLine();
                    Item itemToRemove = library.listAvailable()
                            .stream()
                            .filter(item -> item.getUniqueID().equals(idToDelete))
                            .findFirst()
                            .orElse(null);
                    if (itemToRemove != null) {
                        library.remove(itemToRemove);
                        System.out.println("Предмет видалено з бібліотеки.");
                    } else {
                        System.out.println("Предмет не знайдено.");
                    }
                    break;

                case 3:
                    // Зареєструвати читача
                    System.out.print("Введіть ім'я читача: ");
                    String patronName = scanner.nextLine();
                    System.out.print("Введіть унікальний ID читача: ");
                    String patronID = scanner.nextLine();
                    library.registerPatron(new Patron(patronName, patronID));
                    break;

                case 4:
                    // Видати предмет читачеві
                    System.out.print("Введіть унікальний ID читача: ");
                    String patronIDToBorrow = scanner.nextLine();
                    System.out.print("Введіть унікальний ID предмету для видачі: ");
                    String itemIDToBorrow = scanner.nextLine();

                    Patron borrowingPatron = library.getPatrons().stream()
                            .filter(patron -> patron.getID().equals(patronIDToBorrow))
                            .findFirst()
                            .orElse(null);

                    Item itemToBorrow = library.listAvailable()
                            .stream()
                            .filter(item -> item.getUniqueID().equals(itemIDToBorrow))
                            .findFirst()
                            .orElse(null);

                    if (borrowingPatron != null && itemToBorrow != null) {
                        library.lendItem(borrowingPatron, itemToBorrow);
                        System.out.println("Предмет видано читачу.");
                    } else {
                        System.out.println("Читача або предмет не знайдено.");
                    }
                    break;

                case 5:
                    // Повернути предмет у бібліотеку
                    System.out.print("Введіть унікальний ID читача: ");
                    String patronIDToReturn = scanner.nextLine();
                    System.out.print("Введіть унікальний ID предмету для повернення: ");
                    String itemIDToReturn = scanner.nextLine();

                    Patron returningPatron = library.patrons.stream()
                            .filter(patron -> patron.getID().equals(patronIDToReturn))
                            .findFirst()
                            .orElse(null);

                    Item itemToReturn = library.listBorrowed()
                            .stream()
                            .filter(item -> item.getUniqueID().equals(itemIDToReturn))
                            .findFirst()
                            .orElse(null);

                    if (returningPatron != null && itemToReturn != null) {
                        library.returnItem(returningPatron, itemToReturn);
                        System.out.println("Предмет повернено у бібліотеку.");
                    } else {
                        System.out.println("Читача або предмет не знайдено.");
                    }
                    break;

                case 6:
                    // Показати список доступних предметів
                    System.out.println("Доступні предмети в бібліотеці:");
                    library.listAvailable().forEach(item -> System.out.println(item.getTitle()));
                    break;

                case 7:
                    // Показати список взятих предметів та їхніх читачів
                    System.out.println("Взяті предмети та їхні читачі:");
                    library.listBorrowed().forEach(item -> {
                        Patron borrower = library.patrons.stream()
                                .filter(patron -> patron.getBorrowedItems().contains(item))
                                .findFirst()
                                .orElse(null);
                        if (borrower != null) {
                            System.out.println(item.getTitle() + " взято читачем " + borrower.getName());
                        }
                    });
                    break;

                case 8:
                    // Вихід з програми
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Неправильний вибір. Виберіть опцію зі списку.");
            }
        }
    }
}