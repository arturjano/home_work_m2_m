package pl.janota.home_work_m2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Profile("Plus")
public class ShopPlus {


    @Value("${tax-info.rate}")
    private double vatRate;

    private final ShopService shopService;

    @Autowired
    public ShopPlus(ShopService shopService) {
        this.shopService = shopService;
    }

    private void showVat() {
        AtomicInteger summaryPrice = shopService.getSummaryPrice();
        double vat = vatRate / 100 * summaryPrice.doubleValue() / (1 + vatRate / 100);
        BigDecimal vatFormatted = new BigDecimal(vat).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Cena do zapłaty: " + summaryPrice + " PLN w tym VAT (" + vatRate + "%): " + vatFormatted + " PLN");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        shopService.showShopWelcome();
        shopService.showBasket();
        shopService.addProducts();
        showVat();
        shopService.showShopFarewell();
    }

}
