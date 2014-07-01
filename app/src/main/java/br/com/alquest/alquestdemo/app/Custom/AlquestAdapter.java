package br.com.alquest.alquestdemo.app.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.alquest.alquestdemo.app.R;


public class AlquestAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<AlquestItemListView> itens;

    public AlquestAdapter(Context context, ArrayList<AlquestItemListView> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }

    public AlquestItemListView getItem(int position) {
        return itens.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public String getTitle(int position) {
        return getItem(position).gettitle();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AlquestItemListView item = itens.get(i);
        view = mInflater.inflate(R.layout.alquestlistitem, null);
        ((TextView) view.findViewById(R.id.title)).setText(item.gettitle());
        ((TextView) view.findViewById(R.id.desc)).setText(item.getContent());
        return view;
    }
}