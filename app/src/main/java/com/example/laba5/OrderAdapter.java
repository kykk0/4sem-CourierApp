package com.example.laba5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laba5.constans.CourierAbility;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    private ArrayList<Order> orders;
    private Courier courier = new Courier();
    private LayoutInflater layoutInflater;
    private boolean isSelectedAll = false;
    private boolean isCourierAvailable = false;

    public void setSelectedAll() {
        this.isSelectedAll = !isSelectedAll;
    }

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.orders = orders;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.orders.size();
    }

    @Override
    public Object getItem(int position) {
        return this.orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.order_item, parent, false);
        }

        TextView name_item = view.findViewById(R.id.name_item);
        TextView pack_item = view.findViewById(R.id.pack_item);
        TextView from_item = view.findViewById(R.id.from_item);
        TextView to_item = view.findViewById(R.id.to_item);
        TextView cost_item = view.findViewById(R.id.cost_item);
        CheckBox checkBox = view.findViewById(R.id.checkBox);

        Order order = getOrder(position);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            isCourierAvailable = false;
            Package pack = ((Order) getItem(position)).getPack();
            if (pack instanceof BigPackage && courier.hasAbility(CourierAbility.CAR_DELIVERY )) {
                isCourierAvailable = true;
            } else if (pack instanceof DocPackage && courier.hasAbility(CourierAbility.DOCUMENT_DELIVERY)) {
                isCourierAvailable = true;
            } else if (pack.isFragility() && courier.hasAbility(CourierAbility.FRAGILE_DELIVERY)) {
                isCourierAvailable = true;
            } else if (pack instanceof SmallPackage && !pack.fragility) {
                isCourierAvailable = true;
            }

            if (isCourierAvailable && isChecked) {
                order.setSelected(true);
            } else {
                checkBox.setChecked(false);
                order.setSelected(false);
            }
            notifyDataSetChanged();
        });


        name_item.setText(order.getCompany().getName());
        pack_item.setText(order.getPack().getType());
        from_item.setText(order.getAddrFrom());
        to_item.setText(order.getAddrTo());
        cost_item.setText(order.getCost());
        checkBox.setChecked(order.isSelected());

        boolean flag = false;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).isSelected()) flag = true;
        }
        if (!flag) checkBox.setChecked(false);

        return view;

    }

    private Order getOrder(int position) {
        return (Order) getItem(position);
    }

    public void updateRecords(ArrayList<Order> orders, Courier courier) {
        this.orders = orders;
        this.courier = courier;
        notifyDataSetChanged();
    }


    public ArrayList<Order> getOrders() {
        return orders;
    }


}