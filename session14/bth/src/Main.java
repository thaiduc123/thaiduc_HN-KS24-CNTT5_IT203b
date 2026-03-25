import service.OrderService;
public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        for (int i = 1; i <= 50; i++) {
            int userId = i;
            new Thread(() -> {
                service.placeOrder(userId, 1, 1);
            }).start();
        }
    }
}