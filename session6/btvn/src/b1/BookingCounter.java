package b1;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool firstPool;
    private TicketPool secondPool;

    public BookingCounter(String name, TicketPool firstPool, TicketPool secondPool) {
        this.name = name;
        this.firstPool = firstPool;
        this.secondPool = secondPool;
    }

    public void sellCombo() {

        synchronized (firstPool) {

            if (!firstPool.hasTicket()) {
                System.out.println(name + ": Hết vé phòng " + firstPool.getRoomName());
                return;
            }

            String ticket1 = firstPool.getTicket();
            System.out.println(name + ": Đã lấy vé " + ticket1);

            try {
                Thread.sleep(100); // tạo cơ hội deadlock
            } catch (InterruptedException e) {}

            synchronized (secondPool) {

                if (!secondPool.hasTicket()) {
                    System.out.println(name + ": Hết vé phòng " + secondPool.getRoomName());
                    firstPool.returnTicket(ticket1);
                    return;
                }

                String ticket2 = secondPool.getTicket();

                System.out.println(name + " bán combo thành công: "
                        + ticket1 + " & " + ticket2);
            }
        }
    }
    public void sellComboSafe(TicketPool roomA, TicketPool roomB) {

        TicketPool first = roomA;
        TicketPool second = roomB;

        if (roomA.getRoomName().compareTo(roomB.getRoomName()) > 0) {
            first = roomB;
            second = roomA;
        }

        synchronized (first) {
            synchronized (second) {

                if (!roomA.hasTicket() || !roomB.hasTicket()) {
                    System.out.println(name + ": Không đủ vé combo");
                    return;
                }

                String ticketA = roomA.getTicket();
                String ticketB = roomB.getTicket();

                System.out.println(name + " bán combo thành công: "
                        + ticketA + " & " + ticketB);
            }
        }
    }
    @Override
    public void run() {
        while (true) {
//            sellCombo();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}
        }
    }
}