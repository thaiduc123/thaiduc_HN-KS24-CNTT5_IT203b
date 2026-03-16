import javax.management.Notification;
import javax.xml.crypto.Data;

public class OrderProcessor {
    private final Notification notification;
    private Database db;
    private EmailSender emailSender;
    private PaymentMethod paymentMethod;
    public OrderProcessor (Database db, EmailSender emailSender, Notification notification) {
        this.db=db;
        this.paymentMethod=paymentMethod;
        this.notification=notification;
    }
    public void processOrder(Order order){
        db.save(order);
        // 2. Xử lý thanh toán

        // 3. Gửi thông báo
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail(order.getCustomerEmail(), "Đơn hàng của bạn đã được xử lý thành công!");
    }
}

