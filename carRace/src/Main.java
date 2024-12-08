import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Гонка машинок");
        CarRace race = new CarRace();
        frame.add(race);
        frame.setSize(800, 600); // Установите размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        race.startRace(); // Запуск гонки
    }
}