public class Product {
    String id;
    String name;
    double price;
    int quantity;
    String category;
    public Product(){};
    public Product(String id, String name, double price, int quantity, String category){
        this.id = id;
        this.name = name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
    };
    public String getId(){
        return id;
    };
    public void setId(String id) throws InvalidProductException {
        if(id.matches("^P\\d{3}$")){
            this.id=id;
        } else {
            throw new InvalidProductException("Sai định dạng id");
        }
    };
    public String getName(){
        return name;
    };
    public void setName(){
        this.name=name;
    };
    public double getPrice(){
        return price;
    };
    public void setPrice(double price){
        if (price>=0){
            this.price=price;
        } else {
            System.out.println("Không hợp lệ");
        }
    };
    public int setQuantity(){
        return quantity;
    };
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    };
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
