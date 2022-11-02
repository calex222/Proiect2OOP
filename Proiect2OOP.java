import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import classes.Librarie;
import classes.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.event.*;
import java.sql.Timestamp;

import java.sql.*;

public class Proiect2OOP implements ActionListener {

    private Librarie libCarti = new Librarie(500);
    private JFrame frame = new JFrame();
    private JPanel mainMenuPanel = new JPanel();
    private JPanel stocMenuPanel = new JPanel();
    private JPanel adaugaMenuPanel = new JPanel();
    private JPanel scoateMenuPanel = new JPanel();
    private JButton stocCartiButton = new JButton("STOC CARTI");
    private JButton adaugaButonMeniu = new JButton("ADAUGA CARTE NOUA");
    private JButton adaugaButton = new JButton("ADAUGA CARTE");
    private JButton alegeButton = new JButton("ALEGETI CARTE PE INDEX");
    private JButton scoateButton = new JButton("SCOATE CARTE");
    private JButton exitButton = new JButton("EXIT");
    private JButton backButton = new JButton("BACK");
    private JLabel instructiuniAdaugare = new JLabel(
            "Dati un titlu, un autor si un cost." + "\n"
                    + "Costul trebuie sa fie un numar intreg pozitiv mai mare de 0 si mai mic decat 500");

    private JLabel stocCartiGol = new JLabel("LIBRARIA NU ARE CARTI", SwingConstants.CENTER);

    public void actionPerformed(ActionEvent e) {
    }

    // helps format logger messages
    private String loggerMessageFormat(String s) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return (timestamp.toString() + " : " + s + "\n");
    }

    public Proiect2OOP() {

        Logger.getInstance().init();

        libCarti.initFromFile();

        JLabel titluLibrarieLabel = new JLabel("LIBRARIE", SwingConstants.CENTER);
        titluLibrarieLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        mainMenuPanel.setLayout(new GridLayout(5, 1));

        mainMenuPanel.add(titluLibrarieLabel);
        mainMenuPanel.add(stocCartiButton);
        mainMenuPanel.add(adaugaButonMeniu);
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
        adaugaMenuPanel.setLayout(new GridLayout(3, 1));

        // setup panel scoate

        scoateMenuPanel.setPreferredSize(new Dimension(1600, 900));
        scoateMenuPanel.setLayout(new GridLayout(1, 1));

        // pregatire butoane

        adaugaButonMeniu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainMenuPanel);
                adaugaMenuPanel.add(instructiuniAdaugare);
                adaugaMenuPanel.add(adaugaButton);
                adaugaMenuPanel.add(backButton);
                frame.add(adaugaMenuPanel);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logger.getInstance().writeLog(loggerMessageFormat("Back button pressed, no error"));
                stocMenuPanel.removeAll();
                scoateMenuPanel.removeAll();
                adaugaMenuPanel.removeAll();
                frame.remove(stocMenuPanel);
                frame.remove(adaugaMenuPanel);
                frame.remove(scoateMenuPanel);
                frame.add(mainMenuPanel);
                frame.repaint();
                frame.setVisible(true);
            }
        });

        scoateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logger.getInstance().writeLog(loggerMessageFormat("Scoate button pressed, no error"));
                if (libCarti.getNrCarti() == 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Libraria nu are carti!", "Fail",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.remove(mainMenuPanel);
                    scoateMenuPanel.setLayout(new GridLayout(libCarti.getNrCarti() + 2, 1));
                    ArrayList<JLabel> labelCarte = new ArrayList<JLabel>();
                    for (int i = 0; i < libCarti.getNrCarti(); i++) {
                        labelCarte.add(new JLabel(libCarti.getCarteLaIndex(i).toString() + "  LA INDEX  " + i));
                        scoateMenuPanel.add(labelCarte.get(i));
                    }
                    scoateMenuPanel.add(alegeButton);
                    scoateMenuPanel.add(backButton);
                    frame.add(scoateMenuPanel);
                    frame.repaint();
                    frame.setVisible(true);
                }
            }
        });

        alegeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logger.getInstance().writeLog(loggerMessageFormat("Alege button pressed, no error"));
                String indexString = javax.swing.JOptionPane.showInputDialog("INDEX: ");
                while (!isNumeric(indexString)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Dati un numar intreg", "Eroare numerica",
                            JOptionPane.ERROR_MESSAGE);
                    indexString = javax.swing.JOptionPane.showInputDialog("Costul cartii: ");
                }
                int indexInt = Integer.parseInt(indexString);
                if (indexInt < 0 || indexInt > (libCarti.getNrCarti() - 1)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Indexul nu a fost gasti", "Eroare index",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    libCarti.scoateCarteLaIndex(indexInt);
                    javax.swing.JOptionPane.showMessageDialog(null, "Cartea a fost scoasa cu succes", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logger.getInstance().writeLog(loggerMessageFormat("Exit button pressed, no error"));
                System.exit(0);
            }
        });

        stocCartiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logger.getInstance().writeLog(loggerMessageFormat("Stoc carti button pressed, no error"));
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
                        labelCarte.add(new JLabel(libCarti.getCarteLaIndex(i).toString() + "  LA INDEX  " + i));
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

                Logger.getInstance().writeLog(loggerMessageFormat("Adauga button pressed, no error"));
                String titlu = javax.swing.JOptionPane.showInputDialog("Titlul cartii: ");
                String autor = javax.swing.JOptionPane.showInputDialog("Titlul cartii: ");
                String costString = javax.swing.JOptionPane.showInputDialog("Costul cartii: ");
                while (!isNumeric(costString)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Dati un numar intreg", "Eroare numerica",
                            JOptionPane.ERROR_MESSAGE);
                    costString = javax.swing.JOptionPane.showInputDialog("Costul cartii: ");
                }
                if ((Integer.parseInt(costString) > 0)
                        && (Integer.parseInt(costString) < libCarti.getPretMaximCarte())) {
                    libCarti.adaugaCarte(titlu, autor, Integer.parseInt(costString));
                    javax.swing.JOptionPane.showMessageDialog(null, "Carte adaugata cu succes", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {

                    javax.swing.JOptionPane.showMessageDialog(null, "Cartea nu a fost adaugata, pret incorect", "Fail",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    // checks if a string can be converted to int, returns true is so, false
    // otherwise
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
        /*
         * try {
         * Class.forName("com.mysql.jdbc.Driver");
         * Connection con = DriverManager.getConnection(
         * "jdbc:mysql://localhost:3306/CartiDB", "root", "root");
         * // here sonoo is database name, root is username and password
         * Statement stmt = con.createStatement();
         * ResultSet rs = stmt.executeQuery("select * from emp");
         * while (rs.next())
         * System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " +
         * rs.getString(3));
         * con.close();
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         */
        new Proiect2OOP();
    }

}