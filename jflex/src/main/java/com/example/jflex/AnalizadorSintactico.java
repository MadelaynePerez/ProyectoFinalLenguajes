package com.example.jflex;

import com.example.jflex.AnalizadorDeAritmetica.TipoToken;
import com.example.jflex.AnalizadorDeAritmetica.TokenInfo;
import java.util.Stack;

public class AnalizadorSintactico {
    private TokenInfo[] tokens;
    private int posicion;
    private Stack<String> pila;
    private boolean resultado;

    public AnalizadorSintactico(TokenInfo[] tokens) {
        this.tokens = tokens;
        this.posicion = 0;
        this.pila = new Stack<>();
        resultado = false;
    }

    public void analizar() {
        pila.push("S"); // Estado inicial

        while (!pila.isEmpty()) {
            String top = pila.pop();
            TokenInfo tokenActual = obtenerTokenActual();

            if (top.equals("S")) {
                
                // Reglas de producción para la sentencia inicial
                if (tokenActual.value.equals("CREATE")) {
                    
                    pila.push("crear_sentencia");
                } else if (tokenActual.value.equals("SELECT")) {
                    pila.push("select_sentencia");
                } else if (tokenActual.value.equals("INSERT")) {
                    pila.push("insertar_sentencia");
                }else if (tokenActual.value.equals("DELETE")) {
                    pila.push("eliminar_sentencia");
                } else {
                    
                    reportarError(tokenActual);
                }
            } else if (top.equals("crear_sentencia")) {
                posicion++;
                // Regla: CREATE TABLE identificador (columnas)
                if (matchToken("TABLE")) {
                    posicion++;
                    if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                        posicion++;
                        if (matchToken("(")) {
                            posicion++;
                            procesarColumnas(); // Lógica para procesar las columnas
                            if (!matchToken(")")) {
                                reportarError(tokenActual);
                            } else {
                                posicion++;
                            }
                        } else {
                            reportarError(tokenActual);
                        }
                    } else {
                        reportarError(tokenActual);
                    }
                } else {
                    reportarError(tokenActual);
                }
            } else if (top.equals("select_sentencia")) {
                // Regla: SELECT columnas FROM identificador
                if (matchToken("SELECT")) {
                    posicion++;
                    if (matchToken("*")) {
                        posicion++;
                    }else if (matchTokenEnum(TipoToken.IDENTIFICADOR)){
                        procesarColumnasSelect();
                    }else{
                        reportarError(tokenActual);
                    }
                     // Lógica para procesar columnas en SELECT
                    if (matchToken("FROM")) {
                        posicion++;
                        if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                            posicion++;
                        } else {
                            reportarError(tokenActual);
                        }
                    } else {
                        reportarError(tokenActual);
                    }
                } else {
                    reportarError(tokenActual);
                }
            } else if (top.equals("insertar_sentencia")) {
                // Regla: INSERT INTO identificador VALUES (valores)
                
                if (matchToken("INSERT")) {
                        
                    posicion++;
                    if (matchToken("INTO")) {
                      
                        posicion++;
                        
                        if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                            posicion++;
                            
                            if (matchToken("(")){
                                posicion++;
                                
                                procesarColumnasSelect();
                                if (matchToken(")")){
                                    
                                    posicion++;
                                    if (matchToken("VALUES")) {
                                        
                                        posicion++;
                                        if (matchToken("(")) {
                                            
                                            posicion++;
                                            procesarValores(); // Lógica para procesar los valores
                                            if (!matchToken(")")) {
                                                
                                                reportarError(tokenActual);
                                            } else {
                                                
                                                posicion++;
                                            }
                                        } else {
                                            reportarError(tokenActual);
                                        }
                                    } else {
                                        reportarError(tokenActual);
                                    }
                                }else {
                                     reportarError(tokenActual);
    
                                }
                            } else{
                                reportarError(tokenActual);
                            }
                        } else {
                            reportarError(tokenActual);
                        }
                    } else {
                        reportarError(tokenActual);
                    }
                }else{
                    reportarError(tokenActual);
                }
            } else if (top.equals("eliminar_sentencia")) {
                // Regla: DELETE FROM identificador [WHERE condicion]
                if (matchToken("DELETE")) {
                    posicion++;
                    if (matchToken("FROM")) {
                        posicion++;
                        if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                            posicion++;
                            // Opcional: WHERE condicion
                            if (matchToken("WHERE")) {
                                posicion++;
                                if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                                    posicion++;
                                    // Operador de comparación y valor después de WHERE
                                    if (matchTokenEnum(TipoToken.SIGNOS)) {
                                        posicion++;
                                        if (matchTokenEnum(TipoToken.ENTERO)) {
                                            posicion++;
                                        } else {
                                            reportarError(tokenActual);
                                        }
                                    } else {
                                        reportarError(tokenActual);
                                    }
                                } else {
                                    reportarError(tokenActual);
                                }
                            }
                            // Fin de la sentencia DELETE (sin WHERE o con WHERE completado)
                        } else {
                            reportarError(tokenActual);
                        }
                    } else {
                        reportarError(tokenActual);
                    }
                } else {
                    reportarError(tokenActual);
                }
            } else {
                // Coincide con un token terminal
                if (top.equals(tokenActual.value)) {
                    posicion++;
                } else {
                    reportarError(tokenActual);
                }
            }
            

        }
        if (resultado ){
            System.out.println("su codigo tienen errores sintacticos");
        }else {
           
            System.out.println("su codigo esta escrito correctamente");
        }
    }

    private void procesarColumnas() {
        while (true) {
            if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                posicion++; // Procesa el nombre de la columna
                if (matchTokenEnum(TipoToken.TIPODATO)) {
                    posicion++; // Procesa el tipo de dato
                } else {
                    reportarError(obtenerTokenActual());
                }
                if (matchToken(",")) {
                    posicion++; // Continua con la siguiente columna
                } else {
                    break; // Salimos si no hay más columnas
                }
            } else {
                break; // Salimos si no hay más identificadores
            }
        }
    }

    private void procesarColumnasSelect() {
        while (true) {
            if (matchTokenEnum(TipoToken.IDENTIFICADOR)) {
                posicion++; // Procesa el nombre de la columna
                if (matchToken(",")) {
                    posicion++; // Continua con la siguiente columna
                } else {
                    break; // Salimos si no hay más columnas
                }
            } else {
                break; // Salimos si no hay más identificadores
            }
        }
    }

    private void procesarValores() {
        while (true) {
            if (matchTokenEnum(TipoToken.CADENA) || matchTokenEnum(TipoToken.DECIMAL) || matchTokenEnum(TipoToken.ENTERO)) {
                posicion++; // Procesa el valor
                if (matchToken(",")) {
                    posicion++; // Continua con el siguiente valor
                } else {
                    break; // Salimos si no hay más valores
                }
            } else {
                break; // Salimos si no hay más valores
            }
        }
    }

    private TokenInfo obtenerTokenActual() {
        if (posicion < tokens.length) {
            return tokens[posicion];
        }
        return null; // o lanza una excepción si lo prefieres
    }

    private boolean matchToken(String tipo) {
        return posicion < tokens.length && tokens[posicion].value.equals(tipo);
    }

    private boolean matchTokenEnum(TipoToken tipo) {
        return posicion < tokens.length && tokens[posicion].token == tipo;
    }
    private void reportarError(TokenInfo token) {
        System.out.println("Error de análisis en " + token.value + " en la línea " + token.line);
        resultado=true;
        // Manejo de errores adicional
    }
}
