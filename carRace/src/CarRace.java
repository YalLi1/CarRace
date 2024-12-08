import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CarRace extends JPanel implements ActionListener {
    private BufferedImage[] cars = new BufferedImage[5];
    private int[] xPositions = new int[5]; // Позиции машинок
    private Timer timer;
    private boolean raceStarted = false;
    private int winnerCar = -1; // Победитель

    public CarRace() {
        // Загружаем изображения машинок
        for (int i = 0; i < cars.length; i++) {
            try {
                cars[i] = ImageIO.read(new File("C:\\Users\\New\\Downloads\\" + (i + 1) + ".jpg")); // Замените на путь к изображению вашей машинки
                xPositions[i] = 10; // Начальная позиция машинки
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        timer = new Timer(30, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем машинки
        for (int i = 0; i < cars.length; i++) {
            g.drawImage(cars[i], xPositions[i], 100 + i * 100, this); // Рисуем машинки по вертикали
        }

        // Если гонка состоялась и есть победитель, выводим его
        if (winnerCar != -1) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.RED);
            g.drawString("Победила машинка #" + (winnerCar + 1), getWidth() / 2 - 150, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (raceStarted) {
            for (int i = 0; i < cars.length; i++) {
                if (winnerCar == -1) { // Если нет победителя
                    int moveDistance = (int) (Math.random() * 5) + 1; // Случайное смещение от 1 до 5 пикселей
                    xPositions[i] += moveDistance;

                    // Проверяем, пересекла ли машинка финишную черту
                    if (xPositions[i] >= getWidth() - 50) { // Предполагая, что финишная черта на ширине панели
                        winnerCar = i; // Устанавливаем номер победителя
                        timer.stop(); // Останавливаем гонку
                    }
                }
            }
        }
        repaint(); // Перерисовываем панель
    }

    public void startRace() {
        Timer delayTimer = new Timer(3000, e -> {
            raceStarted = true; // Начинаем гонку после 3 секунд
            timer.start();
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }
}