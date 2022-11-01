import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import classes.Librarie;
import classes.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.event.*;
import java.sql.Timestamp;

public class Proiect2OOP implements ActionListener {

    private Librarie libCarti = new Librarie(500);
    private JFrame frame = new JFrame();
    private JPanel mainMenuPanel = new JPanel();
    private JPanel stocMenuPanel = new JPanel();
    private JPanel adaugaMenuPanel = new JPanel();
    private JPanel scoateMenuPanel = new JPanel();
    private JButton stocCartiButton = new JButton("STOC CARTI");
    private JButton adaugaButton = new JButton("ADAUGA CARTE");
    private JButton scoateButton = new JButton("SCOATE CARTE");
    private JButton exitButton = new JButton("EXIT");
    private JButton backButton = new JButton("BACK");

    private JLabel stocCartiGol = new JLabel("LIBRARIA NU ARE CARTI", SwingConstants.CENTER);

    public void actionPerformed(ActionEvent e) {
    }

    private String loggerMessageFormat(String s) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return (timestamp.toString() + " : " + s + "\n");
    }

    public Proiect2OOP() {

        Logger.getInstance().init();

        JLabel titluLibrarieLabel = new JLabel("LIBRARIE", SwingConstants.CENTER);
        titluLibrarieLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        mainMenuPanel.setLayout(new GridLayout(5, 1));

        mainMenuPanel.add(titluLibrarieLabel);
        mainMenuPanel.add(stocCartiButton);
        mainMenuPanel.add(adaugaButton);
        mainMenuPanel.add(scoateButton);
        mainMenuPanel.add(exitButton);
        mainMenuPanel.setPreferredSize(new Dimension(1600, 900));
        frame.add(mainMenuPanel);

        frame.setTitle("Librarie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // setup panel stoc carti

        stocMenuPanel.setPreferredSize(new Dimension(1600, 900));
        stocMenuPanel.setLayout(new GridLayout(1, 1));

        // setup panel adauga

        adaugaMenuPanel.setPreferredSize(new Dimension(1600, 900));
        adaugaMenuPanel.setLayout(new GridLayout(1, 1));

        // setup panel scoate

        scoateMenuPanel.setPreferredSize(new Dimension(1600, 900));
        scoateMenuPanel.setLayout(new GridLayout(1, 1));

        // pregatire butoane

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stocMenuPanel.removeAll();
                frame.remove(stocMenuPanel);
                frame.remove(adaugaMenuPanel);
                frame.remove(scoateMenuPanel);
                frame.add(mainMenuPanel);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        stocCartiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainMenuPanel);
                if (libCarti.getNrCarti() == 0) {
                    stocMenuPanel.setLayout(new GridLayout(2, 1));
                    stocMenuPanel.add(stocCartiGol);
                    stocMenuPanel.add(backButton);
                    frame.add(stocMenuPanel);
                    frame.repaint();
                    frame.setVisible(true);
                } else {
                    stocMenuPanel.setLayout(new GridLayout(libCarti.getNrCarti() + 1, 1));
                    ArrayList<JLabel> labelCarte = new ArrayList<JLabel>();
                    for (int i = 0; i < libCarti.getNrCarti(); i++) {
                        labelCarte.add(new JLabel(libCarti.getCarteLaIndex(i).toString()));
                        stocMenuPanel.add(labelCarte.get(i));
                    }
                    stocMenuPanel.add(backButton);
                    frame.add(stocMenuPanel);
                    frame.repaint();
                    frame.setVisible(true);
                }
            }
        });

        adaugaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Logger.getInstance().writeLog(loggerMessageFormat("Adauga button pressed"));
                String titlu = javax.swing.JOptionPane.showInputDialog("Titlul cartii: ");
                String autor = javax.swing.JOptionPane.showInputDialog("Titlul cartii: ");
                String costString = javax.swing.JOptionPane.showInputDialog("Costul cartii: ");
                while (!isNumeric(costString)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Dati un numar intreg", "Eroare numerica",
                            JOptionPane.ERROR_MESSAGE);
                    costString = javax.swing.JOptionPane.showInputDialog("Costul cartii: ");
                }
                if (Integer.parseInt(costString) < libCarti.getPretMaximCarte()) {
                    libCarti.adaugaCarte(titlu, autor, Integer.parseInt(costString));
                    javax.swing.JOptionPane.showMessageDialog(null, "Carte adaugata cu succes", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Cartea nu a fost adaugata, pret prea mare", "Fail",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        new Proiect2OOP();
    }

}