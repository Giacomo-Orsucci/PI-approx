package View;

import javax.swing.*;

//classe facente parte del package View come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class InfoView extends JFrame {

    private JPanel rootPanel = new JPanel();
    private final JTextArea testo = new JTextArea();

    public InfoView(String titolo) {

        super(titolo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(600, 100, 720, 400);
        rootPanel = (JPanel) getContentPane();

        //scrivo nella TextArea
        testo.append("PROGRAMMA PER APPROSSIMARE IL PI GRECO.\n\nNOTE SUGLI INGRESSI DELLA SUCCESSIONE DI BELLARD: \n\n"
                + "non inserire un numero di passaggi troppo grande per non far andare \nin una specie di loop il programma a causa della pesantezza di calcolo\n"
                + "e non inserire una precisione con un numero di decimali superiore ai 35. Tale limite è dato dalle approssimazioni nel calcolo,"
                + "\nuna volta superato il trentacinquesimo decimale il programma non riesce a trovare valori più precisi del pi greco di riferimento"
                + "\ne quindi il ciclo di calcolo entra in loop."
                + "\n\nNOTE SUGLI INGRESSI DELLA SUCCESSIONE DI CHEBYSHEV: \n\n"
                + "non inserire un numero di passaggi troppo grande per non far andare \nin una specie di loop il programma a causa della pesantezza di calcolo\n"
                + "e non inserire una precisione con un numero di decimali superiore a 14. Tale limite è dato dalla formula stessa, la quale dopo il \n"
                + "quattordicesimo decimale \"sbaglia\", diventa più grande del pi greco di riferimento e quindi il ciclo di calcolo entra in loop.\n\n"
                + "NOTE GENERALI SULLE USCITE: \n\n"
                + "In uscita è possibile trovare la differenza tra il pi greco di riferimento \ne quello calcolato, in notazione scientifica (sotto forma di esponenziale).");

        testo.setEditable(false);

        rootPanel.add(testo);

        setVisible(true);
    }
}
