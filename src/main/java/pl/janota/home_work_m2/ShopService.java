package pl.janota.home_work_m2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ShopService {

    @Value("${app-info.name}")
    private String name;
    private final List<Product> productList;

    int max = 300;

    int min = 50;

    public ShopService() {

        productList = new ArrayList<>();

        addProduct("Jabłka", getPrice());
        addProduct("Pomidory", getPrice());
        addProduct("Śliwki", getPrice());
        addProduct("Cytryny", getPrice());
        addProduct("Pomarańcze", getPrice());

    }

    public AtomicInteger getSummaryPrice() {
        AtomicInteger summaryPrice = new AtomicInteger();
        productList.forEach(product -> summaryPrice.addAndGet(product.getPrice()));
        return summaryPrice;
    }

    public int getPrice() {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void addProduct(String name, int price) {
        Product product = new Product(name, price);
        productList.add(product);
    }

    public void showShopVersion() {
        System.out.println("Wersja sklepu: " + name);
    }

    public void showSummaryPrice() {
        System.out.println("Cena sumaryczna koszyka: " + getSummaryPrice());
    }

    public void showBasket() {
        productList.forEach(System.out::println);
    }

}
