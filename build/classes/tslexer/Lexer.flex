
package tslexer;
import static tslexer.Token.*;

%%

%class TypeScriptLexer
%type Token
L = [A-Za-z_]
D = [0-9]
P = [(){}\[\];,]

%{
public String lexema;
%}

%%

"//".*              { return COMMENT; }
"="                 { return ASIG; }
"=="                { return IGUAL; }
"+"                 { return MAS; }
"*"                 { return MUL; }
"-"                 { return MENOS; }

// Palabras reservadas en azul
"break"|"case"|"catch"|"class"|"const"|"continue"|"debugger"|"default"|"delete"|"do"|"else"|
"enum"|"export"|"extends"|"false"|"finally"|"for"|"function"|"if"|"import"|"in"|"instanceof"|
"new"|"null"|"return"|"super"|"switch"|"this"|"throw"|"true"|"try"|"typeof"|"var"|"void"|
"while"|"with"|"let"|"abstract"|"arguments"|"await"|"boolean"|"byte"|"char"|"double"|"final"|
"float"|"goto"|"implements"|"int"|"interface"|"long"|"native"|"package"|"private"|"protected"|
"public"|"short"|"static"|"synchronized"|"transient"|"volatile"|"yield"|"as"|"any"|"async"|
"bigint"|"constructor"|"declare"|"from"|"get"|"infer"|"intrinsic"|"is"|"keyof"|"module"|
"namespace"|"never"|"readonly"|"require"|"number"|"object"|"set"|"string"|"symbol"|"type"|
"undefined"|"unique"|"unknown"|"global"|"override" { return COND; }

"\{"|"\}"|"\["|"\]"|"("|")" {lexema = yytext(); return BRACKETS;}
"\n"            {lexema = yytext(); return Token.NEWLINE; }
{L}({L}|{D})*       { lexema = yytext(); return ID; }
[-+]?{D}+\.[D]+     { lexema = yytext(); return REAL; }
[-+]?{D}+           { lexema = yytext(); return INT; }
{P}                 { return SEP; }
"\""|"\'"|"\`"        { lexema = yytext(); return STRING; }
.                   { lexema = yytext(); return NOT_RECOGNIZED; } 
