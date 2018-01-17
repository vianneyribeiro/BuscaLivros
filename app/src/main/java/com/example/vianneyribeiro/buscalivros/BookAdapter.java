package com.example.vianneyribeiro.buscalivros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vianneyribeiro on 16/01/2018.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.book_row, parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleView = (TextView) itemListView.findViewById(R.id.book_title);
        titleView.setText(currentBook.getTitle());

        TextView authorView = (TextView) itemListView.findViewById(R.id.book_author);
        authorView.setText(currentBook.getAuthor());

        return itemListView;
    }
}
