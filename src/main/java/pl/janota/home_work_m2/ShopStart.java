package pl.janota.home_work_m2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("Start")
public class ShopStart {

    private final ShopService shopService;

    @Autowired
    public ShopStart(ShopService shopService) {
        this.shopService = shopService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        shopService.showShopWelcome();
        shopService.showBasket();
        shopService.addProducts();
        shopService.showSummaryPrice();
        shopService.showShopFarewell();
    }

}
