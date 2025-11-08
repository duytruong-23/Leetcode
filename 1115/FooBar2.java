import java.util.concurrent.CountDownLatch;

class FooBar2 {
    private int n;
    private CountDownLatch fooLatch = new CountDownLatch(0);
    private CountDownLatch barLatch = new CountDownLatch(1);

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooLatch.await();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            fooLatch = new CountDownLatch(1);
            barLatch.countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barLatch.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            barLatch = new CountDownLatch(1);
            fooLatch.countDown();
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(3);

        Thread threadFoo = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadBar = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadFoo.start();
        threadBar.start();
    }
}