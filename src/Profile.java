import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Profile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные (фамилия имя отчество дата_рождения номер_телефона(без +) пол):");

        String input = scanner.nextLine();

        String[] data = input.split(" ");

        if (data.length != 6) {
            System.err.println("Ошибка: Вы ввели неверное количество данных.");
            return;
        }

        try {
            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            char gender = data[5].charAt(0);

            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Ошибка: Неверный формат даты рождения.");
            }
            String[] dateParts = birthDate.split("\\.");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            if (day > 31) {
                throw new IllegalArgumentException("Ошибка: Такого дня не существует!");
            }
            if (month > 12){
                throw new IllegalArgumentException("Ошибка: Такого месяца не существует!");
            }
            if (year > 2024){
                throw new IllegalArgumentException("Ошибка: Такой год еще не наступил!");
            }
            if (phoneNumber.length() != 11){
                throw new IllegalArgumentException("Ошибка: Номер телефона должен иметь 11 символов!");
            }

            if (gender != 'ж' && gender != 'м') {
                throw new IllegalArgumentException("Ошибка: Неверный формат пола.");
            }

            String fileName = surname + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write("ФИО: " + surname + " " + name + " " + patronymic + "; " + "Дата Рождения: " + birthDate + "; " + "Номер Телефона: +" + phoneNumber + "; " + "Пол: " + gender + "\n");
            writer.close();
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат номера телефона.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
