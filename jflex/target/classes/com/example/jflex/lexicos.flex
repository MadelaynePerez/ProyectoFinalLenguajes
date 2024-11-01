    %%

    %public
    %class AnalizadorDeAritmetica 
    %type TokenInfo

    %{
        public class TokenInfo {
            public TipoToken token;
            public String value;
            public int line;
            public int column;
            public Color Color;

            public TokenInfo(TipoToken token, String value, int line, int column, Color Color) {
                this.token = token;
                this.value = value;
                this.line = line;
                this.column = column;
                this.Color=Color;
            }

            public TokenInfo(TipoToken token, String value, int line, int column) {
                this.token = token;
                this.value = value;
                this.line = line;
                this.column = column;
                
            }
        }

        public enum TipoToken {
            IDENTIFICADOR, 
            ARITMETICOS,
            TIPODATO,
            DECIMAL, 
            CREATE, 
            ENTERO,
            FECHA, 
            CADENA,
            BOOLEANO,
            FUNCIONAGREGACION,
            SIGNOS,
            RELACIONALES,
            LOGICOS,
            COMENTARIOS,
            ERROR,
            EOF //FINAL 
        }
    %}

    %line
    %column

    Digit = [0-9]
    Identificador = [a-z] 
    WhiteSpace = [ \t\n\r]
    Palabras = Function|If

    %%
    "--"[^\n]*  { return new TokenInfo(TipoToken.COMENTARIOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }

    "CREATE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "DATABASE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "TABLE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1 , Color.orange);}
    "KEY" { return new TokenInfo(TipoToken.CREATE, yytext() , yyline + 1, yycolumn + 1, Color.orange);}
    "PRIMARY" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "UNIQUE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "FOREIGN" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "REFERENCES" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn+ 1, Color.orange);}
    "ALTER" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "ADD" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "COLUMN" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "TYPE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "DROP" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "CONSTRAINT" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "IF" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "CASCADE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "ON" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "DELETE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "SET" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "UPDATE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "INSERT" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "INTO" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "VALUES" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "SELECT" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "FROM" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "WHERE" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);} 
    "AS" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "GROUP" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "ORDER" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "BY" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "ASC" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "DESC" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "LIMIT" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}
    "JOIN" { return new TokenInfo(TipoToken.CREATE, yytext(), yyline + 1, yycolumn + 1, Color.orange);}

    "INTEGER" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "BIGINT" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "VARCHAR" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "DECIMAL" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "DATE" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "TEXT" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "BOOLEAN" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }
    "SERIAL" { return new TokenInfo(TipoToken.TIPODATO, yytext(), yyline + 1, yycolumn + 1, Color.getHSBColor(128, 0, 255)); }


    {Digit}+ { return new TokenInfo(TipoToken.ENTERO, yytext(), yyline + 1, yycolumn + 1, Color.blue);}
    {Digit}+.{Digit}+ { return new TokenInfo(TipoToken.DECIMAL, yytext(), yyline + 1, yycolumn + 1, Color.blue); }
    "'" ( [^'\\\n]* | "''" )* "'"  { return new TokenInfo(TipoToken.CADENA, yytext(), yyline + 1, yycolumn + 1 , Color.green); }
    {Digit}{Digit}{Digit}{Digit}"-"{Digit}{Digit}"-"{Digit}{Digit}  { return new TokenInfo(TipoToken.FECHA, yytext(), yyline + 1, yycolumn + 1, Color.yellow);}

    {Identificador}({Identificador}|{Digit}|_)* { return new TokenInfo(TipoToken.IDENTIFICADOR, yytext(), yyline + 1, yycolumn + 1, Color.magenta); }

    "True"|"False" { return new TokenInfo(TipoToken.BOOLEANO , yytext(), yyline + 1, yycolumn + 1, Color.blue); }

    "SUM"|"AVG"|"COUNT"|"MAX"|"MIN" { return new TokenInfo(TipoToken.FUNCIONAGREGACION , yytext(), yyline + 1, yycolumn, Color.blue); }

    "+" { return new TokenInfo(TipoToken.ARITMETICOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "-" { return new TokenInfo(TipoToken.ARITMETICOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "*" { return new TokenInfo(TipoToken.ARITMETICOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "/" { return new TokenInfo(TipoToken.ARITMETICOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }

    "<" { return new TokenInfo(TipoToken.RELACIONALES, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    ">" { return new TokenInfo(TipoToken.RELACIONALES, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "<=" { return new TokenInfo(TipoToken.RELACIONALES, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    ">=" { return new TokenInfo(TipoToken.RELACIONALES, yytext(), yyline + 1, yycolumn + 1, Color.black); }

    "AND"|"OR"|"NOT" { return new TokenInfo(TipoToken.LOGICOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }


    "=" { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "(" { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    ")" { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "," { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    ";" { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    "." { return new TokenInfo(TipoToken.SIGNOS, yytext(), yyline + 1, yycolumn + 1, Color.black); }
    {WhiteSpace} { /* Ignorar espacios en blanco */ }
    <<EOF>> { return new TokenInfo(TipoToken.EOF, "", yyline + 1, yycolumn + 1); }

    . { 
    
    return new TokenInfo(TipoToken.ERROR, yytext(), yyline + 1, yycolumn + 1);  // Se devuelve un token de error
    }