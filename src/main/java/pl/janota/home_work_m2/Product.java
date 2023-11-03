package pl.janota.home_work_m2;

public class Product {

    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "Produkt: " + name + '\n' +
                "Cena: " + price + " PLN" ;
    }
}
