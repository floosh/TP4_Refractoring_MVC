/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Modele.Modele;
import Modele.MoveFlocking;
import Modele.MoveRandom;
import Modele.Tortue;
import Modele.TortueAutonome;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author win
 */
public class mainWindows extends JFrame implements ActionListener {

    private Modele modele;
    private Controleur controleur;
    
    
    private Tortue current;

    private FeuilleDessin feuille;
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);
    private JTextField inputValue;
    private JComboBox colorList;

    private enum Mode {
        FLOCKING, AUTO, MANUEL
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                mainWindows fenetre;

                Object[] options = {"Killer feature -> le flocking","Des tortues automatiques !", "Je préfère conduire mes tortues moi-même"};
                int n = JOptionPane.showOptionDialog(null, "De quelle manière les morceaux de polygones (ou tortues) doivent se déplacer ?",
                        "Menu principal",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                switch (n) {
                    case 0:
                        fenetre = new mainWindows(Mode.FLOCKING);
                        break;
                    case 1:
                        fenetre = new mainWindows(Mode.AUTO);
                        break;
                    case 2:
                        fenetre = new mainWindows(Mode.MANUEL);
                        break;
                    default:
                        return;
                }
                fenetre.setVisible(true);
            }
        });

    }

    private void quitter() {
        System.exit(0);
    }

    public mainWindows(Mode mode) {
        super("un logo tout simple");
        init(mode, 600, 400);

        if (mode == Mode.AUTO || mode == Mode.FLOCKING) {
            gameLoop();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    private void init(Mode mode, int width, int height) {

        // Such model
        modele = new Modele(width, height);

        // Controller, wow
        this.controleur = new Controleur(modele);

        // Much design patern
        feuille = new FeuilleDessin(modele);
        modele.addObserver(feuille);

        getContentPane().setLayout(new BorderLayout(10, 10));
        
        // Wow, Layouts
        JMenuBar menubar = new JMenuBar();
        // WTF dat ugly switch ...
        switch (mode) {
            case MANUEL:

                current = controleur.addTortue(Color.black);

                JToolBar toolBar = new JToolBar();
                JPanel buttonPanel = new JPanel();

                buttonPanel.add(toolBar);

                getContentPane().add(buttonPanel, "North");

                addButton(toolBar, "Ajouter", "Nouvelle tortue", null);

                toolBar.add(Box.createRigidArea(HGAP));
                inputValue = new JTextField("45", 5);
                toolBar.add(inputValue);
                addButton(toolBar, "Avancer", "Avancer 50", null);
                addButton(toolBar, "Droite", "Droite 45", null);
                addButton(toolBar, "Gauche", "Gauche 45", null);

                // is dat r'ly used ?
                JMenu menuCommandes = new JMenu("Commandes"); // on installe le premier menu
                menubar.add(menuCommandes);
                addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
                addMenuItem(menuCommandes, "Droite", "Droite", -1);
                addMenuItem(menuCommandes, "Gauche", "Gauche", -1);

                // Wow, much colors
                String[] colorStrings = {"noir", "bleu", "cyan", "gris fonce", "rouge",
                    "vert", "gris clair", "magenta", "orange",
                    "gris", "rose", "jaune"};

                // Create the combo box, wow
                toolBar.add(Box.createRigidArea(HGAP));
                JLabel colorLabel = new JLabel("   Couleur: ");
                toolBar.add(colorLabel);
                colorList = new JComboBox(colorStrings);
                toolBar.add(colorList);

                break;
            case AUTO:
                for (int i = 0; i < 10; i++) {
                    modele.addTortue(new TortueAutonome(modele.width/2, modele.height/2, Tortue.Forme.TRIANGLE, Color.black, new MoveRandom()));
                }
                break;
            case FLOCKING:
                for (int i = 0; i < 10; i++) {
                    modele.addTortue(new TortueAutonome(modele.width/2, modele.height/2, Tortue.Forme.CERCLE, Color.black, new MoveFlocking(modele)));
                }
                break;
                
        }

        // Menus
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        feuille.setBackground(Color.white);
        feuille.setSize(new Dimension(width, height));
        feuille.setPreferredSize(new Dimension(width, height));

        getContentPane().add(feuille, "Center");
        
        this.setResizable(false);
        
        pack();
        setVisible(true);
    }

    //Gimme gimme gimme a loop before midnight !
    private void gameLoop() {

        Thread gameLoop = new Thread() {

            public void run() {

                long lastLoopTime = System.nanoTime();
                final int TARGET_FPS = 30;
                final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

                while (true) {
                    long now = System.nanoTime();
                    long updateLength = now - lastLoopTime;
                    lastLoopTime = now;
                    double delta = updateLength / ((double) OPTIMAL_TIME);

                    modele.computeNextStep();

                    try{
                        Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
                   } catch (Exception ex) {
                   };
                }

            }
        };
 
        gameLoop.start();

    }

    public String getInputValue() {
        String s = inputValue.getText();
        return (s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();

        if ("Quitter".equals(c)) {
            quitter();
        } else if ("Ajouter".equals(c)) {
            current = controleur.addTortue(decodeColor(colorList.getSelectedIndex()));
        } else {
            controleur.mouvement(c, inputValue.getText(), current);
        }
    }

    protected Color decodeColor(int c) {
        switch (c) {
            case 0:
                return (Color.black);
            case 1:
                return (Color.blue);
            case 2:
                return (Color.cyan);
            case 3:
                return (Color.darkGray);
            case 4:
                return (Color.red);
            case 5:
                return (Color.green);
            case 6:
                return (Color.lightGray);
            case 7:
                return (Color.magenta);
            case 8:
                return (Color.orange);
            case 9:
                return (Color.gray);
            case 10:
                return (Color.pink);
            case 11:
                return (Color.yellow);
            default:
                return (Color.black);
        }
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(this);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

}
