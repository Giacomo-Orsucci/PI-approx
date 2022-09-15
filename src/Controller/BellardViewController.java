package Controller;

import Model.SuccessioneBellard;
import View.BellardView;
import java.awt.event.*;
import java.math.*;
import javax.swing.*;

//classe facente parte del package controller come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class BellardViewController implements ActionListener {

    private boolean eccezione = false;

    private BigDecimal precisione;

    private BellardView bV;

    private int passaggi = 0;

    public BellardViewController(BellardView bV) {
        this.bV = bV;
    }

    @Override
    public void actionPerformed(ActionEvent aE) {
        JButton origine = (JButton) aE.getSource();
        /*
            switch per identificare il bottone all'origine dell'evento e quindi per differenziare
            il tipo di calcolo da eseguire con la successione di Bellard. Al momento della conversione degli ingressi
            vengono inoltre gestite le eventuali eccezioni sugli inserimenti e gli inserimenti errati (che non rispettano gli ingressi richiesti). 
         */
        switch (origine.getText()) {
            case "Calcola per numero passaggi":
                bV.getCalcoloPassaggiTA().setText("");
                try {
                    passaggi = Integer.parseInt(bV.getPassaggiTF().getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero intero di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
                    eccezione = true;
                }
                if (passaggi <= 0 && !eccezione) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero maggiore di 0 di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
                } else if (passaggi > 0) {
                    SuccessioneBellard sBV = new SuccessioneBellard(passaggi, bV);
                    sBV.calcoloPerCicli();
                }
                passaggi = 0;
                eccezione = false;
                break;

            case "Calcola per precisione":
                boolean controlloPrecisione = true;
                bV.getCalcoloPrecisioneTA().setText("");
                try {
                    precisione = new BigDecimal(bV.getPrecisioneTF().getText());
                    controlloPrecisione = precisione.compareTo(new BigDecimal(BigInteger.ONE)) == -1;
                    controlloPrecisione = controlloPrecisione && precisione.compareTo(new BigDecimal("0.00000000000000000000000000000000001")) == 1;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                    eccezione = true;
                }
                if (!controlloPrecisione || eccezione) {
                    if (!eccezione) {
                        JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001 che rispetti i limiti (controllare in info)", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    SuccessioneBellard sBV = new SuccessioneBellard(precisione, bV);
                    sBV.calcoloPerPrecisione();
                }
                precisione = new BigDecimal(BigInteger.ONE);
                eccezione = false;
                break;
        }
    }

}
