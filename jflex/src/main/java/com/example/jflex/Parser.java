package com.example.jflex;

import java.util.ArrayList;

import com.example.jflex.AnalizadorDeAritmetica;
import com.example.jflex.AnalizadorDeAritmetica.TokenInfo;

public class Parser {
    
    
    private ArrayList<TokenInfo> tokens;

    public Parser( ArrayList<AnalizadorDeAritmetica.TokenInfo> tokenlist) {
        this.tokens = tokenlist;
    }

    private void inicio(){
        AnalizadorDeAritmetica.TokenInfo aux = tokens.get(0);
    }

    private void create(){


    }

}
