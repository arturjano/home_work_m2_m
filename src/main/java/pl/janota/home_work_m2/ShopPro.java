package pl.janota.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Profile("Pro")
public class ShopPro {


    @Value("${tax-info.rate}")
    private double vatRate;

    @Value("${discount.rate}")
    private double discountRate;

    private Shop shop;

    @Autowired
    public ShopPro(Shop shop) {
        this.shop = shop;
    }

    private void showVat() {
        AtomicInteger summaryPrice = shop.getSummaryPrice();
        double vat = vatRate / 100 * summaryPrice.doubleValue() / (1 + vatRate / 100);
        BigDecimal vatFormatted = new BigDecimal(vat).setScale(2, RoundingMode.HALF_UP);
        System.out.println("W tym VAT (" + vatRate + "%): " + vatFormatted + " PLN");
    }

    public void showDiscount() {
        AtomicInteger summaryPrice = shop.getSummaryPrice();
        double discountPrice = summaryPrice.doubleValue() - discountRate / 100 * summaryPrice.doubleValue();
        System.out.println("Cena z uwzględnionym rabatem (" + discountRate + "%): " + discountPrice + " PLN");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        shop.showBasket();
        shop.showShopVersion();
        shop.showSummaryPrice();
        showDiscount();
        showVat();

    }

}