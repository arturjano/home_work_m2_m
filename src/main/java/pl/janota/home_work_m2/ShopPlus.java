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
@Profile("Plus")
public class ShopPlus {


    @Value("${tax-info.rate}")
    private double vatRate;

    private Shop shop;

    @Autowired
    public ShopPlus(Shop shop) {
        this.shop = shop;
    }

    private void showVat() {
        AtomicInteger summaryPrice = shop.getSummaryPrice();
        double vat = vatRate/100*summaryPrice.doubleValue()/(1+vatRate/100);
        BigDecimal vatFormatted = new BigDecimal(vat).setScale(2, RoundingMode.HALF_UP);
        System.out.println("W tym VAT (" + vatRate +"%): " + vatFormatted + " PLN");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        shop.showBasket();
        shop.showShopVersion();
        shop.showSummaryPrice();
        showVat();
    }

}
