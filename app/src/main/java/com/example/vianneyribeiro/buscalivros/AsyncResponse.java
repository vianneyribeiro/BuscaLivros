package com.example.vianneyribeiro.buscalivros;

import java.util.ArrayList;

/**
 * Created by vianneyribeiro on 16/01/2018.
 */

public interface AsyncResponse {

    void processFinish(ArrayList<String> title, ArrayList<String> author);
}
