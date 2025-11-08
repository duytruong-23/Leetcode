public class FooBar3 {
    private int n;
    private final Object lock = new Object();
    private boolean fooTurn = true;

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!fooTurn) {
                    lock.wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fooTurn = false;
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (fooTurn) {
                    lock.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooTurn = true;
                lock.notifyAll();
            }

        }
    }
}
