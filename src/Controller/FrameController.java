package Controller;

import View.BellardView;
import View.ChebyshevView;
import View.ConfrontoView;
import View.InfoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

//classe facente parte del package controller come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class FrameController implements ActionListener {

    /*  
        Creo un determinato frame a seconda del bottone premuto.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton origine = (JButton) e.getSource();

        switch (origine.getText()) {
            case "Chebyshev":
                ChebyshevView cheby = new ChebyshevView("Successione Chebyshev");
                break;

            case "Bellard":
                BellardView belly = new BellardView("Successione Bellard");
                break;

            case "Per precisone":
                ConfrontoView confyP = new ConfrontoView("Confronto per precisione", false);
                break;

            case "Per passaggi":
                ConfrontoView confyC = new ConfrontoView("Confronto per passaggi", true);
                break;
            case "Info sul programma":
                InfoView infoView = new InfoView("Informazioni sul programma");
                break;
        }
    }

}
