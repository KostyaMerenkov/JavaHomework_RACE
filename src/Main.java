import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final int CARS_COUNT = 4;
    public static final int WINNERS = 1;
    public static final CountDownLatch ready_cdl = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch finish_cdl = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch win_cdl = new CountDownLatch(WINNERS);
    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }


        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        ready_cdl.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finish_cdl.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
