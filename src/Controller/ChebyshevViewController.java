package Controller;

import Model.SuccessioneChebyshev;
import View.ChebyshevView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

//classe facente parte del package controller come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class ChebyshevViewController implements ActionListener {

    private boolean eccezione = false;

    private BigDecimal precisione;

    private ChebyshevView cV;

    private int passaggi = 0;

    public ChebyshevViewController(ChebyshevView cV) {
        this.cV = cV;
    }

    @Override
    public void actionPerformed(ActionEvent aE) {
        JButton origine = (JButton) aE.getSource();

        switch (origine.getText()) {
            /*
                switch per identificare il bottone all'origine dell'evento e quindi per differenziare
                il tipo di calcolo da eseguire con la successione di Chebyshev. Al momento della conversione degli ingressi
                vengono inoltre gestite le eventuali eccezioni sugli inserimenti e gli inserimenti errati (che non rispettano gli ingressi richiesti). 
             */
            case "Calcola per numero passaggi":
                cV.getCalcoloPassaggiTA().setText("");
                try {
                    passaggi = Integer.parseInt(cV.getPassaggiTF().getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero intero di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
                    eccezione = true;
                }
                if (passaggi <= 0 && !eccezione) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero maggiore di 0 di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
                } else if (passaggi > 0) {
                    SuccessioneChebyshev sCV = new SuccessioneChebyshev(passaggi, cV);
                    sCV.calcoloPerCicli();
                }
                passaggi = 0;
                eccezione = false;
                break;

            case "Calcola per precisione":
                boolean controlloPrecisione = true;
                cV.getCalcoloPrecisioneTA().setText("");
                try {
                    precisione = new BigDecimal(cV.getPrecisioneTF().getText());
                    controlloPrecisione = precisione.compareTo(new BigDecimal(BigInteger.ONE)) == -1;
                    controlloPrecisione = controlloPrecisione && precisione.compareTo(new BigDecimal("0.00000000000001")) == 1;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                    eccezione = true;
                }
                if (!controlloPrecisione || eccezione) {
                    if (!eccezione) {
                        JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001 che rispetti i limiti (controllare in info)", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    SuccessioneChebyshev sCV = new SuccessioneChebyshev(precisione, cV);
                    sCV.calcoloPerPrecisione();
                }
                precisione = new BigDecimal(BigInteger.ONE);
                eccezione = false;
                break;
        }
    }

}
