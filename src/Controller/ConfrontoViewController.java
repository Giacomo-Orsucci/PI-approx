package Controller;

import Model.SuccessioneChebyshev;
import View.ConfrontoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import Model.SuccessioneBellard;
import java.math.BigInteger;

//classe facente parte del package controller come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class ConfrontoViewController implements ActionListener {

    private ConfrontoView cV;

    private boolean perCicli;

    private int passaggi;

    private BigDecimal precisione;

    private boolean eccezione = false;

    public ConfrontoViewController(ConfrontoView cV, boolean perCicli) {
        this.cV = cV;
        this.perCicli = perCicli;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        /*
            Si identifica il bottone all'origine dell'evento per differenziare
            il tipo di calcolo da eseguire per il confronto della successioni di Bellard e Chebyshev. Al momento della conversione degli ingressi
            vengono inoltre gestite le eventuali eccezioni sugli inserimenti e gli inserimenti errati (che non rispettano gli ingressi richiesti). 
         */

        if (perCicli) {
            cV.getChebyshevTA().setText("Successione Chebyshev\n");
            cV.getBellardTA().setText("Successione Bellard\n");

            try {
                passaggi = Integer.parseInt(cV.getValoreTF().getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Inserire un numero intero di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
                eccezione = true;
            }
            if (passaggi <= 0 && !eccezione) {
                JOptionPane.showMessageDialog(null, "Inserire un numero maggiore di 0 di passaggi", "Errore sull'inserimento dei passaggi", JOptionPane.ERROR_MESSAGE);
            } else if (passaggi > 0) {
                SuccessioneChebyshev sCV = new SuccessioneChebyshev(passaggi, cV);
                SuccessioneBellard sBV = new SuccessioneBellard(passaggi, cV);
                sCV.calcoloPerCicli();
                sBV.calcoloPerCicli();
            }
            passaggi = 0;
            eccezione = false;
        } else {
            boolean controlloPrecisione = false;
            cV.getChebyshevTA().setText("Successione Chebyshev\n");
            cV.getBellardTA().setText("Successione Bellard\n");

            try {
                precisione = new BigDecimal(cV.getValoreTF().getText());
                controlloPrecisione = precisione.compareTo(new BigDecimal(BigInteger.ONE)) == -1;
                controlloPrecisione = controlloPrecisione && precisione.compareTo(new BigDecimal("0.00000000000001")) == 1;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                eccezione = true;
            }
            if (!controlloPrecisione || eccezione) {
                if (!eccezione) {
                    JOptionPane.showMessageDialog(null, "Inserire un numero in formato 0.001 che rispetti i limiti di Chebyshev (controllare in info)", "Errore sull'inserimento della precisione", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                SuccessioneChebyshev sCV = new SuccessioneChebyshev(precisione, cV);
                SuccessioneBellard sBV = new SuccessioneBellard(precisione, cV);
                sCV.calcoloPerPrecisione();
                sBV.calcoloPerPrecisione();
            }
            precisione = new BigDecimal(BigInteger.ONE);
            eccezione = false;
        }
    }

}
