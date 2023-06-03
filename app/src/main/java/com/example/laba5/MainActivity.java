package com.example.laba5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba5.constans.CourierAbility;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Courier courier;
    private ArrayList<Order> orders;
    private ArrayList<Order> displayedOrders;
    private ListView listView;
    private OrderAdapter adapter;

    private Button btnShowAll;
    private Button btnShowAvailable;
    private Button btn_clear;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courier = new Courier("Хасбек", "12345678901");

        courier.addAbility(CourierAbility.DOCUMENT_DELIVERY);

        TextView courier_name = findViewById(R.id.courier_name);
        courier_name.setText(courier.getFIO());

        Set<CourierAbility> courierAbilities = courier.getAbilities();
        StringBuilder abilitiesText = new StringBuilder();
        for (CourierAbility ability : courierAbilities) {
            String abilityName = convertAbilityToRussian(ability);
            abilitiesText.append(abilityName).append("\n");
        }
        TextView abilities = findViewById(R.id.couruier_features);
        abilities.setText(abilitiesText);

        SmallPackage clock = new SmallPackage("20*30*40", true, "Красноярск", "Сочи");
        DocPackage document = new DocPackage("Волгоград", "Самара");
        BigPackage alisa = new BigPackage("120*80*90", false, 15, "Ижевск", "Москва");
        BigPackage chair = new BigPackage("120*120*120", false, 8, "Новокузнецк", "Хабаровск");
        SmallPackage tshirt = new SmallPackage("20*20*5", true, "Санкт-Петербург", "Москва");
        SmallPackage book = new SmallPackage("25*15*10", true, "Владивосток", "Казань");

        SmallPackage keys = new SmallPackage("15*15*40", false, "Санкт-Петербург", "Томск");
        SmallPackage flashDrive = new SmallPackage("3*2*1", false, "Красноярск", "Сочи");

        Company yandex = new Company("Yandex", "Москва, ул. Льва Толстого, 16");
        Company google = new Company("Google", "Калифорния");
        Company ozon = new Company("Ozon", "Москва, Пресненская Набережная 10");
        Company dhl = new Company("Dhl", "Москва, Лесная улица, 7, А");
        Company amazon = new Company("Amazon", "Seattle, WA, USA");
        Company apple = new Company("Apple", "Cupertino, CA, USA");
        Company ebay = new Company("Ebay", "San Jose, CA, USA");

        Order order2 = new Order(ozon, clock, clock.getSourceAddress(), clock.getDestinationAddress(), 1500);
        Order order3 = new Order(google, document, document.getSourceAddress(), document.getDestinationAddress(), 1600);
        Order order4 = new Order(yandex, alisa, alisa.getSourceAddress(), alisa.getDestinationAddress(), 3500);
        Order order5 = new Order(dhl, chair, chair.getSourceAddress(), chair.getDestinationAddress(), 5000);
        Order order6 = new Order(amazon, tshirt, tshirt.getSourceAddress(), tshirt.getDestinationAddress(), 800);
        Order order7 = new Order(apple, book, book.getSourceAddress(), book.getDestinationAddress(), 1800);

        Order order1 = new Order(google, keys, keys.getSourceAddress(), keys.getDestinationAddress(), 1200);
        Order order8 = new Order(ebay, flashDrive, flashDrive.getSourceAddress(), flashDrive.getDestinationAddress(), 300);

        orders = new ArrayList<>();
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);

        orders.add(order1);
        orders.add(order8);

        listView = findViewById(R.id.listView);
        adapter = new OrderAdapter(this, orders);
        listView.setAdapter(adapter);

        btnShowAll = findViewById(R.id.button_show_all);
        btnShowAvailable = findViewById(R.id.button_show_available);
        btn_ok = findViewById(R.id.button_ok);
        btn_clear = findViewById(R.id.button_clear);

        btnShowAll.setOnClickListener(v -> displayAllOrders());

        btnShowAvailable.setOnClickListener(v -> displayAvailableOrders());

        btn_ok.setOnClickListener(v -> {
            double result = 0;
            for (int i = 0; i < adapter.getOrders().size(); i++) {
                if (adapter.getOrders().get(i).isSelected()) {
                    result += Double.parseDouble(adapter.getOrders().get(i).getCost());
                }
            }
            showInfo(result);
        });

        btn_clear.setOnClickListener(v -> {

            for (int i = 0; i < adapter.getOrders().size(); i++) {
                adapter.getOrders().get(i).setSelected(false);
            }
            adapter.notifyDataSetChanged();

        });

        displayAvailableOrders();
    }

    private String convertAbilityToRussian(CourierAbility ability) {
        switch (ability) {
            case CAR_DELIVERY:
                return "Доставка на машине";
            case DOCUMENT_DELIVERY:
                return "Доставка документов";
            case FRAGILE_DELIVERY:
                return "Доставка хрупких товаров";
            default:
                return "Нет";
        }
    }

    private void displayAllOrders() {
        displayedOrders = orders;
        adapter.updateRecords(displayedOrders, courier);
    }

    private void displayAvailableOrders() {
        displayedOrders = new ArrayList<>();
        for (Order order : orders) {
            Package pack = order.getPack();
            if (pack instanceof BigPackage && courier.hasAbility(CourierAbility.CAR_DELIVERY)) {
                displayedOrders.add(order);
            } else if (pack instanceof DocPackage && courier.hasAbility(CourierAbility.DOCUMENT_DELIVERY)) {
                displayedOrders.add(order);
            } else if (pack.isFragility() && courier.hasAbility(CourierAbility.FRAGILE_DELIVERY)) {
                displayedOrders.add(order);
            } else if (pack instanceof SmallPackage && !pack.fragility) {
                displayedOrders.add(order);
            }
        }
        adapter.updateRecords(displayedOrders, courier);

        if (displayedOrders.isEmpty()) {
            Toast.makeText(this, "Для вас нет подходящих заказов", Toast.LENGTH_SHORT).show();
        }
    }

    private void showInfo(double cost) {
        Toast.makeText(this, "Общая стоимость: " + cost, Toast.LENGTH_SHORT).show();
    }
}