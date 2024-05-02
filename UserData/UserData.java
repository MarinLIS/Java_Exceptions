package UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



class UserData {
    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private int phoneNumber;
    private char gender;

    public UserData(String input) {
        String[] data = input.split(" ");
        if (data.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных");
        }
        surname = data[0];
        name = data[1];
        patronymic = data[2];
        birthDate = data[3];
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(birthDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Неверный формат даты. формат должен быть дд.мм.гггг!");
        }
        try {
            phoneNumber = Integer.parseInt(data[4]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный номер телефона. Номер должен быть не более 9 символов!");
        }
        gender = data[5].charAt(0);
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверный пол. Необходимо указать f(женский) или m(мужской)!");
        }

    }

    public void processData() {
        try {
            File file = new File(surname + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " "
                    + gender + "\n");
            writer.close();
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}