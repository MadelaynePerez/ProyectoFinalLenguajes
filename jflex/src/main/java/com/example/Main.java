package com.example;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

import com.example.jflex.AnalizadorDeAritmetica;
import com.example.jflex.AnalizadorSintactico;
import com.example.jflex.AnalizadorDeAritmetica.TokenInfo;

public class Main {
    public static void main(String[] args) {
        String input = " CREATE TABLE empleados (\r\n" + //
                        " id SERIAL PRIMARY KEY,\r\n" + //
                        " nombre VARCHAR(100) NOT NULL,\r\n" + //
                        " puesto VARCHAR(50),\r\n" + //
                        " salario DECIMAL(10, 2),\r\n" + //
                        " fecha_contratacion DATE,\r\n" + //
                        " departamento_id INTEGER,\r\n" + //
                        " email VARCHAR(100) UNIQUE,\r\n" + //
                        " CONSTRAINT fk_departamento\r\n" + //
                        " FOREIGN KEY (departamento_id)\r\n" + //
                        " REFERENCES departamentos(id)\r\n" + //
                        " );";
        TokenInfo tokens[];
        AnalizadorDeAritmetica lexer = new AnalizadorDeAritmetica(new StringReader(input));

        try {
    
            AnalizadorDeAritmetica.TokenInfo tokenInfo;
            Stack<AnalizadorDeAritmetica.TokenInfo> tokensStack = new Stack<>();

            while ((tokenInfo = lexer.yylex()) != null && tokenInfo.token != AnalizadorDeAritmetica.TipoToken.EOF) {
                tokensStack.push(tokenInfo);
                System.out.println("Valor: " + tokenInfo.value + " Línea: " + tokenInfo.line +
                                   " Columna: " + tokenInfo.column + " Tipo: " + tokenInfo.token);                        
            }
            tokens = new TokenInfo[tokensStack.size()];
            tokensStack.toArray(tokens);

            AnalizadorSintactico parser = new AnalizadorSintactico(tokens);
            parser.analizar();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

