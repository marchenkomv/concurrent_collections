package task1;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {

    private final long CALL_ADD_DELAY = 500L;
    private final long CALL_PROCESS_TIME = 3000L;
    private final int MAX_CALLS_FOR_ATS = 60;

    public ConcurrentLinkedQueue<Call> callQueue = new ConcurrentLinkedQueue<>();

    public void processCalls() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Call deletedCall = callQueue.poll();
                if (deletedCall != null) {
                    System.out.println(Thread.currentThread().getName() + " обработал звонок");
                }
                Thread.sleep(CALL_PROCESS_TIME);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " завершил работу");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void addCallsToQueue() {
        for (int i = 0; i < MAX_CALLS_FOR_ATS; i++) {
            try {
                callQueue.add(new Call());
                System.out.println("Добавлен новый звонок в очередь. Всего звонков в очереди: " + callQueue.size());
                Thread.sleep(CALL_ADD_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
