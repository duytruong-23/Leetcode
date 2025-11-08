import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        Runnable printFoo = () -> System.out.print("foo");
        Runnable printBar = () -> System.out.print("bar");

        FooBar fooBar = new FooBar(3);

        CompletableFuture<Void> fooFuture = CompletableFuture.runAsync(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture<Void> barFuture = CompletableFuture.runAsync(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture.allOf(fooFuture, barFuture)
                .thenRun(() -> System.out.println("\nEnd of FooBar execution"))
                .join();

        FooBar2 fooBar2 = new FooBar2(3);

        CompletableFuture<Void> fooFuture2 = CompletableFuture.runAsync(() -> {
            try {
                fooBar2.foo(printFoo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture<Void> barFuture2 = CompletableFuture.runAsync(() -> {
            try {
                fooBar2.bar(printBar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture.allOf(fooFuture2, barFuture2)
                .thenRun(() -> System.out.println("\nEnd of FooBar2 execution")).join();

        FooBar3 fooBar3 = new FooBar3(3);
        CompletableFuture<Void> fooFuture3 = CompletableFuture.runAsync(() -> {
            try {
                fooBar3.foo(printFoo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture<Void> barFuture3 = CompletableFuture.runAsync(() -> {
            try {
                fooBar3.bar(printBar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CompletableFuture.allOf(fooFuture3, barFuture3)
                .thenRun(() -> System.out.println("\nEnd of FooBar3 execution")).join();
    }

}
