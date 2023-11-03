package pl.janota.homework2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Shop {

    @Value("${app-info.name}")
    private String name;
    private List<Product> productList;

    private AtomicInteger summaryPrice;

    public Shop() {

        int min = 50;
        int max = 300;
        int price1 = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int price2 = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int price3 = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int price4 = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int price5 = (int) Math.floor(Math.random() * (max - min + 1) + min);

        Product product1 = new Product("Jabłka", price1);
        Product product2 = new Product("Pomidory", price2);
        Product product3 = new Product("Śliwki", price3);
        Product product4 = new Product("Cytryny", price4);
        Product product5 = new Product("Pomarańcze", price5);

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);

        AtomicInteger summaryPrice = new AtomicInteger();
        productList.forEach(product -> {
            System.out.println(product);
            summaryPrice.addAndGet(product.getPrice());
        });
        this.summaryPrice = summaryPrice;

    }

    public AtomicInteger getSummaryPrice() {
        return summaryPrice;
    }

    public void showShopVersion() {
        System.out.println("Wersja sklepu: " + name);
    }

    public void showSummaryPrice() {
        System.out.println("Cena sumaryczna koszyka: " + summaryPrice);
    }

    public void showBasket() {
        productList.forEach(System.out::println);
    }

}
