package pl.janota.home_work_m2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    private void addProduct(String name, int price) {
        if (!name.isEmpty()) {
            Product product = new Product(name, price);
            productList.add(product);
        }
    }

    public void addProducts() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Wprowadź nazwę nowego produktu lub idź do kasy (wpisz \"kasa\"):");
            userInput = scanner.nextLine();
            if (!userInput.equals("kasa")) {
                addProduct(userInput, getPrice());
                showBasket();
            }

        } while (!userInput.equals("kasa"));
    }

    public void showShopWelcome() {
        System.out.println("Dzień dobry! Wita Cię Twój sklep " + name);
    }

    public void showShopFarewell() {
        System.out.println("Do widzenia! Żegna Cię Twój sklep " + name);
    }

    public void showSummaryPrice() {
        System.out.println("Do zapłaty: " + getSummaryPrice() + " PLN");
    }

    public void showBasket() {
        System.out.println("Aktualnie posiadasz następujące produkty w koszyku:");
        productList.forEach(System.out::println);
        showSummaryPrice();
    }


}
