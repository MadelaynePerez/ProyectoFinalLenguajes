package com.example;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;
import java.awt.Color;
import com.example.jflex.AnalizadorDeAritmetica;

public class Main {
    public static void main(String[] args) {
        String input = "  CREATE TABLE empleados (\r\n" +
                       " id SERIAL PRIMARY KEY,\r\n" +
                       " nombre VARCHAR(100) NOT NULL,\r\n" +
                       " puesto VARCHAR(50),\r\n" +
                       " salario DECIMAL(10, 2),\r\n" +
                       " fecha_contratacion DATE,\r\n" +
                       " departamento_id INTEGER,\r\n" +
                       " email VARCHAR(100) UNIQUE,\r\n" +
                       " CONSTRAINT fk_departamento\r\n" +
                       " FOREIGN KEY (departamento_id)\r\n" +
                       " REFERENCES departamentos(id)\r\n" +
                       " );";

        AnalizadorDeAritmetica lexer = new AnalizadorDeAritmetica(new StringReader(input));
        Stack<AnalizadorDeAritmetica.TokenInfo> tokensStack = new Stack<>();
        Color.getHSBColor(128, 0, 255);

        try {
            AnalizadorDeAritmetica.TokenInfo tokenInfo;
            while ((tokenInfo = lexer.yylex()) != null && tokenInfo.token != AnalizadorDeAritmetica.TipoToken.EOF) {
                tokensStack.push(tokenInfo);  // Apilar cada token
                System.out.println("Valor: " + tokenInfo.value + " Línea: " + tokenInfo.line +
                                   " Columna: " + tokenInfo.column + " Tipo: " + tokenInfo.token);
            }

            // (Opcional) Imprimir los tokens desde la pila
            System.out.println("\nTokens desde la pila (en orden LIFO):");
            while (!tokensStack.isEmpty()) {
                AnalizadorDeAritmetica.TokenInfo token = tokensStack.pop();  // Desapilar cada token
                System.out.println("Token en pila - Valor: " + token.value + " Tipo: " + token.token);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

