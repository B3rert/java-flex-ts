/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tslexer;

import java.io.File;

/**
 *
 * @author dsdev
 */
public class TsLexer {

   
   public static void main(String[] args) {
        // TODO code application logic here
        String rutaLexer = "C:\\Users\\dsdev\\OneDrive\\Escritorio\\PryectoPS\\java\\TsLexer\\src\\tslexer\\Lexer.flex";
        generarJavaLexer(rutaLexer);
    }
    
    
    public static void generarJavaLexer(String rutaLexer){
        File archivo = new File(rutaLexer);
       JFlex.Main.generate(archivo);
        
    }
}
