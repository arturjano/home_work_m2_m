package pl.janota.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Profile("Start")
public class ShopStart{


    private Shop shop;

    @Autowired
    public ShopStart(Shop shop) {
        this.shop = shop;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        shop.showBasket();
        shop.showShopVersion();
        shop.showSummaryPrice();
    }

}
