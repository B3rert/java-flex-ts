/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tslexer;

/**
 *
 * @author dsdev
 */
public enum Token {
    ID,             // Identificadores (nombres de variables, funciones, etc.)
    INT,            // Números enteros
    IGUAL,          // Operador de igualdad
    MAS,            // Operador de suma
    MUL,            // Operador de multiplicación
    ASIG,           // Operador de asignación
    MENOS,          // Operador de resta
    ERROR,          // Errores en el análisis
    COND,           // Palabras reservadas
    SEP,            // Separadores (paréntesis, corchetes, etc.)
    REAL,           // Números reales
    STRING,         // Para cadenas entre comillas
    NOT_RECOGNIZED, // Para tokens no reconocidos
    BRACKETS,
    NEWLINE,
    COMMENT;
}
