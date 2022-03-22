package task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int EMPLOYEE_COUNT = 4;
    private static final long SLEEP_TIME = 1000L;
    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();
        Thread ats = new Thread(null, callCenter::addCallsToQueue, "ATS");
        ats.start();
        for (int i = 1; i <= EMPLOYEE_COUNT; i++) {
            threads.add(i - 1, new Thread(null, callCenter::processCalls, "Оператор" + i));
            threads.get(i - 1).start();
        }
        ats.join();
        while (callCenter.callQueue.size() > 0) {
            Thread.sleep(SLEEP_TIME);
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
