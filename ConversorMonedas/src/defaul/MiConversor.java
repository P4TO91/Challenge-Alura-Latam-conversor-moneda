package defaul;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;


public class MiConversor {

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel conversionPanel;
    private JPanel interestPanel;
    private JTextField txt_1;
    private JButton btnConvertir;
    private JComboBox<Moneda> cmb;
    private JComboBox<Opcion> cmb2;
    private JLabel lblConversionResult;
    private JTextField txtPrincipal;
    private JTextField txtInterestRate;
    private JTextField txtTimePeriod;
    private JButton btnCalculateInterest;
    private JLabel lblInterestResult;
    private JLabel lblInterestResult2;
 

    public enum Moneda {
        Dolar_USA,
        Euro,
        Libra_Esterlina,
        Yen_Japones,
        Won_sul_coreano,
    }

    public enum Opcion {
        Compra,
        Venta,
    }

  //Precios para convertir peso colombiano a la moneda extranjera
    public double dolarCompra = 4072.50;
    public double euroCompra = 4291.31033;
    public double libraCompra = 4946.80332;
    public double yenJaponesCompra = 27.16269;
    public double wonSulCoreanoCompra = 3.00367;
    //Precios para convertir la moneda extranjera a peso Colombiano
    public double dolarVenta = 4053.76;
    public double euroVenta = 4292.52646;
    public double libraVenta = 4948.83020;
    public double yenJaponesVenta = 27.16815;
    public double wonSulCoreanoVenta = 3.00456;

    public double valorInput = 0.00;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MiConversor window = new MiConversor();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MiConversor() {
        initialize();
        frame.setTitle("Conversor de Peso Colombiano a Monedas Extranjeras & Tasas de Interes");
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        conversionPanel = new JPanel();
        tabbedPane.addTab("Convertir Moneda", null, conversionPanel, null);
        conversionPanel.setLayout(null);
       
        txt_1 = new JTextField();
        txt_1.setBounds(21, 51, 124, 26);
        conversionPanel.add(txt_1);
        txt_1.setColumns(10);

        cmb = new JComboBox<Moneda>();
        cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
        cmb.setBounds(144, 10, 188, 27);
        conversionPanel.add(cmb);

        cmb2 = new JComboBox<Opcion>();
        cmb2.setModel(new DefaultComboBoxModel<>(Opcion.values()));
        cmb2.setBounds(21, 10, 124, 27);
        conversionPanel.add(cmb2);

        btnConvertir = new JButton("Convertir");
        btnConvertir.addActionListener(e -> Convertir());
        btnConvertir.setBounds(327, 9, 117, 29);
        conversionPanel.add(btnConvertir);

        lblConversionResult = new JLabel("$ 00.00");
        lblConversionResult.setBounds(185, 56, 99, 16);
        conversionPanel.add(lblConversionResult);

        interestPanel = new JPanel();
        tabbedPane.addTab("Calcular Interés", null, interestPanel, null);
        interestPanel.setLayout(null);

        JLabel lblTimeDescription = new JLabel("Ingrese Capital:");
        lblTimeDescription.setBounds(180, 1, 124, 15);
        interestPanel.add(lblTimeDescription);
        
        txtPrincipal = new JTextField();
        txtPrincipal.setBounds(175, 10, 124, 27);
        interestPanel.add(txtPrincipal);
        txtPrincipal.setColumns(10);
        
        JLabel lblTimeDescription1 = new JLabel("Ingrese interes (numero):");
        lblTimeDescription1.setBounds(7, 1, 160, 15);
        interestPanel.add(lblTimeDescription1);

        txtInterestRate = new JTextField();
        txtInterestRate.setBounds(4, 10, 160, 27);
        interestPanel.add(txtInterestRate);
        txtInterestRate.setColumns(10);
        
     // Crear un JLabel descriptivo
        JLabel lblTimeDescription2 = new JLabel("Ingrese el tiempo (meses):");
        lblTimeDescription2.setBounds(317, 1, 180,15);
        interestPanel.add(lblTimeDescription2);
        
        txtTimePeriod = new JTextField();
        txtTimePeriod.setBounds(310, 10, 180,27);
        interestPanel.add(txtTimePeriod);
        txtTimePeriod.setColumns(10);

        btnCalculateInterest = new JButton("Calcular Interés + Capital");
        btnCalculateInterest.addActionListener(e -> CalcularInteres());
        btnCalculateInterest.setBounds(125, 40, 200, 29);
        interestPanel.add(btnCalculateInterest);

        lblInterestResult = new JLabel("$ 00.00");
        lblInterestResult.setBounds(125, 80, 250, 16);
        interestPanel.add(lblInterestResult);
        
        lblInterestResult2 = new JLabel("$ 00.00");
        lblInterestResult2.setBounds(125, 120, 250, 16);
        interestPanel.add(lblInterestResult2);
       
    }

    public void Convertir() {
        if (Validar(txt_1.getText())) {
            Moneda moneda = (Moneda) cmb.getSelectedItem();
            Opcion opcion = (Opcion) cmb2.getSelectedItem();

            double resultado = 0.0;

            switch (moneda) {
            case Dolar_USA:
                if (opcion == Opcion.Compra) {
                    resultado = PesosAMoneda(dolarCompra);
                } else if (opcion == Opcion.Venta) {
                    resultado = MonedaAPeso(dolarVenta);
                }
                break;
            case Euro:
                if (opcion == Opcion.Compra) {
                    resultado = PesosAMoneda(euroCompra);
                } else if (opcion == Opcion.Venta) {
                    resultado = MonedaAPeso(euroVenta);
                }
                break;
            case Libra_Esterlina:
                if (opcion == Opcion.Compra) {
                    resultado = PesosAMoneda(libraCompra);
                } else if (opcion == Opcion.Venta) {
                    resultado = MonedaAPeso(libraVenta);
                }
                break;
            case Yen_Japones:
                if (opcion == Opcion.Compra) {
                    resultado = PesosAMoneda(yenJaponesCompra);
                } else if (opcion == Opcion.Venta) {
                    resultado = MonedaAPeso(yenJaponesVenta);
                }
                break;
            case Won_sul_coreano:
                if (opcion == Opcion.Compra) {
                    resultado = PesosAMoneda(wonSulCoreanoCompra);
                } else if (opcion == Opcion.Venta) {
                    resultado = MonedaAPeso(wonSulCoreanoVenta);
                }
                break;
           
            default:
                throw new IllegalArgumentException("Unexpected value: " + moneda);
        }

            lblConversionResult.setText("$ " + Redondear(resultado));
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            String numeroFormateado = df.format(resultado);

            lblConversionResult.setText("$ " + numeroFormateado); // Actualizar el JLabel con el resultado formateado
        }
    }

    public void CalcularInteres() {
        if (Validar(txtPrincipal.getText()) && Validar(txtInterestRate.getText()) && Validar(txtTimePeriod.getText())) {
            double principal = Double.parseDouble(txtPrincipal.getText());
            double tasaInteres = Double.parseDouble(txtInterestRate.getText())/100; // Convertir la tasa a decimal
            double tiempo = Double.parseDouble(txtTimePeriod.getText());

            double interes = CalcularInteresSimple(principal, tasaInteres, tiempo);
            double interes2 = CalcularInteresCompuesto(principal, tasaInteres, tiempo);
            

            lblInterestResult.setText("Interés simple: $" + Redondear(interes));
            lblInterestResult2.setText("Interés Compuesto: $" + Redondear(interes2));
            
        }
    }

    public double PesosAMoneda(double moneda) {
        double res = valorInput / moneda;
        return res;
    }

    public double MonedaAPeso(double moneda) {
        double res = valorInput * moneda;
        return res;
    }

    public double CalcularInteresSimple(double principal, double tasaInteres, double tiempo) {
        double interes = principal+(principal * tasaInteres * tiempo);
        return interes;
    }
    
    public double CalcularInteresCompuesto(double principal, double tasaInteres, double tiempo) {
        double interes2 = principal * Math.pow(1 + tasaInteres, tiempo);
        return interes2;
    }

    public String Redondear(double valor) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(valor);
    }

    public boolean Validar(String texto) {
        try {
            double x = Double.parseDouble(texto);
            valorInput = x;
            return true;
        } catch (NumberFormatException e) {
            lblConversionResult.setText("número inválido.");
            lblInterestResult.setText("número inválido.");
            return false;
        }
    }
}

