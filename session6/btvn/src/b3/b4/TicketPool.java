package b3.b4;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets;
    private int ticketCounter;

    public TicketPool(String roomName, int totalTickets) {

        this.roomName = roomName;
        tickets = new ArrayList<>();

        for (int i = 1; i <= totalTickets; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }

        ticketCounter = totalTickets + 1;
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }

        return null;
    }

    public synchronized void addTickets(int count) {

        for (int i = 0; i < count; i++) {
            String id = roomName + "-" + String.format("%03d", ticketCounter++);
            tickets.add(new Ticket(id, roomName));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public synchronized int remainingTickets() {

        int count = 0;

        for (Ticket t : tickets) {
            if (!t.isSold()) {
                count++;
            }
        }

        return count;
    }
}