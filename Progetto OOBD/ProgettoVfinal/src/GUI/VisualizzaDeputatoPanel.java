package GUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class VisualizzaDeputatoPanel extends JPanel{
    private JLabel lbTAgNumeroSelezionato;
    private JLabel lbPrefissoNumeroSelezionato;
    private JLabel lbNumeroSelezionato;
    private JLabel lbTAgDeputato;
    private JLabel lbPrefissoDeputato;
    private JLabel lbNumeroDeputato;

    public VisualizzaDeputatoPanel (String TAgNumeroSelezionato,
             String PrefissoNumeroSelezionato,
             String NumeroSelezionato,
             String TAgDeputato,
             String PrefissoDeputato,
             String NumeroDeputato)  {

         lbTAgNumeroSelezionato=new JLabel(TAgNumeroSelezionato);
        lbPrefissoNumeroSelezionato=new JLabel(PrefissoNumeroSelezionato);
          lbNumeroSelezionato=new JLabel(NumeroSelezionato);
          lbTAgDeputato=new JLabel(TAgDeputato);
          lbPrefissoDeputato=new JLabel(PrefissoDeputato);
          lbNumeroDeputato=new JLabel(NumeroDeputato);

        this.setLayout(new GridLayout(4,1));
        JPanel panelNumero =new JPanel(new GridLayout(1,3));
        JPanel panelDeputato =new JPanel(new GridLayout(1,3));
        this.add(new JLabel("Hai Selezionato questo Numero"));
        panelNumero.add(lbTAgNumeroSelezionato);
        panelNumero.add(lbPrefissoNumeroSelezionato);
        panelNumero.add(lbNumeroSelezionato);
        this.add(panelNumero);
        this.add(new JLabel("Questo è il numero per il reindirizzamento"));
        panelDeputato.add(lbTAgDeputato);
        panelDeputato.add(lbPrefissoDeputato);
        panelDeputato.add(lbNumeroDeputato);
        this.add(panelDeputato);



    }

}