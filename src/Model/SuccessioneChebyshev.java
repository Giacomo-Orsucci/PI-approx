package Model;

import View.ChebyshevView;
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
public class SuccessioneChebyshev {

    private JTextArea areaStampa;

    private ChebyshevView cV;

    private ConfrontoView confronto;

    private int numCicli;

    private BigDecimal precisione;
    private BigDecimal piCalcolato;

    //pi greco di riferimento con 100 decimali passato come stringa (unico modo per dare questo valore al Big Decimal).
    private final static BigDecimal PIRIFERIMENTO = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    private final static int NRISULTATI = 20;

    public SuccessioneChebyshev(int numCicli, ChebyshevView cV) { //costruttore per calcolo con numero passaggi

        this.numCicli = numCicli;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.cV = cV;
        areaStampa = this.cV.getCalcoloPassaggiTA();
    }

    public SuccessioneChebyshev(BigDecimal precisione, ChebyshevView cV) { //costruttore per calcolo con precisione

        this.precisione = precisione;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.cV = cV;
        areaStampa = this.cV.getCalcoloPrecisioneTA();
    }

    public SuccessioneChebyshev(int numCicli, ConfrontoView confronto) { //costruttore per confronto con calcolo con numero passaggi

        this.numCicli = numCicli;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.cV = null;
        this.confronto = confronto;
        areaStampa = this.confronto.getChebyshevTA();
    }

    public SuccessioneChebyshev(BigDecimal precisione, ConfrontoView confronto) { //costruttore per confronto con calcolo con precisione

        this.precisione = precisione;
        piCalcolato = new BigDecimal(BigInteger.ZERO);
        this.cV = null;
        this.confronto = confronto;
        areaStampa = this.confronto.getChebyshevTA();
    }

    //applico la successione
    private BigDecimal formula(int n) {

        BigDecimal fat1Num = new BigDecimal(-1);
        BigDecimal fat2Num = new BigDecimal(2);
        int appEspNum2 = (2 * n) + 1;

        fat1Num = fat1Num.pow(n);
        fat2Num = fat2Num.subtract(BigDecimal.valueOf(Math.sqrt(3)));
        fat2Num = fat2Num.pow(appEspNum2);
        fat1Num = fat1Num.multiply(fat2Num); //ho calcolato il numeratore
        //ho calcolato tutta la successione, mi manca di moltiplicare per 12 quando salvo i risultati
        fat1Num = fat1Num.divide(BigDecimal.valueOf(appEspNum2), MathContext.DECIMAL128);
        return fat1Num;
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
        for (int i = 0; i < numCicli; i++) {
            piCalcolato = piCalcolato.add(formula(i));
            if (i % intervallo == 0) {

                areaStampa.append("Risultato numero " + numeroRisultato + ": "
                        + piCalcolato.multiply(BigDecimal.valueOf(12)).subtract(PIRIFERIMENTO).abs().round(MathContext.DECIMAL128).toString() + "\n");

                numeroRisultato++;
            }
        }
    }

    public void calcoloPerPrecisione() {

        int uscitaInt, numeroRisultato = 0;
        BigDecimal appoggio = new BigDecimal(0);
        int i = 0;
        /*
            applico la successione fino a quando la differenza tra il pi greco di riferimento
            e quello calcolato con la formula non è minore o uguale alla precisione inserita dall'utente.
            Questo primo ciclo serve per capire quante iterazioni siano necessarie, e quindi ogni quante iterazioni
            stampare il risultato per visualizzare un totale di 20 risultati.
         */
        do {

            piCalcolato = piCalcolato.add(formula(i));
            appoggio = piCalcolato.multiply(BigDecimal.valueOf(12));
            appoggio = PIRIFERIMENTO.subtract(appoggio);
            i++;
            appoggio = appoggio.abs();
            uscitaInt = appoggio.compareTo(precisione); //1 se l'appoggio è maggiore della precisione
        } while (uscitaInt == 1);
        piCalcolato = new BigDecimal(0);
        appoggio = new BigDecimal(0);
        int intervallo = i / NRISULTATI;

        if (i < NRISULTATI) {
            intervallo = 1;
        }

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
                        + piCalcolato.multiply(BigDecimal.valueOf(12)).subtract(PIRIFERIMENTO).abs().round(MathContext.DECIMAL128).toString() + "\n");
            }
            appoggio = piCalcolato.multiply(BigDecimal.valueOf(12));
            appoggio = PIRIFERIMENTO.subtract(appoggio);
            i++;
            appoggio = appoggio.abs();
            uscitaInt = appoggio.compareTo(precisione); //1 se l'appoggio è maggiore della precisione
        } while (uscitaInt == 1);
        areaStampa.append("\n Numero di iterazioni: " + i);
        numeroRisultato = 0;
    }
}
