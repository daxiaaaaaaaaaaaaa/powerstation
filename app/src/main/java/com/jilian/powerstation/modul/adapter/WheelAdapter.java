package com.jilian.powerstation.modul.adapter;



import java.util.List;

public class WheelAdapter implements com.contrarywind.adapter.WheelAdapter {
    // items
    private List<String> items;

    /**
     * Constructor
     *
     * @param items the items
     */
    public WheelAdapter(List<String> items) {
        this.items = items;

    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return "";
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    @Override
    public int indexOf(Object o) {
        return items.indexOf(o);
    }

}
