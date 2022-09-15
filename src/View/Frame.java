package View;

import Controller.FrameController;
import javax.swing.*;

//classe facente parte del package View come previsto dal pattern MVC.
/**
 *
 * @author Giacomo Orsucci e Leonardo Giambini 4IC.
 */
public class Frame extends JFrame {

    private final JButton chebyshev = new JButton("Chebyshev");
    private final JButton bellard = new JButton("Bellard");
    private final JButton confrontoP = new JButton("Per precisone");
    private final JButton confrontoC = new JButton("Per passaggi");
    private final JButton info = new JButton("Info sul programma");

    private final JLabel confrontoL = new JLabel("Funzioni a confronto");

    private JPanel rootPanel = new JPanel();

    private FrameController controller = new FrameController();

    public Frame(String titolo) {

        super(titolo);

        setBounds(200, 100, 720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        rootPanel = (JPanel) getContentPane();
        rootPanel.setLayout(null);

        //setto posizione e dimenzione dei bottoni
        chebyshev.setBounds(260, 45, 200, 100);
        bellard.setBounds(260, 335, 200, 100);
        confrontoL.setBounds(260, 190, 200, 100);
        confrontoC.setBounds(50, 190, 200, 100);
        confrontoP.setBounds(470, 190, 200, 100);
        info.setBounds(10, 10, 150, 50);

        //aggiungo i bottoni al listener
        chebyshev.addActionListener(controller);
        bellard.addActionListener(controller);
        confrontoC.addActionListener(controller);
        confrontoP.addActionListener(controller);
        info.addActionListener(controller);

        //imposto l'allineamento centrale alla label
        confrontoL.setHorizontalAlignment(JLabel.CENTER);

        //aggiungo i bottoni al pannello
        rootPanel.add(chebyshev);
        rootPanel.add(bellard);
        rootPanel.add(confrontoL);
        rootPanel.add(confrontoC);
        rootPanel.add(confrontoP);
        rootPanel.add(info);

        setVisible(true);
    }

}
