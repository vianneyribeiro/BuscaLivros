package com.example.vianneyribeiro.buscalivros;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    ListView listView;
    ArrayList<Book> books;
    BookAdapter adapter;
    BookAsyncTask bookAsynctask;
    TextView defaultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing all Variable and views.
        editText = findViewById(R.id.search_text);
        button = findViewById(R.id.search_button);
        listView = findViewById(R.id.list);
        books = new ArrayList<Book>();
        adapter = new BookAdapter(this, books);
        defaultTextView = findViewById(R.id.default_text_view);
        final Context context = MainActivity.this;

        //Set button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetworkUtil.isNetworkConnected(context)) {

                    String url = createStringUrl();
                    if (!url.equals("")) {
                        bookAsynctask = new BookAsyncTask(new AsyncResponse() {
                            @Override
                            public void processFinish(ArrayList<String> title, ArrayList<String> author) {
                                clearBookList();
                                if (title.size() != 0) {
                                    defaultTextView.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < title.size(); i++) {
                                        addBook(title.get(i), author.get(i));
                                    }
                                } else {
                                    listView.setVisibility(View.GONE);
                                    defaultTextView.setVisibility(View.VISIBLE);
                                }
                                listView.setAdapter(adapter);
                            }
                        });
                        bookAsynctask.execute(url);
                    }

                } else {

                    Toast.makeText(context, "Você está sem conexão com a internet", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    /**
     * Clears books ArrayList.
     */
    private void clearBookList() {
        books.clear();
    }

    /**
     * Add title and author to the books ArrayList.
     *
     * @param title
     * @param author
     */
    private void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    /**
     * Create String url.
     */
    private String createStringUrl() {
        String keyword = "";
        if (!editText.getText().toString().equals("")) {
            keyword = editText.getText().toString();
            Toast.makeText(MainActivity.this, "Buscando...", Toast.LENGTH_SHORT).show();
            return "https://www.googleapis.com/books/v1/volumes?" + "q=" + keyword;
        } else {
            Toast.makeText(MainActivity.this, "Informe um nome", Toast.LENGTH_SHORT).show();
        }
        return keyword;
    }

}
