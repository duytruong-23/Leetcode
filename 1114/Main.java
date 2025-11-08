import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        Foo3 foo = new Foo3();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

    }
}