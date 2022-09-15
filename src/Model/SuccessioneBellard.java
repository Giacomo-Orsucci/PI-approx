package Model;

import View.BellardView;
import View.ConfrontoView;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import javax.swing.JTextArea;

//classe facente parte del package Model come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class SuccessioneBellard {

    private JTextArea areaStampa;

    private BellardView bV;

    private ConfrontoView confronto;

    private int numCicli;

    private BigDecimal precisione;
    private BigDecimal piCalcolato;

    //pi greco di riferimento con 100 decimali passato come stringa (unico modo per dare questo valore al Big Decimal).
    private final static BigDecimal PIRIFERIMENTO = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    private final static int NRISULTATI = 20;

    public SuccessioneBellard(int numCicli, BellardView bV) { //costruttore per calcolo con numero passaggi

        this.numCicli = numCicli;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.bV = bV;
        areaStampa = bV.getCalcoloPassaggiTA();
    }

    public SuccessioneBellard(BigDecimal precisione, BellardView bV) { //costruttore per calcolo con precisione

        this.precisione = precisione;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.bV = bV;
        areaStampa = bV.getCalcoloPrecisioneTA();
    }

    public SuccessioneBellard(int numCicli, ConfrontoView confronto) { //costruttore per confronto con calcolo con numero passaggi

        this.numCicli = numCicli;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.confronto = confronto;
        areaStampa = this.confronto.getBellardTA();
    }

    public SuccessioneBellard(BigDecimal precisione, ConfrontoView confronto) { //costruttore per confronto con calcolo con precisione

        this.precisione = precisione;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.confronto = confronto;
        areaStampa = this.confronto.getBellardTA();
    }

    //applico la successione
    private BigDecimal formula(int n) {

        BigDecimal num1 = new BigDecimal(-1); //numeratore del primo termine ma anche primo fattore
        BigDecimal den1 = new BigDecimal(2);
        BigDecimal fat2 = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(-32);
        BigDecimal den2 = new BigDecimal(4);

        num1 = num1.pow(n); //calcolo il primo termine del primo fattore
        den1 = den1.pow(n * 10);
        num1 = num1.divide(den1, MathContext.DECIMAL128);

        den2 = den2.multiply(BigDecimal.valueOf(n)); //calcolo il primo termine del secondo fattore
        den2 = den2.add(BigDecimal.ONE);
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(-1);  //calcolo il secondo termine del secondo fattore
        den2 = new BigDecimal(4 * n);
        den2 = den2.add(BigDecimal.valueOf(3));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(256);  //calcolo il terzo termine del secondo fattore
        den2 = new BigDecimal(10 * n);
        den2 = den2.add(BigDecimal.valueOf(1));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(-64);  //calcolo il quarto termine del secondo fattore
        den2 = new BigDecimal(10 * n);
        den2 = den2.add(BigDecimal.valueOf(3));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(-4);  //calcolo il quinto termine del secondo fattore
        den2 = new BigDecimal(10 * n);
        den2 = den2.add(BigDecimal.valueOf(5));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(-4);  //calcolo il sesto termine del secondo fattore
        den2 = new BigDecimal(10 * n);
        den2 = den2.add(BigDecimal.valueOf(7));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        num2 = new BigDecimal(1);  //calcolo il settimo termine del secondo fattore
        den2 = new BigDecimal(10 * n);
        den2 = den2.add(BigDecimal.valueOf(9));
        num2 = num2.divide(den2, MathContext.DECIMAL128);

        fat2 = fat2.add(num2);

        return num1.multiply(fat2);
    }

    /*
        Applico la formula per un numero di iterazioni indicato in ingresso dall'utente.
     */
    public void calcoloPerCicli() {
        int intervallo = numCicli / NRISULTATI;
        int numeroRisultato = 1;
        if (intervallo == 0) {
            intervallo = 1;
        }
        for (int i = 0; i != numCicli; i++) {
            piCalcolato = piCalcolato.add(formula(i));
            if (i % intervallo == 0) {
                areaStampa.append("Risultato numero " + numeroRisultato + ": "
                        + PIRIFERIMENTO.subtract(piCalcolato.multiply(BigDecimal.valueOf(1d / 64d))).abs().round(MathContext.DECIMAL128).toString() + "\n");
                numeroRisultato++;
            }
        }
    }

    public void calcoloPerPrecisione() {

        int uscitaInt, numeroRisultato = 0;
        int i = 0;

        BigDecimal appoggio;

        /*
            applico la successione fino a quando la differenza tra il pi greco di riferimento
            e quello calcolato con la formula non è minore o uguale alla precisione inserita dall'utente.
            Questo primo ciclo serve per capire quante iterazioni siano necessarie, e quindi ogni quante iterazioni
            stampare il risultato per visualizzare un totale di 20 risultati.
         */
        do {

            piCalcolato = piCalcolato.add(formula(i));
            appoggio = piCalcolato.multiply(BigDecimal.valueOf(1d / 64d));
            appoggio = PIRIFERIMENTO.subtract(appoggio);
            i++;
            appoggio = appoggio.abs();
            uscitaInt = appoggio.compareTo(precisione); //1 se l'appoggio è maggiore della precisione
        } while (uscitaInt == 1);

        int intervallo = i / NRISULTATI;
        if (i < NRISULTATI) {
            intervallo = 1;
        }

        piCalcolato = new BigDecimal(0);

        i = 0;

        /*
            riapplico la successione fino a quando la differenza tra il pi greco di riferimento
            e quello calcolato con la formula non è minore o uguale alla precisione inserita dall'utente.
            In questo nuovo ciclo stampo i risultati.
         */
        do {
            numeroRisultato++;
            piCalcolato = piCalcolato.add(formula(i));
            if (i % intervallo == 0) {
                areaStampa.append("Risultato numero " + numeroRisultato + ": "
                        + PIRIFERIMENTO.subtract(piCalcolato.multiply(BigDecimal.valueOf(1d / 64d))).abs().round(MathContext.DECIMAL128).toString() + "\n");
            }
            appoggio = piCalcolato.multiply(BigDecimal.valueOf(1d / 64d));
            appoggio = PIRIFERIMENTO.subtract(appoggio);
            i++;
            appoggio = appoggio.abs();
            uscitaInt = appoggio.compareTo(precisione); //1 se l'appoggio è maggiore della precisione
        } while (uscitaInt == 1);
        areaStampa.append("\n Numero di iterazioni: " + i);
    }
}
