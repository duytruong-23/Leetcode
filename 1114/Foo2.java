public class Foo2 {
    private boolean firstDone = false;
    private boolean secondDone = false;

    public Foo2() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstDone = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {

        while (!firstDone) {
            // Wait until first() is done
            // Use a loop to avoid spurious wakeups
            // Use wait() to avoid busy waiting
            wait();
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondDone = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!secondDone) {
            // Wait until second() is done
            // Use a loop to avoid spurious wakeups
            // Use wait() to avoid busy waiting
            wait();
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
