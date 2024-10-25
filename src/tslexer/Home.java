package tslexer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends JFrame {

    private JTextPane jTextPane1;
    private JButton btnSelectFile;

    public Home() {
        setTitle("Analizador Lexico TypeScript");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear JTextPane para mostrar los resultados
        jTextPane1 = new JTextPane();
        jTextPane1.setContentType("text/html"); // Establecer tipo de contenido HTML
        // Cambiar el color de fondo usando el valor hexadecimal
        jTextPane1.setBackground(Color.decode("#212121")); // Color gris oscuro
        jTextPane1.setEditable(false); // No editable
        JScrollPane scrollPane = new JScrollPane(jTextPane1);
        add(scrollPane, BorderLayout.CENTER);

        // Crear botón para seleccionar archivo
        btnSelectFile = new JButton("Seleccionar Archivo");
        btnSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Analizar();
            }
        });
        add(btnSelectFile, BorderLayout.SOUTH);
    }

    public void Analizar() {
        // Usar JFileChooser para seleccionar el archivo
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            // Usar un StringBuilder para almacenar el resultado
            StringBuilder resul = new StringBuilder();
            resul.append("<html><body><pre style='font-family: monospace;'>");

            try (Reader reader = new BufferedReader(new FileReader(archivo))) {
                TypeScriptLexer lexer = new TypeScriptLexer(reader);

                boolean comillas = false;

                while (true) {
                    Token token = lexer.yylex();
                    if (token == null) {
                        resul.append("</pre></body></html>");

                        jTextPane1.setText(resul.toString());
                        return;
                    }

                    // Lógica para formatear el resultado según el tipo de token
                    switch (token) {
                        case ERROR:
                        case NOT_RECOGNIZED:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:red;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case ID:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.lexema).append("</span>");
                            } else {
                                resul.append("<span style='color:white;'>").append(lexer.lexema).append("</span>");
                            }
                            break;
                        case INT:
                        case REAL:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.lexema).append("</span>");
                            } else {
                                resul.append("<span style='color:green;'>").append(lexer.lexema).append("</span>");
                            }
                            break;
                        case IGUAL:
                        case MAS:
                        case MUL:
                        case ASIG:
                        case MENOS:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:red;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case COND:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:#5564eb;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case STRING:
                            // Agregar el texto del STRING sin condiciones de color
                            resul.append("<span style='color:orange;'>").append(lexer.lexema).append("</span>");
                            comillas = !comillas; // Alternar el estado de comillas
                            break;
                        case COMMENT:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:gray;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case SEP:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:yellow;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case BRACKETS:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append("<span style='color:green;'>").append(lexer.yytext()).append("</span>");
                            }
                            break;
                        case NEWLINE:
                            resul.append("<br>");
                            break;
                        default:
                            if (comillas) {
                                resul.append("<span style='color:orange;'>").append(lexer.yytext()).append("</span>");
                            } else {
                                resul.append(lexer.yytext()); // Agregar texto normalmente
                            }
                    }

                }
            } catch (IOException e) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.setVisible(true);
        });
    }
}
