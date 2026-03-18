class DigitalProduct extends Product {
    double sizeMB;
    public DigitalProduct(String id, String name, double price, double sizeMB) {
        super(id, name, price);
        this.sizeMB = sizeMB;
    }
    public double getSizeMB() {
        return sizeMB;
    }
    public void setSizeMB(double sizeMB) {
        this.sizeMB = sizeMB;
    }
    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " | Tên: " + name + " | Giá: " + price + " | Dung lượng: " + sizeMB + " MB");
    }
}
