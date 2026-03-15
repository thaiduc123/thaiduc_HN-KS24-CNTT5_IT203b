package b2;

public class TicketSupplier implements Runnable {

    private TicketPool pool;

    public TicketSupplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        pool.addTickets(3);
    }
}
