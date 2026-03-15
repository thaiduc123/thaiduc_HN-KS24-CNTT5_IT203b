package b3.b4;
import java.util.Random;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;

    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }

    @Override
    public void run() {

        while (true) {

            Ticket ticket;

            if (random.nextBoolean()) {
                ticket = roomA.sellTicket();
                if (ticket == null) {
                    ticket = roomB.sellTicket();
                }
            } else {
                ticket = roomB.sellTicket();
                if (ticket == null) {
                    ticket = roomA.sellTicket();
                }
            }

            if (ticket != null) {
                soldCount++;

                System.out.println(counterName +
                        " đã bán vé " + ticket.getTicketId());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}