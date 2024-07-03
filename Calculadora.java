import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {
    private JTextField pantalla;
    private double numero1, numero2, resultado;
    private String operacion;

    public Calculadora() {
        super("Calculadora");
        setLayout(new BorderLayout());

        pantalla = new JTextField(20);
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);

        JPanel panelNumeros = new JPanel(new GridLayout(4, 3));
        add(panelNumeros, BorderLayout.CENTER);

        String[] numeros = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "."};
        for (String numero : numeros) {
            JButton boton = new JButton(numero);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    pantalla.setText(pantalla.getText() + ((JButton) e.getSource()).getText());
                }
            });
            panelNumeros.add(boton);
        }

        JPanel panelOperaciones = new JPanel(new GridLayout(5, 1));
        add(panelOperaciones, BorderLayout.EAST);

        String[] operaciones = {"+", "-", "*", "/", "=", "C"};
        for (String operacion : operaciones) {
            JButton boton = new JButton(operacion);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String texto = ((JButton) e.getSource()).getText();
                    if (texto.equals("=")) {
                        calcularResultado();
                    } else if (texto.equals("C")) {
                        limpiarPantalla();
                    } else {
                        numero1 = Double.parseDouble(pantalla.getText());
                        pantalla.setText("");
                        Calculadora.this.operacion = texto;
                    }
                }
            });
            panelOperaciones.add(boton);
        }

        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void calcularResultado() {
        numero2 = Double.parseDouble(pantalla.getText());
        switch (operacion) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                resultado = numero1 / numero2;
                break;
        }
        pantalla.setText(String.valueOf(resultado));
    }

    private void limpiarPantalla() {
        pantalla.setText("");
        numero1 = 0;
        numero2 = 0;
        operacion = "";
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}