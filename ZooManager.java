//Aldan St. Omer
//Assignment 2 - COMP2232

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ZooManager extends JFrame implements ActionListener
{
    //Buttons used in Main Frame
    private JButton nextJButton;
    private JButton foodListJButton;
    private JButton foodAddJButton;
    private JButton foodReportJButton;
    private JButton feedJButton;
    private JButton medicineListJButton;
    private JButton medicineAddJButton;
    private JButton medicineReportJButton;
    private JButton healJButton;

    //Labels for contents in panels
    private JLabel[][] aniPanelJL;
    private JLabel[] foodPanelJL;
    private JLabel[] mediPanelJL;

    //Labels for Totals Panels
    private JLabel[][] fTotalsArray;
    private JLabel[][] mTotalsArray;
    private int[][] fTotalsAmt;
    private int[][] mTotalsAmt;

    //Editable Text Fields
    private JTextField hayJTField;
    private JTextField fruitJTField;
    private JTextField grainJTField;
    private JTextField fishJTField;
    private JTextField meatJTField;
    private JTextField herbivoreJTField;
    private JTextField omnivoreJTField;
    private JTextField carnivoreJTField;

    //Image Labels
    private JLabel animalImageJLabel;
    private JLabel welcomeImageJLabel;

    private JScrollPane foodJSPane;
    private JScrollPane medicineJSPane;
    private JTextArea foodJTArea;
    private JTextArea medicineJTArea;

    private static Zoo newZoo;
    private static AnimalFeeder newFeeder;
    private static AnimalHealer newHealer;
    private static ZooKeeper newZooKeeper;
    private int n;

    public static void main(String[] args) throws Exception
    {
        Welcome newWel = new Welcome();

        newZooKeeper = new ZooKeeper();
        newZooKeeper.setNewZoo(newZoo);
        newZooKeeper.setName(newWel.getName());

        ZooManager newZooManager = new ZooManager();
        centerFrame(newZooManager);


        File inFileName = new File("animals.txt");
        Scanner inFile = new Scanner(inFileName);

        while (inFile.hasNext())
        {
            Animal newAni = new Animal();
            newAni.setCageID(inFile.next());
            newAni.setName(inFile.next());
            newAni.setSpecies(inFile.next());
            if (newAni.getSpecies().contains("-"))
            {
                newAni.setSpecies(newAni.getSpecies().replaceAll("-", " "));
            }//endIf
            newAni.setAge(Integer.valueOf(inFile.next()));
            newAni.setHungerStatus(Integer.valueOf(inFile.next()));
            newAni.setHealthStatus(Integer.valueOf(inFile.next()));
            newAni.setCategory(inFile.next());
            newZoo.addAnimal(newAni);
        }//endWhile

        newFeeder = new AnimalFeeder(newZoo.getCages());
        newHealer = new AnimalHealer(newZoo.getCages());

        newZooManager.search();
    }//main

    public ZooManager()
    {
        nextJButton = new JButton("Next");
        foodListJButton = new JButton("Print List");
        foodAddJButton = new JButton("Add");
        foodReportJButton = new JButton("Print Report");
        feedJButton = new JButton("Feed");
        medicineListJButton = new JButton("Print List");
        medicineAddJButton = new JButton("Add");
        medicineReportJButton = new JButton("Print Report");
        healJButton = new JButton("Heal");

        aniPanelJL = new JLabel[6][2];
        foodPanelJL = new JLabel[11];
        mediPanelJL = new JLabel[9];

        fTotalsArray = new JLabel[5][4];
        mTotalsArray = new JLabel[3][4];
        fTotalsAmt = new int[5][4];
        mTotalsAmt = new int[3][4];

        hayJTField = new JTextField("0");
        fruitJTField = new JTextField("0");
        grainJTField = new JTextField("0");
        fishJTField = new JTextField("0");
        meatJTField = new JTextField("0");
        herbivoreJTField = new JTextField("0");
        omnivoreJTField = new JTextField("0");
        carnivoreJTField = new JTextField("0");

        animalImageJLabel = new JLabel();
        welcomeImageJLabel = new JLabel();

        foodJTArea =new JTextArea();
        medicineJTArea =new JTextArea();

        foodJSPane = new JScrollPane(foodJTArea);
        medicineJSPane = new JScrollPane(medicineJTArea);

        newZoo = new Zoo();

        setLayout(null);
        setTitle("Zoo Manager 2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame();
        setSize(1335, 700);
        setResizable(false);
        n= 0;
        setVisible(true);
    }//ZooGUI Constructor

    public void fieldEdit()
    {
        if (newZoo.getAnimal(getN()).getCategory().contains("Herbivore"))
        {
            hayJTField.setEditable(true);
            fruitJTField.setEditable(true);
            grainJTField.setEditable(true);
            herbivoreJTField.setEditable(true);
            System.out.println(newZoo.getAnimal(getN()).getCategory());
        }//endIf

        if (newZoo.getAnimal(getN()).getCategory().contains("Omnivore"))
        {
            hayJTField.setEditable(true);
            fruitJTField.setEditable(true);
            fishJTField.setEditable(true);
            omnivoreJTField.setEditable(true);
            System.out.println(newZoo.getAnimal(getN()).getCategory());
        }//endIf

        if (newZoo.getAnimal(getN()).getCategory().contains("Carnivore"))
        {
            fishJTField.setEditable(true);
            meatJTField.setEditable(true);
            carnivoreJTField.setEditable(true);
            System.out.println(newZoo.getAnimal(getN()).getCategory());
        }//endIf
    }//fieldEdit

    public boolean animalStatusCheck(Animal animal)
    {
        if (animal.getHealthStatus() != animal.getMAX_HEALTH_LVL() || animal.getHungerStatus() != animal.getMAX_FOOD_LVL())
        {
            return true;
        }//endIf
        return false;
    }//animalStatusCheck

    public void animalChange(Animal animal)
    {
        aniPanelJL[0][1].setText(String.valueOf(animal.getCageID()));
        aniPanelJL[1][1].setText(String.valueOf(animal.getName()));
        aniPanelJL[2][1].setText(String.valueOf(animal.getSpecies()));
        aniPanelJL[3][1].setText(String.valueOf(animal.getCategory()));
        aniPanelJL[4][1].setText(String.valueOf(animal.getHungerStatus()));
        if (animal.getHealthStatus() < 8)
        {
            aniPanelJL[5][1].setForeground(Color.RED);
        }//endIf
        else
        {
            aniPanelJL[5][1].setForeground(Color.BLACK);
        }//endElse
        aniPanelJL[5][1].setText(String.valueOf(animal.getHealthStatus()));
    }//animalChange

    public void windowFrame()
    {
        //Border for the Panels
        Border newBorder = BorderFactory.createLineBorder(Color.BLUE);


        //ANIMAL PANEL
        JPanel animalJPanel = new JPanel();
        animalJPanel.setLayout(null);
        TitledBorder titleAni;
        titleAni = BorderFactory.createTitledBorder(newBorder, "Animal");
        titleAni.setTitleJustification(TitledBorder.CENTER);
        animalJPanel.setBorder(titleAni);
        animalJPanel.setBounds(5, 5, 325, 325);

        //Labels' texts being set
        aniPanelJL[0][0] = new JLabel("Cage ID");
        aniPanelJL[1][0] = new JLabel("Name");
        aniPanelJL[2][0] = new JLabel("Species");
        aniPanelJL[3][0] = new JLabel("Category");
        aniPanelJL[4][0] = new JLabel("Hunger");
        aniPanelJL[5][0] = new JLabel("Health");
        aniPanelJL[0][1] = new JLabel("Null");
        aniPanelJL[1][1] = new JLabel("Null");
        aniPanelJL[2][1] = new JLabel("Null");
        aniPanelJL[3][1] = new JLabel("Null");
        aniPanelJL[4][1] = new JLabel("Null");
        aniPanelJL[5][1] = new JLabel("Null");

        //labels' positions being set
        aniPanelJL[0][0].setBounds(50, 30, 70, 30);
        aniPanelJL[1][0].setBounds(50, 60, 70, 30);
        aniPanelJL[2][0].setBounds(50, 90, 70, 30);
        aniPanelJL[3][0].setBounds(50, 120, 70, 30);
        aniPanelJL[4][0].setBounds(50, 150, 70, 30);
        aniPanelJL[5][0].setBounds(50, 180, 70, 30);
        aniPanelJL[0][1].setBounds(180, 30, 130, 30);
        aniPanelJL[1][1].setBounds(180, 60, 130, 30);
        aniPanelJL[2][1].setBounds(180, 90, 130, 30);
        aniPanelJL[3][1].setBounds(180, 120, 130, 30);
        aniPanelJL[4][1].setBounds(180, 150, 130, 30);
        aniPanelJL[5][1].setBounds(180, 180, 130, 30);

        //Adding label components to Animal Panel
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                animalJPanel.add(aniPanelJL[i][j]);
            }//endFor
        }//endFor

        //Icon for zone being set and added to Animal Panel
        animalImageJLabel.setBounds(50, 250, 50, 50);
        animalJPanel.add(animalImageJLabel);

        //Next button being set and added to Animal Panel
        nextJButton.setBounds(150, 250, 100, 50);
        nextJButton.setEnabled(false);
        nextJButton.addActionListener(this);
        animalJPanel.add(nextJButton);



        //FOOD_TOTALS PANEL
        JPanel foodTotalsJPanel = new JPanel();
        foodTotalsJPanel.setLayout(null);
        foodTotalsJPanel.setBounds(335, 5, 650, 325);

        //Food Panel (Being placed inside FoodTotals Panel)
        JPanel foodJPanel = new JPanel();
        foodJPanel.setLayout(null);
        foodJPanel.setBounds(0, 0, 295, 325);
        TitledBorder titleFood;
        titleFood = BorderFactory.createTitledBorder(newBorder, "Food");
        titleFood.setTitleJustification(TitledBorder.CENTER);
        foodJPanel.setBorder(titleFood);

        //FTotals Panel (Being placed inside FoodTotals Panel)
        JPanel fTotalsJPanel = new JPanel();
        fTotalsJPanel.setLayout(null);
        fTotalsJPanel.setBounds(355, 0, 295, 325);
        TitledBorder titleFTtl;
        titleFTtl = BorderFactory.createTitledBorder(newBorder, "Totals");
        titleFTtl.setTitleJustification(TitledBorder.CENTER);
        fTotalsJPanel.setBorder(titleFTtl);


        //Labels' texts being set
        foodPanelJL[0] = new JLabel("Type");
        foodPanelJL[1] = new JLabel("Hay");
        foodPanelJL[2] = new JLabel("Fruit");
        foodPanelJL[3] = new JLabel("Grain");
        foodPanelJL[4] = new JLabel("Fish");
        foodPanelJL[5] = new JLabel("Meat");
        foodPanelJL[6] = new JLabel("Amount");
        foodPanelJL[7] = new JLabel("A");
        foodPanelJL[8] = new JLabel("B");
        foodPanelJL[9] = new JLabel("C");
        foodPanelJL[10] = new JLabel("D");

        //Labels' positions being set
        foodPanelJL[0].setBounds(50, 30, 70, 30);
        foodPanelJL[1].setBounds(50, 60, 70, 30);
        foodPanelJL[2].setBounds(50, 90, 70, 30);
        foodPanelJL[3].setBounds(50, 120, 70, 30);
        foodPanelJL[4].setBounds(50, 150, 70, 30);
        foodPanelJL[5].setBounds(50, 180, 70, 30);
        foodPanelJL[6].setBounds(180, 30, 70, 30);
        foodPanelJL[7].setBounds(50, 30, 70, 30);
        foodPanelJL[8].setBounds(110, 30, 70, 30);
        foodPanelJL[9].setBounds(170, 30, 70, 30);
        foodPanelJL[10].setBounds(235, 30, 70, 30);

        //Text fields positions and editable status being set
        hayJTField.setBounds(180, 60, 70, 30);
        hayJTField.setEditable(false);
        fruitJTField.setBounds(180, 90, 70, 30);
        fruitJTField.setEditable(false);
        grainJTField.setBounds(180, 120, 70, 30);
        grainJTField.setEditable(false);
        fishJTField.setBounds(180, 150, 70, 30);
        fishJTField.setEditable(false);
        meatJTField.setBounds(180, 180, 70, 30);
        meatJTField.setEditable(false);

        //Key Listeners added to text fields so only numbers can be inputs
        hayJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();

                if (((c<'0')||(c>'9'))&&((c != KeyEvent.VK_BACK_SPACE)||(c != KeyEvent.VK_DELETE)))
                {
                    e.consume();

                }//endIf
            }//keyTyped
            @Override
            public void keyReleased(KeyEvent e) {
                if ((hayJTField.getText().isBlank() == false) && Integer.parseInt(hayJTField.getText()) > 0) {
                    fruitJTField.setEditable(false);
                    grainJTField.setEditable(false);
                    fishJTField.setEditable(false);
                    meatJTField.setEditable(false);
                }//endIf
            }//keyReleased
        });//addKeyListener
        fruitJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
            @Override
            public void keyReleased(KeyEvent e) {
                if ((hayJTField.getText().isBlank() == false) && Integer.parseInt(fruitJTField.getText()) > 0)
                {
                    hayJTField.setEditable(false);
                    grainJTField.setEditable(false);
                    fishJTField.setEditable(false);
                    meatJTField.setEditable(false);
                }//endIf
            }//keyReleased
        });//addKeyListener
        grainJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
            @Override
            public void keyReleased(KeyEvent e) {
                if ((hayJTField.getText().isBlank() == false) && Integer.parseInt(grainJTField.getText()) > 0)
                {
                    hayJTField.setEditable(false);
                    fruitJTField.setEditable(false);
                    fishJTField.setEditable(false);
                    meatJTField.setEditable(false);
                }//endIf
            }//keyReleased
        });//addKeyListener
        fishJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
            @Override
            public void keyReleased(KeyEvent e) {
                if ((hayJTField.getText().isBlank() == false) && Integer.parseInt(fishJTField.getText()) > 0)
                {
                    hayJTField.setEditable(false);
                    fruitJTField.setEditable(false);
                    grainJTField.setEditable(false);
                    meatJTField.setEditable(false);
                }//endIf
            }//keyReleased
        });//addKeyListener
        meatJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
            @Override
            public void keyReleased(KeyEvent e) {
                if ((hayJTField.getText().isBlank() == false) && Integer.parseInt(meatJTField.getText()) > 0)
                {
                    hayJTField.setEditable(false);
                    fruitJTField.setEditable(false);
                    grainJTField.setEditable(false);
                    fishJTField.setEditable(false);
                }//endIf
            }//keyReleased
        });//addKeyListener

        //Panel with grid layout placed in FTotals Panel displaying the totals being added
        JPanel fArrayPanel = new JPanel();
        fArrayPanel.setLayout(new GridLayout(5,4));
        fArrayPanel.setBounds(25, 60, 245, 150);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                fTotalsArray[i][j] =new JLabel(String.valueOf(fTotalsAmt[i][j]), SwingConstants.CENTER);

                fArrayPanel.add(fTotalsArray[i][j]);
            }//endFor
        }//endFor
        fTotalsJPanel.add(fArrayPanel);

        //Add, Feed and Print List buttons for Food and FTotals being positioned and editable status being set
        foodAddJButton.setBounds(295, 140, 60, 30);
        foodAddJButton.addActionListener(this);
        feedJButton.setBounds(100, 250, 100, 50);
        feedJButton.setEnabled(false);
        feedJButton.addActionListener(this);
        foodListJButton.setBounds(100, 250, 100, 50);
        foodListJButton.setEnabled(false);
        foodListJButton.addActionListener(this);

        //Adding Label components to Food Panel
        foodJPanel.add(foodPanelJL[0]);
        foodJPanel.add(foodPanelJL[1]);
        foodJPanel.add(foodPanelJL[2]);
        foodJPanel.add(foodPanelJL[3]);
        foodJPanel.add(foodPanelJL[4]);
        foodJPanel.add(foodPanelJL[5]);
        foodJPanel.add(foodPanelJL[6]);

        //Adding Text field components to Food Panel
        foodJPanel.add(hayJTField);
        foodJPanel.add(fruitJTField);
        foodJPanel.add(grainJTField);
        foodJPanel.add(fishJTField);
        foodJPanel.add(meatJTField);
        foodJPanel.add(foodListJButton);

        //Adding Label components to FTotals Panel
        fTotalsJPanel.add(foodPanelJL[7]);
        fTotalsJPanel.add(foodPanelJL[8]);
        fTotalsJPanel.add(foodPanelJL[9]);
        fTotalsJPanel.add(foodPanelJL[10]);
        fTotalsJPanel.add(feedJButton);



        //FEEDING REPORT PANEL
        JPanel feedingReportJPanel = new JPanel();
        feedingReportJPanel.setLayout(null);
        feedingReportJPanel.setBounds(990, 5, 325, 325);
        TitledBorder titleFeedReport;
        titleFeedReport = BorderFactory.createTitledBorder(newBorder, "Feeding Report");
        titleFeedReport.setTitleJustification(TitledBorder.CENTER);
        feedingReportJPanel.setBorder(titleFeedReport);

        //Scrollable Pane and Text Area setup
        foodJSPane.setSize(200, 300);
        foodJSPane.setBounds(50,30, 225, 200);
        foodJSPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        foodJSPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        foodJTArea.setEditable(false);

        //Report button setup
        foodReportJButton.setBounds(87, 250, 150, 50);
        foodReportJButton.setEnabled(false);
        foodReportJButton.addActionListener(this);



        //WELCOME-PANEL
        JPanel welcomeJPanel = new JPanel();
        welcomeJPanel.setLayout(null);
        welcomeJPanel.setBounds(5, 335, 325, 325);
        TitledBorder titleWelcome;
        titleWelcome = BorderFactory.createTitledBorder(newBorder, "Welcome "+ newZooKeeper.getName());
        titleWelcome.setTitleJustification(TitledBorder.CENTER);
        welcomeJPanel.setBorder(titleWelcome);

        JLabel welcome1JLabel = new JLabel("Welcome Zoo Keeper to");
        JLabel welcome2JLabel = new JLabel("The Cave Hill Zoo!");
        JLabel welcome3JLabel = new JLabel("Help Us to Feed and Heal our Animals");

        welcome1JLabel.setBounds(95, 30, 230, 30);
        welcome2JLabel.setBounds(110, 60, 230, 30);
        welcome3JLabel.setBounds(60, 90, 230, 30);

        //Icon for zone being set and added to Animal Panel
        ImageIcon iconLogo = new ImageIcon("zooLogo.png");
        iconLogo.setDescription("zoo logo icon");
        Image scaleImageLogo = iconLogo.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
        iconLogo =new ImageIcon(scaleImageLogo);
        welcomeImageJLabel.setIcon(iconLogo);
        welcomeImageJLabel.setBounds(115, 110, 100, 190);

        welcomeJPanel.add(welcome1JLabel);
        welcomeJPanel.add(welcome2JLabel);
        welcomeJPanel.add(welcome3JLabel);
        welcomeJPanel.add(welcomeImageJLabel);



        //MEDICINE_TOTALS PANEL
        JPanel medicineTotalsJPanel = new JPanel();
        medicineTotalsJPanel.setLayout(null);
        medicineTotalsJPanel.setBounds(335, 335, 650, 325);

        //Medicine Panel (Being placed inside MedicineTotals Panel)
        JPanel medicineJPanel = new JPanel();
        medicineJPanel.setLayout(null);
        medicineJPanel.setBounds(0, 0, 295, 325);
        TitledBorder titleMedicine;
        titleMedicine = BorderFactory.createTitledBorder(newBorder, "Medicine");
        titleMedicine.setTitleJustification(TitledBorder.CENTER);
        medicineJPanel.setBorder(titleMedicine);

        //MTotals Panel (Being placed inside MedicineTotals Panel)
        JPanel mTotalsJPanel = new JPanel();
        mTotalsJPanel.setLayout(null);
        mTotalsJPanel.setBounds(355, 0, 295, 325);
        TitledBorder titleMTtl;
        titleMTtl = BorderFactory.createTitledBorder(newBorder, "Totals");
        titleMTtl.setTitleJustification(TitledBorder.CENTER);
        mTotalsJPanel.setBorder(titleMTtl);

        //Labels' texts being set
        mediPanelJL[0] = new JLabel("Type");
        mediPanelJL[1] = new JLabel("Herbivore");
        mediPanelJL[2] = new JLabel("Omnivore");
        mediPanelJL[3] = new JLabel("Carnivore");
        mediPanelJL[4] = new JLabel("Amount");
        mediPanelJL[5] = new JLabel("A");
        mediPanelJL[6] = new JLabel("B");
        mediPanelJL[7] = new JLabel("C");
        mediPanelJL[8] = new JLabel("D");

        //Labels' positions being set
        mediPanelJL[0].setBounds(50, 30, 70, 30);
        mediPanelJL[1].setBounds(50, 60, 70, 30);
        mediPanelJL[2].setBounds(50, 90, 70, 30);
        mediPanelJL[3].setBounds(50, 120, 70, 30);
        mediPanelJL[4].setBounds(180, 30, 70, 30);
        mediPanelJL[5].setBounds(50, 30, 70, 30);
        mediPanelJL[6].setBounds(110, 30, 70, 30);
        mediPanelJL[7].setBounds(170, 30, 70, 30);
        mediPanelJL[8].setBounds(235, 30, 70, 30);

        //Text fields positions and editable status being set
        herbivoreJTField.setBounds(180, 60, 70, 30);
        herbivoreJTField.setEditable(false);
        omnivoreJTField.setBounds(180, 90, 70, 30);
        omnivoreJTField.setEditable(false);
        carnivoreJTField.setBounds(180, 120, 70, 30);
        carnivoreJTField.setEditable(false);

        //Key Listeners added to text fields so only numbers can be inputs
        herbivoreJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
        });//addKeyListener
        omnivoreJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
        });//addKeyListener
        carnivoreJTField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c<'0')||(c>'9'))&&(c != KeyEvent.VK_BACK_SPACE))
                {
                    e.consume();
                }//endIf
            }//keyTyped
        });//addKeyListener

        //Panel with grid layout placed in FTotals Panel displaying the totals being added
        JPanel mArrayPanel = new JPanel();
        mArrayPanel.setLayout(new GridLayout(3,4));
        mArrayPanel.setBounds(25, 60, 245, 90);
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                mTotalsArray[i][j] =new JLabel(String.valueOf(mTotalsAmt[i][j]), SwingConstants.CENTER);

                mArrayPanel.add(mTotalsArray[i][j]);
            }//endFor
        }//endFor
        mTotalsJPanel.add(mArrayPanel);

        //Add, Heal and Print List buttons for Medicine and MTotals being positioned and editable status being set
        medicineAddJButton.setBounds(295, 140, 60, 30);
        medicineAddJButton.addActionListener(this);
        healJButton.setBounds(100, 250, 100, 50);
        healJButton.setEnabled(false);
        healJButton.addActionListener(this);
        medicineListJButton.setBounds(100, 250, 100, 50);
        medicineListJButton.setEnabled(false);
        medicineListJButton.addActionListener(this);

        //Adding Label components to Medicine Panel
        medicineJPanel.add(mediPanelJL[0]);
        medicineJPanel.add(mediPanelJL[1]);
        medicineJPanel.add(mediPanelJL[2]);
        medicineJPanel.add(mediPanelJL[3]);
        medicineJPanel.add(mediPanelJL[4]);

        //Adding Text field components to Medicine Panel
        medicineJPanel.add(herbivoreJTField);
        medicineJPanel.add(omnivoreJTField);
        medicineJPanel.add(carnivoreJTField);
        medicineJPanel.add(medicineListJButton);

        //Adding Label components to MTotals Panel
        mTotalsJPanel.add(mediPanelJL[5]);
        mTotalsJPanel.add(mediPanelJL[6]);
        mTotalsJPanel.add(mediPanelJL[7]);
        mTotalsJPanel.add(mediPanelJL[8]);
        mTotalsJPanel.add(healJButton);



        //HEALING REPORT PANEL
        JPanel healingReportJPanel = new JPanel();
        healingReportJPanel.setLayout(null);
        healingReportJPanel.setBounds(990, 335, 325, 325);
        TitledBorder titleHealReport;
        titleHealReport = BorderFactory.createTitledBorder(newBorder, "Healing Report");
        titleHealReport.setTitleJustification(TitledBorder.CENTER);
        healingReportJPanel.setBorder(titleHealReport);

        //Scrollable Pane and Text Area setup
        medicineJSPane.setSize(200, 300);
        medicineJSPane.setBounds(50,30, 225, 200);
        medicineJSPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        medicineJSPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        medicineJTArea.setEditable(false);

        //Report button setup
        medicineReportJButton.setBounds(87, 250, 150, 50);
        medicineReportJButton.setEnabled(false);
        medicineReportJButton.addActionListener(this);



        //Components to be added to FoodTotals Panel
        foodTotalsJPanel.add(foodJPanel);
        foodTotalsJPanel.add(fTotalsJPanel);
        foodTotalsJPanel.add(foodAddJButton);

        //Components to be added to MedicineTotals Panel
        medicineTotalsJPanel.add(medicineJPanel);
        medicineTotalsJPanel.add(mTotalsJPanel);
        medicineTotalsJPanel.add(medicineAddJButton);

        //Components to be added to FeedingReport Panel
        feedingReportJPanel.add(foodReportJButton);
        feedingReportJPanel.add(foodJSPane);

        //Components to be added to HealingReport Panel
        healingReportJPanel.add(medicineReportJButton);
        healingReportJPanel.add(medicineJSPane);



        //Main Panels to be added to the Window Frame
        add(animalJPanel);
        add(foodTotalsJPanel);
        add(feedingReportJPanel);
        add(welcomeJPanel);
        add(medicineTotalsJPanel);
        add(healingReportJPanel);
    }//windowFrame

    private static void centerFrame(ZooManager newZooM)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = newZooM.getSize().width;
        int h = newZooM.getSize().height;
        int x = (dim.width -w) / 2;
        int y = (dim.height -h) /2;

        newZooM.setLocation(x,y);
    }//centerFrame

    public void search()
    {
        if (getN() == newZoo.getCageSize())
        {
            setN(0);
            return;
        }//endIf

        if (getN() ==0)
        {
            if(animalStatusCheck(newZoo.getAnimal(getN())) == true)
            {
                animalChange(newZoo.getAnimal(getN()));
                if (newZoo.getAnimal(getN()).getCageID().contains("A"))
                {
                    ImageIcon icon = new ImageIcon("iconZoneA-savannah.png");
                    icon.setDescription("savannah icons");
                    Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                    icon =new ImageIcon(scaleImage);
                    animalImageJLabel.setIcon(icon);
                }//endIf
                if (newZoo.getAnimal(getN()).getCageID().contains("B"))
                {
                    ImageIcon icon = new ImageIcon("iconZoneB-amazonia.png");
                    icon.setDescription("jungle icons");
                    Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                    icon =new ImageIcon(scaleImage);
                    animalImageJLabel.setIcon(icon);
                }//endIf
                if (newZoo.getAnimal(getN()).getCageID().contains("C"))
                {
                    ImageIcon icon = new ImageIcon("iconZoneC-eurasia.png");
                    icon.setDescription("desert icons");
                    Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                    icon =new ImageIcon(scaleImage);
                    animalImageJLabel.setIcon(icon);
                }//endIf
                if (newZoo.getAnimal(getN()).getCageID().contains("D"))
                {
                    ImageIcon icon = new ImageIcon("iconZoneD-tundra.png");
                    icon.setDescription("tundra icons");
                    Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                    icon =new ImageIcon(scaleImage);
                    animalImageJLabel.setIcon(icon);
                }//endIf
            }
            else
            {
                setN(getN()+1);
            }//endElse
        }//endIf

        if(getN() >0 && getN() < newZoo.getCageSize())
        {
            while (animalStatusCheck(newZoo.getAnimal(getN())) == false)
            {
                setN(getN()+1);
            }//endWhile
            animalChange(newZoo.getAnimal(getN()));
            if (newZoo.getAnimal(getN()).getCageID().contains("A"))
            {
                ImageIcon icon = new ImageIcon("iconZoneA-savannah.png");
                icon.setDescription("savannah icons");
                Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                icon =new ImageIcon(scaleImage);
                animalImageJLabel.setIcon(icon);
            }//endIf
            if (newZoo.getAnimal(getN()).getCageID().contains("B"))
            {
                ImageIcon icon = new ImageIcon("iconZoneB-amazonia.png");
                icon.setDescription("jungle icons");
                Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                icon =new ImageIcon(scaleImage);
                animalImageJLabel.setIcon(icon);
            }//endIf
            if (newZoo.getAnimal(getN()).getCageID().contains("C"))
            {
                ImageIcon icon = new ImageIcon("iconZoneC-eurasia.png");
                icon.setDescription("desert icons");
                Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                icon =new ImageIcon(scaleImage);
                animalImageJLabel.setIcon(icon);
            }//endIf
            if (newZoo.getAnimal(getN()).getCageID().contains("D"))
            {
                ImageIcon icon = new ImageIcon("iconZoneD-tundra.png");
                icon.setDescription("tundra icons");
                Image scaleImage = icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
                icon =new ImageIcon(scaleImage);
                animalImageJLabel.setIcon(icon);
            }//endIf
        }//endIf

        fieldEdit();
        setN(getN()+1);
    }//search

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == nextJButton)
        {
            search();
            System.out.println(getN());
            foodAddJButton.setEnabled(true);
            medicineAddJButton.setEnabled(true);
            nextJButton.setEnabled(false);
        }//endIf

        if (e.getSource() == foodAddJButton)
        {
                String hayAmt, fruitAmt, grainAmt, fishAmt, meatAmt;
                int hayInt, fruitInt, grainInt, fishInt, meatInt, z;

                hayAmt = hayJTField.getText();
                hayInt = Integer.parseInt(hayAmt);
                hayJTField.setEditable(false);
                hayJTField.setText("0");
                fruitAmt = fruitJTField.getText();
                fruitInt = Integer.parseInt(fruitAmt);
                fruitJTField.setEditable(false);
                fruitJTField.setText("0");
                grainAmt = grainJTField.getText();
                grainInt = Integer.parseInt(grainAmt);
                grainJTField.setEditable(false);
                grainJTField.setText("0");
                fishAmt = fishJTField.getText();
                fishInt = Integer.parseInt(fishAmt);
                fishJTField.setEditable(false);
                fishJTField.setText("0");
                meatAmt = meatJTField.getText();
                meatInt = Integer.parseInt(meatAmt);
                meatJTField.setEditable(false);
                meatJTField.setText("0");
                if (hayInt > 0)
                {
                    z = 0;
                    containsMealFunc(hayInt, z);
                    newFeeder.setId(newZoo.getAnimal(getN() - 1).getCageID());
                    newFeeder.setType("Hay");
                    newFeeder.setAmt(hayInt + fruitInt + grainInt + fishInt + meatInt);
                    newFeeder.addMeal();

                }//endIf
                if (fruitInt > 0)
                {
                    z = 1;
                    containsMealFunc(fruitInt, z);
                    newFeeder.setId(newZoo.getAnimal(getN() - 1).getCageID());
                    newFeeder.setType("Fruit");
                    newFeeder.setAmt(hayInt + fruitInt + grainInt + fishInt + meatInt);
                    newFeeder.addMeal();
                }//endIf
                if (grainInt > 0)
                {
                    z = 2;
                    containsMealFunc(grainInt, z);
                    newFeeder.setId(newZoo.getAnimal(getN() - 1).getCageID());
                    newFeeder.setType("Grain");
                    newFeeder.setAmt(hayInt + fruitInt + grainInt + fishInt + meatInt);
                    newFeeder.addMeal();
                }//endIf
                if (fishInt > 0)
                {
                    z = 3;
                    containsMealFunc(fishInt, z);
                    newFeeder.setId(newZoo.getAnimal(getN() - 1).getCageID());
                    newFeeder.setType("Fish");
                    newFeeder.setAmt(hayInt + fruitInt + grainInt + fishInt + meatInt);
                    newFeeder.addMeal();
                }//endIf
                if (meatInt > 0)
                {
                    z = 4;
                    containsMealFunc(meatInt, z);
                    newFeeder.setId(newZoo.getAnimal(getN() - 1).getCageID());
                    newFeeder.setType("Meat");
                    newFeeder.setAmt(hayInt + fruitInt + grainInt + fishInt + meatInt);
                    newFeeder.addMeal();
                }//endIf

                for (int i = 0; i < 5; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        fTotalsArray[i][j].setText(String.valueOf(fTotalsAmt[i][j]));
                    }//endFor
                }//endFor
                if(getN()== newZoo.getCageSize())
                {
                    foodAddJButton.setEnabled(false);
                    nextJButton.setEnabled(false);
                    feedJButton.setEnabled(false);
                    foodListJButton.setEnabled(true);

                    if(!medicineAddJButton.isEnabled())
                    {
                        setN(0);
                    }//endIf
                }//endIf
                else
                {
                    foodAddJButton.setEnabled(false);
                    if (!medicineAddJButton.isEnabled())
                    {
                        nextJButton.setEnabled(true);
                    }//endIf
                }//endElse
        }//endIf

        if (e.getSource() == feedJButton)
        {
            try
            {
                newFeeder.simFeeding();
            }//endTry
            catch (OverFeedingException f)
            {
                System.out.println("Animal has died");
            }//endCatch
            //newFeeder.reportFunc(foodJTArea);
            newZoo.setNewAF(newFeeder);
            newZoo.setNewFeedJTA(foodJTArea);
            newZoo.printHungerReport();
            nextJButton.setEnabled(false);
            feedJButton.setEnabled(false);
            foodReportJButton.setEnabled(true);
        }//endIf

        if(e.getSource() == foodListJButton)
        {
            try
            {
                newFeeder.printFeedingList();
            }//endTry
            catch (IOException f)
            {
            }//endCatch
            feedJButton.setEnabled(true);
            foodListJButton.setEnabled(false);
        }//endIf

        if (e.getSource() == foodReportJButton)
        {
            try
            {
                newFeeder.printFeedingReport();
            }//endTry
            catch (IOException f)
            {
            }//endCatch
            foodReportJButton.setEnabled(false);
        }//endIf

        if (e.getSource() == medicineAddJButton)
        {
            String herbivoreAmt, omnivoreAmt, carnivoreAmt;
            int herbivoreInt, omnivoreInt, carnivoreInt, z;

            herbivoreAmt = herbivoreJTField.getText();
            herbivoreInt = Integer.parseInt(herbivoreAmt);
            herbivoreJTField.setEditable(false);
            herbivoreJTField.setText("0");
            omnivoreAmt = omnivoreJTField.getText();
            omnivoreInt = Integer.parseInt(omnivoreAmt);
            omnivoreJTField.setEditable(false);
            omnivoreJTField.setText("0");
            carnivoreAmt = carnivoreJTField.getText();
            carnivoreInt = Integer.parseInt(carnivoreAmt);
            carnivoreJTField.setEditable(false);
            carnivoreJTField.setText("0");
            if (herbivoreInt > 0)
            {
                z = 0;
                containsPrescFunc(herbivoreInt, z);
                newHealer.setId(newZoo.getAnimal(getN() - 1).getCageID());
                newHealer.setType("Herbivore");
                newHealer.setAmt(herbivoreInt +omnivoreInt + carnivoreInt);
                newHealer.addPrescription();
            }//endIf
            if (omnivoreInt > 0)
            {
                z = 1;
                containsPrescFunc(omnivoreInt, z);
                newHealer.setId(newZoo.getAnimal(getN() - 1).getCageID());
                newHealer.setType("Omnivore");
                newHealer.setAmt(herbivoreInt +omnivoreInt + carnivoreInt);
                newHealer.addPrescription();
            }//endIf
            if (carnivoreInt > 0)
            {
                z = 2;
                containsPrescFunc(carnivoreInt, z);
                newHealer.setId(newZoo.getAnimal(getN() - 1).getCageID());
                newHealer.setType("Carnivore");
                newHealer.setAmt(herbivoreInt +omnivoreInt + carnivoreInt);
                newHealer.addPrescription();
            }//endIf

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    mTotalsArray[i][j].setText(String.valueOf(mTotalsAmt[i][j]));
                }//endFor
            }//endFor
            if(getN()== newZoo.getCageSize())
            {
                medicineAddJButton.setEnabled(false);
                nextJButton.setEnabled(false);
                healJButton.setEnabled(false);
                medicineListJButton.setEnabled(true);
                if (foodAddJButton.isEnabled() == false)
                {
                    setN(0);
                }//endIf
            }//endIf
            else
            {
                medicineAddJButton.setEnabled(false);
                if (foodAddJButton.isEnabled() == false)
                {
                    nextJButton.setEnabled(true);
                }//endIf
            }//endElse
        }//endIf

        if (e.getSource() == healJButton)
        {
            try
            {
                newHealer.simHealing();
            }//endTry
            catch (OverdosingException f)
            {
                System.out.println("Animal has died");
            }//endCatch
            newZoo.setNewAH(newHealer);
            newZoo.setNewHealJTA(medicineJTArea);
            newZoo.printHealthReport();
            nextJButton.setEnabled(false);
            healJButton.setEnabled(false);
            medicineReportJButton.setEnabled(true);
        }//endIf

        if(e.getSource() == medicineListJButton)
        {
            try
            {
                newHealer.printHealingList();
            }//endTry
            catch (Exception f)
            {
            }//endCatch
            medicineListJButton.setEnabled(false);
            healJButton.setEnabled(true);
        }//endIf

        if (e.getSource() == medicineReportJButton)
        {
            try
            {
                newHealer.printHealingReport();
            }//endTry
            catch (IOException f)
            {
            }//endCatch
            medicineReportJButton.setEnabled(false);
        }//endIf
    }//actionPerformed

    public void containsMealFunc(int a, int z)
    {
        if (newZoo.getAnimal(getN()-1).getCageID().contains("A"))
        {
            fTotalsAmt[z][0]=fTotalsAmt[z][0]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("B"))
        {
            fTotalsAmt[z][1]=fTotalsAmt[z][1]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("C"))
        {
            fTotalsAmt[z][2]=fTotalsAmt[z][2]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("D"))
        {
            fTotalsAmt[z][3]=fTotalsAmt[z][3]+a;
        }//endIf
    }//containsMealFunc

    public void containsPrescFunc(int a, int z)
    {
        if (newZoo.getAnimal(getN()-1).getCageID().contains("A"))
        {
            mTotalsAmt[z][0]=mTotalsAmt[z][0]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("B"))
        {
            mTotalsAmt[z][1]=mTotalsAmt[z][1]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("C"))
        {
            mTotalsAmt[z][2]=mTotalsAmt[z][2]+a;
        }//endIf
        if (newZoo.getAnimal(getN()-1).getCageID().contains("D"))
        {
            mTotalsAmt[z][3]=mTotalsAmt[z][3]+a;
        }//endIf
    }//containsPrescFunc

    public int getN()
    {
        return n;
    }//getN

    public void setN(int n)
    {
        this.n = n;
    }//setN
}//ZooManager
