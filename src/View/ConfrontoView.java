package View;

import Controller.ConfrontoViewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

//classe facente parte del package View come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class ConfrontoView extends JFrame {

    private JPanel panelDX = new JPanel();
    private JPanel panelSX = new JPanel();
    private JPanel rootPanel = new JPanel();

    private JTextField valoreTF = new JTextField();

    private JTextArea chebyshevTA = new JTextArea(21, 21);
    private JTextArea bellardTA = new JTextArea(21, 21);

    private JButton calcolaB = new JButton("Confronta le successioni");

    public ConfrontoView(String titolo, boolean perCicli) {

        //creo il frame
        super(titolo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(600, 100, 1080, 720);

        //estraggo il panel del frame
        rootPanel = (JPanel) getContentPane();

        //imposto i layout dei pannelli
        panelDX.setLayout(new GridLayout(1, 2));
        panelSX.setLayout(new GridLayout(2, 1));

        //setto la TF
        valoreTF.setPreferredSize(new Dimension(panelSX.getWidth(), 200));
        valoreTF.setHorizontalAlignment(JTextField.CENTER);

        //setto il bottone
        calcolaB.setHorizontalAlignment(JButton.CENTER);
        calcolaB.addActionListener(new ConfrontoViewController(this, perCicli));

        //setto le TA
        chebyshevTA.setPreferredSize(new Dimension(500, this.getHeight()));
        bellardTA.setPreferredSize(new Dimension(500, this.getHeight()));
        chebyshevTA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        bellardTA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        chebyshevTA.setEditable(false);
        bellardTA.setEditable(false);

        //aggiungo componenti al pannello ovest
        panelDX.add(chebyshevTA);
        panelDX.add(bellardTA);

        //aggiungo componenti al pannello centrale
        panelSX.add(valoreTF);
        panelSX.add(calcolaB);

        //aggiungo i pannelli al pannello del frame
        rootPanel.add(panelSX, BorderLayout.WEST);
        rootPanel.add(panelDX, BorderLayout.CENTER);

        setVisible(true);

    }

    public JTextField getValoreTF() {
        return valoreTF;
    }

    public void setValoreTF(JTextField valoreTF) {
        this.valoreTF = valoreTF;
    }

    public JTextArea getChebyshevTA() {
        return chebyshevTA;
    }

    public void setChebyshevTA(JTextArea chebyshevTA) {
        this.chebyshevTA = chebyshevTA;
    }

    public JTextArea getBellardTA() {
        return bellardTA;
    }

    public void setBellardTA(JTextArea bellardTA) {
        this.bellardTA = bellardTA;
    }

    public JButton getCalcolaB() {
        return calcolaB;
    }

    public void setCalcolaB(JButton calcolaB) {
        this.calcolaB = calcolaB;
    }
}
