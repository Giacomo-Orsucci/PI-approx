package View;

import Controller.BellardViewController;
import java.awt.*;
import javax.swing.*;

//classe facente parte del package View come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class BellardView extends JFrame {

    private JPanel rootPanel = new JPanel();
    private JPanel panelSX = new JPanel();
    private JPanel panelDX = new JPanel();

    private JTextField passaggiTF = new JTextField();
    private JTextField precisioneTF = new JTextField();

    private JTextArea calcoloPassaggiTA = new JTextArea(20, 20);
    private JTextArea calcoloPrecisioneTA = new JTextArea(20, 20);

    private JButton nPassaggiB = new JButton("Calcola per numero passaggi");
    private JButton precisioneB = new JButton("Calcola per precisione");

    public BellardView(String titolo) {
        //creo il frame
        super(titolo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(600, 100, 1080, 720);

        //estraggo il panel del frame
        rootPanel = (JPanel) getContentPane();

        //imposto il layout a griglia
        panelSX.setLayout(new GridLayout(4, 1));
        panelDX.setLayout(new GridLayout(2, 1));

        //setto i TF
        passaggiTF.setPreferredSize(new Dimension(panelSX.getWidth(), 50));
        precisioneTF.setPreferredSize(new Dimension(panelSX.getWidth(), 50));
        passaggiTF.setHorizontalAlignment(JTextField.CENTER);
        precisioneTF.setHorizontalAlignment(JTextField.CENTER);

        //setto le TA
        calcoloPassaggiTA.setPreferredSize(new Dimension(500, this.getHeight()));
        calcoloPrecisioneTA.setPreferredSize(new Dimension(500, this.getHeight()));
        calcoloPassaggiTA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        calcoloPrecisioneTA.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.BLACK));
        calcoloPassaggiTA.setEditable(false);
        calcoloPrecisioneTA.setEditable(false);

        //setto i bottoni
        nPassaggiB.setHorizontalAlignment(JButton.CENTER);
        nPassaggiB.addActionListener(new BellardViewController(this));
        precisioneB.setHorizontalAlignment(JButton.CENTER);
        precisioneB.addActionListener(new BellardViewController(this));

        //aggiungo componenti al panel ovest
        panelSX.add(passaggiTF);
        panelSX.add(nPassaggiB);
        panelSX.add(precisioneTF);
        panelSX.add(precisioneB);

        //aggiungo componenti al panel centrale
        panelDX.add(calcoloPassaggiTA);
        panelDX.add(calcoloPrecisioneTA);

        //aggiungo i pannelli al pannello del frame
        rootPanel.add(panelSX, BorderLayout.WEST);
        rootPanel.add(panelDX, BorderLayout.CENTER);

        setVisible(true);
    }

    public JTextField getPassaggiTF() {
        return passaggiTF;
    }

    public void setPassaggiTF(JTextField passaggiTF) {
        this.passaggiTF = passaggiTF;
    }

    public JTextField getPrecisioneTF() {
        return precisioneTF;
    }

    public void setPrecisioneTF(JTextField precisioneTF) {
        this.precisioneTF = precisioneTF;
    }

    public JTextArea getCalcoloPassaggiTA() {
        return calcoloPassaggiTA;
    }

    public void setCalcoloPassaggiTA(JTextArea calcoloPassaggiTA) {
        this.calcoloPassaggiTA = calcoloPassaggiTA;
    }

    public JTextArea getCalcoloPrecisioneTA() {
        return calcoloPrecisioneTA;
    }

    public void setCalcoloPrecisioneTA(JTextArea calcoloPrecisioneTA) {
        this.calcoloPrecisioneTA = calcoloPrecisioneTA;
    }

    public JButton getnPassaggiB() {
        return nPassaggiB;
    }

    public void setnPassaggiB(JButton nPassaggiB) {
        this.nPassaggiB = nPassaggiB;
    }

    public JButton getPrecisioneB() {
        return precisioneB;
    }

    public void setPrecisioneB(JButton precisioneB) {
        this.precisioneB = precisioneB;
    }

}
