public class TimerThread extends Thread {

    // Timer thread sleeps for 10 seconds (10,000 milliseconds) then finishes.
    @Override
    public void run() {
        System.out.println("Timer started!");
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {}
    }

}
