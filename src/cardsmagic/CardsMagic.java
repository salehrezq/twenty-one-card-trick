/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsmagic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author S
 */
public class CardsMagic extends JPanel
{

    static JFrame frame;
    Timer timer_1;
    Timer timer_2;
    Timer timer_3;
    static final int timerTime = 70;
    int step_counter = 2;
    ////////
    DLabel card_1;
    DLabel card_2;
    DLabel card_3;
    DLabel card_4;
    DLabel card_5;
    DLabel card_6;
    DLabel card_7;
    DLabel card_8;
    DLabel card_9;
    DLabel card_10;
    DLabel card_11;
    DLabel card_12;
    DLabel card_13;
    DLabel card_14;
    DLabel card_15;
    DLabel card_16;
    DLabel card_17;
    DLabel card_18;
    DLabel card_19;
    DLabel card_20;
    DLabel card_21;
    ///////////
    JTextPane infoTextPanel = new JTextPane();
    /////////////
    JLabel[] cardsArray_One;
    JLabel[] cardsArray_Two;
    JLabel[] cardsArray_Three;
    //
    String[] cardsFirstCollect_Chosen;
    String[] cardsFirstCollect_Left_1;
    String[] cardsFirstCollect_Left_2;
    //
    static Class listenerReflect = ActionListener.class;
    Method actionMethod;
    Class magicClassReflect = CardsMagic.class;
    DButton button;
    Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    Font noteFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    HashMap<JLabel, String> LabelsToValuesMap;
    //
    DButton exitButton;
    //
    ButtonGroup chooseColumn;
    JRadioButton leftColumnRadio;
    JRadioButton midColumnRadio;
    JRadioButton rightColumnRadio;
    /////////////
    boolean readyForNext = false;
    /////////////
    Timer moveTimer;
    DLabel mindReadNumberCard;
    Point p;
    Dimension d;
    int widthIcrement = 10;
    int heightIncrement = 10;
    int fontIncrement = 1;
    int fontSize = 6;
    Rectangle rect;

    public CardsMagic()
    {
        this.setLayout(null);

        cardsArray_One = new DLabel[7];
        cardsArray_Two = new DLabel[7];
        cardsArray_Three = new DLabel[7];
        //
        cardsFirstCollect_Chosen = new String[7];
        cardsFirstCollect_Left_1 = new String[7];
        cardsFirstCollect_Left_2 = new String[7];
        //
        LabelsToValuesMap = new HashMap();
        //
        button = new DButton("Start");
        button.setBounds(185, 600, 90, 40);
        button.addActionListener(new EventWatcher());
        button.setFont(buttonFont);
        button.setAlignmentX(50);
        button.setAlignmentY(100);
        button.setActionCommand("Start");
        this.add(button);

        exitButton = new DButton("Exit");
        exitButton.setActionCommand("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setBounds(320, 600, 75, 40);
        exitButton.addActionListener(new EventWatcher());
        this.add(exitButton);


        //
        infoTextPanel.setBounds(380, 100, 200, 500);
        infoTextPanel.setFont(noteFont);
        infoTextPanel.setEditable(false);
        //   infoTextPanel.setEditable(false);
        /////////
        card_1 = new DLabel();
        card_2 = new DLabel();
        card_3 = new DLabel();
        card_4 = new DLabel();
        card_5 = new DLabel();
        card_6 = new DLabel();
        card_7 = new DLabel();
        card_8 = new DLabel();
        card_9 = new DLabel();
        card_10 = new DLabel();
        card_11 = new DLabel();
        card_12 = new DLabel();
        card_13 = new DLabel();
        card_14 = new DLabel();
        card_15 = new DLabel();
        card_16 = new DLabel();
        card_17 = new DLabel();
        card_18 = new DLabel();
        card_19 = new DLabel();
        card_20 = new DLabel();
        card_21 = new DLabel();
        //
        //
        addLabelsToArray_One();
        addLabelsToArray_Two();
        addLabelsToArray_Three();
        //
        chooseColumn = new ButtonGroup();
        leftColumnRadio = new JRadioButton("French", false);
        midColumnRadio = new JRadioButton("German", false);
        rightColumnRadio = new JRadioButton("English", false);

        leftColumnRadio.setActionCommand("leftColumn");
        midColumnRadio.setActionCommand("midColumn");
        rightColumnRadio.setActionCommand("rightColumn");

        chooseColumn.add(leftColumnRadio);
        chooseColumn.add(midColumnRadio);
        chooseColumn.add(rightColumnRadio);

        leftColumnRadio.addActionListener(new EventWatcher());
        midColumnRadio.addActionListener(new EventWatcher());
        rightColumnRadio.addActionListener(new EventWatcher());

        leftColumnRadio.setBounds(133, 570, 20, 20);
        midColumnRadio.setBounds(222, 570, 20, 20);
        rightColumnRadio.setBounds(310, 570, 20, 20);
        ////////

        mindReadNumberCard = new DLabel();
        mindReadNumberCard.setFont(new Font(Font.SANS_SERIF, 12, fontSize));
        p = new Point(295, 280);
        d = new Dimension(10, 11);
        rect = new Rectangle(p, d);

    }

    public void assignNumbersRandomlyToCards()
    {
        Random rand = new Random(21); // Ideally just create one instance globally
        List<Integer> generated = new ArrayList<Integer>();
        for (int i = 0; i < 21; i++)
        {
            while (true)
            {
                Integer next = rand.nextInt(21) + 1;
                if (!generated.contains(next))
                {
                    // Done for this iteration
                    generated.add(next);
                    break;
                }
            }
        }
        //
        Collections.shuffle(generated);
        Collections.shuffle(generated);
        //
        for (int i = 0; i < cardsArray_One.length; i++)
        {
            cardsArray_One[i].setText(String.valueOf(generated.get(i)));
            LabelsToValuesMap.put(cardsArray_One[i], cardsArray_One[i].getText());
        }
        for (int i = 0; i < cardsArray_Two.length; i++)
        {
            cardsArray_Two[i].setText(String.valueOf(generated.get(i + 7)));
            LabelsToValuesMap.put(cardsArray_Two[i], cardsArray_Two[i].getText());
        }
        for (int i = 0; i < cardsArray_Three.length; i++)
        {
            cardsArray_Three[i].setText(String.valueOf(generated.get(i + 14)));
            LabelsToValuesMap.put(cardsArray_Three[i], cardsArray_Three[i].getText());
        }
    }

    public void addLabelsToArray_One()
    {
        cardsArray_One[0] = card_1;
        cardsArray_One[1] = card_2;
        cardsArray_One[2] = card_3;
        cardsArray_One[3] = card_4;
        cardsArray_One[4] = card_5;
        cardsArray_One[5] = card_6;
        cardsArray_One[6] = card_7;
    }

    public void addLabelsToArray_Two()
    {
        cardsArray_Two[0] = card_8;
        cardsArray_Two[1] = card_9;
        cardsArray_Two[2] = card_10;
        cardsArray_Two[3] = card_11;
        cardsArray_Two[4] = card_12;
        cardsArray_Two[5] = card_13;
        cardsArray_Two[6] = card_14;
    }

    public void addLabelsToArray_Three()
    {
        cardsArray_Three[0] = card_15;
        cardsArray_Three[1] = card_16;
        cardsArray_Three[2] = card_17;
        cardsArray_Three[3] = card_18;
        cardsArray_Three[4] = card_19;
        cardsArray_Three[5] = card_20;
        cardsArray_Three[6] = card_21;
    }

    public void setColumn_One_Locations(JLabel[] arrayLabelOne)
    {
        int length = arrayLabelOne.length;
        int gap = 10;
        int counter = 10;
        for (int i = 0; i < length; i++)
        {
            arrayLabelOne[i].setBounds(110, counter, 60, 70);
            counter = counter + arrayLabelOne[i].getBounds().height + gap;
        }
    }

    public void setColumn_Two_Locations(JLabel[] arrayLabelOne)
    {
        int length = arrayLabelOne.length;
        int gap = 10;
        int counter = 10;
        for (int i = 0; i < length; i++)
        {
            arrayLabelOne[i].setBounds(200, counter, 60, 70);
            counter = counter + arrayLabelOne[i].getBounds().height + gap;
        }
    }

    public void setColumn_Three_Locations(JLabel[] arrayLabelOne)
    {
        int length = arrayLabelOne.length;
        int gap = 10;
        int counter = 10;
        for (int i = 0; i < length; i++)
        {
            arrayLabelOne[i].setBounds(290, counter, 60, 70);
            counter = counter + arrayLabelOne[i].getBounds().height + gap;
        }
    }
    ActionListener listener_1 = new ActionListener()
    {
        int i = 0;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            setEnabledRadioButtons(false);
            Component c = CardsMagic.this.getTopLevelAncestor();
            CardsMagic.this.add(cardsArray_One[i++]);
            c.validate();
            c.repaint();
            if (i == cardsArray_One.length)
            {
                timer_1.stop();
                i = 0;
                timer_2.start();
            }
        }
    };
    //
    ActionListener listener_2 = new ActionListener()
    {
        int i = 0;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Component c = CardsMagic.this.getTopLevelAncestor();
            CardsMagic.this.add(cardsArray_Two[i++]);
            c.validate();
            c.repaint();
            if (i == cardsArray_Two.length)
            {
                timer_2.stop();
                i = 0;
                timer_3.start();

            }
        }
    };
    //
    ActionListener listener_3 = new ActionListener()
    {
        int i = 0;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Component c = CardsMagic.this.getTopLevelAncestor();
            CardsMagic.this.add(cardsArray_Three[i++]);
            c.validate();
            c.repaint();
            if (i == cardsArray_Three.length)
            {
                timer_3.stop();
                i = 0;
                setEnabledRadioButtons(true);
            }
        }
    };

    public void setEnabledRadioButtons(boolean bool)
    {
        leftColumnRadio.setEnabled(bool);
        midColumnRadio.setEnabled(bool);
        rightColumnRadio.setEnabled(bool);
    }

    public void showCards()
    {

        setColumn_One_Locations(cardsArray_One);
        timer_1 = new Timer(timerTime, listener_1);
        timer_1.start();


        setColumn_Two_Locations(cardsArray_Two);
        timer_2 = new Timer(timerTime, listener_2);
        // timer_2.start();


        setColumn_Three_Locations(cardsArray_Three);
        timer_3 = new Timer(timerTime, listener_3);
        // timer_3.start();
    }

    public void removeCards()
    {
        for (int i = 0; i < 7; i++)
        {
            this.remove(cardsArray_One[i]);
        }
        for (int i = 0; i < 7; i++)
        {
            this.remove(cardsArray_Two[i]);
        }
        for (int i = 0; i < 7; i++)
        {
            this.remove(cardsArray_Three[i]);
        }
    }

    public void removeAll_in_panel()
    {
        removeCards();
        this.remove(infoTextPanel);
        this.remove(leftColumnRadio);
        this.remove(midColumnRadio);
        this.remove(rightColumnRadio);
    }

    public void collect_One(JLabel[] chosen, JLabel[] left_1, JLabel[] left_2)
    {
        for (int i = 0; i < 7; i++)
        {
            cardsFirstCollect_Left_1[i] = left_1[i].getText();
        }

        for (int i = 0; i < 7; i++)
        {
            cardsFirstCollect_Chosen[i] = chosen[i].getText();
        }

        for (int i = 0; i < 7; i++)
        {
            cardsFirstCollect_Left_2[i] = left_2[i].getText();
        }
        //

    }

    public void coolectAndDeploy_First()
    {
        cardsArray_One[0].setText(cardsFirstCollect_Left_1[0]);
        cardsArray_One[1].setText(cardsFirstCollect_Left_1[3]);
        cardsArray_One[2].setText(cardsFirstCollect_Left_1[6]);
        cardsArray_One[3].setText(cardsFirstCollect_Chosen[2]);
        cardsArray_One[4].setText(cardsFirstCollect_Chosen[5]);
        cardsArray_One[5].setText(cardsFirstCollect_Left_2[1]);
        cardsArray_One[6].setText(cardsFirstCollect_Left_2[4]);
        //
        cardsArray_Two[0].setText(cardsFirstCollect_Left_1[1]);
        cardsArray_Two[1].setText(cardsFirstCollect_Left_1[4]);
        cardsArray_Two[2].setText(cardsFirstCollect_Chosen[0]);
        cardsArray_Two[3].setText(cardsFirstCollect_Chosen[3]);
        cardsArray_Two[4].setText(cardsFirstCollect_Chosen[6]);
        cardsArray_Two[5].setText(cardsFirstCollect_Left_2[2]);
        cardsArray_Two[6].setText(cardsFirstCollect_Left_2[5]);
        //
        cardsArray_Three[0].setText(cardsFirstCollect_Left_1[2]);
        cardsArray_Three[1].setText(cardsFirstCollect_Left_1[5]);
        cardsArray_Three[2].setText(cardsFirstCollect_Chosen[1]);
        cardsArray_Three[3].setText(cardsFirstCollect_Chosen[4]);
        cardsArray_Three[4].setText(cardsFirstCollect_Left_2[0]);
        cardsArray_Three[5].setText(cardsFirstCollect_Left_2[3]);
        cardsArray_Three[6].setText(cardsFirstCollect_Left_2[6]);

        //showCards();
    }

    public void move()
    {
        moveTimer = new Timer(30, mover);
        moveTimer.start();
    }
    ActionListener mover = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            p.translate(-5, -5);
            d.setSize(d.width + widthIcrement, d.height + heightIncrement);
            rect.x = p.x;
            rect.y = p.y;
            rect.width = d.width;
            rect.height = d.height;
            mindReadNumberCard.setBounds(rect);
            mindReadNumberCard.setFont(new Font(Font.SANS_SERIF, 12, fontSize + fontIncrement));
            fontIncrement = fontIncrement + 4;
            revalidate();
            repaint();
            if ((p.x < 15) || (p.y < 20))
            {
                setEndStat();
                moveTimer.stop();
            }
        }
    };

    public void setEndStat()
    {
        try
        {
            Thread.sleep(200);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(CardsMagic.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.add(button);
        button.setActionCommand("Again");
        button.setText("Again");
        button.setBounds(185, 600, 90, 40);

        this.add(exitButton);
    }

    private class EventWatcher implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {

            if (e.getActionCommand().equals("Start"))
            {
                button.setActionCommand("No Action");
                remove(exitButton);
                showCards();
                add(leftColumnRadio);
                add(midColumnRadio);
                add(rightColumnRadio);
                assignNumbersRandomlyToCards();
                CardsMagic.this.add(infoTextPanel);
                infoTextPanel.setText("<<Step 1>>\n\n1.Think about a Number.\n\n"
                        + "2.Select the column that contains your Number.\n\n"
                        + "3.Click \"Next\".");

                button.setBounds(130, 600, 220, 40);
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                button.setText("Waiting you to choose");
            }
            if ((e.getActionCommand().equals("leftColumn")) || (e.getActionCommand().equals("midColumn")) || (e.getActionCommand().equals("rightColumn")))
            {
                Random randGenerater = new Random();
                int randChoice = randGenerater.nextInt(20);
                
                //////
                if (e.getActionCommand().equals("leftColumn"))
                {
                    readyForNext = true;

                    if ((randChoice == 0)
                            || (randChoice == 3)
                            || (randChoice == 4)
                            || (randChoice == 7)
                            || (randChoice == 8)
                            || (randChoice == 11)
                            || (randChoice == 13)
                            || (randChoice == 14)
                            || (randChoice == 16)
                            || (randChoice == 19))
                    {
                        collect_One(cardsArray_One, cardsArray_Two, cardsArray_Three);
                    } else
                    {
                        collect_One(cardsArray_One, cardsArray_Three, cardsArray_Two);
                    }

                    button.setBounds(185, 600, 83, 40);
                    button.setActionCommand("Next_1");
                    button.setText("Next");
                    //
                    if (step_counter > 3)
                    {
                        button.setActionCommand("Read mind");
                        button.setBounds(130, 600, 190, 40);
                        button.setText("Reading your mind!");
                    }

                }
                if (e.getActionCommand().equals("midColumn"))
                {
                    readyForNext = true;


                    if ((randChoice == 0)
                            || (randChoice == 3)
                            || (randChoice == 4)
                            || (randChoice == 7)
                            || (randChoice == 8)
                            || (randChoice == 11)
                            || (randChoice == 13)
                            || (randChoice == 14)
                            || (randChoice == 16)
                            || (randChoice == 19))
                    {
                        collect_One(cardsArray_Two, cardsArray_One, cardsArray_Three);
                    } else
                    {
                        collect_One(cardsArray_Two, cardsArray_Three, cardsArray_One);
                    }


                    button.setActionCommand("Next_1");
                    button.setBounds(185, 600, 83, 40);
                    button.setText("Next");
                    //
                    if (step_counter > 3)
                    {
                        button.setActionCommand("Read mind");
                        button.setBounds(130, 600, 190, 40);
                        button.setText("Reading your mind!");
                    }
                }
                if (e.getActionCommand().equals("rightColumn"))
                {
                    readyForNext = true;

                    if ((randChoice == 0)
                            || (randChoice == 3)
                            || (randChoice == 4)
                            || (randChoice == 7)
                            || (randChoice == 8)
                            || (randChoice == 11)
                            || (randChoice == 13)
                            || (randChoice == 14)
                            || (randChoice == 16)
                            || (randChoice == 19))
                    {
                        collect_One(cardsArray_Three, cardsArray_Two, cardsArray_One);
                    } else
                    {
                        collect_One(cardsArray_Three, cardsArray_One, cardsArray_Two);
                    }

                    button.setActionCommand("Next_1");
                    button.setBounds(185, 600, 83, 40);
                    button.setText("Next");
                    //
                    if (step_counter > 3)
                    {
                        button.setActionCommand("Read mind");
                        button.setBounds(130, 600, 190, 40);
                        button.setText("Reading your mind");
                    }
                }
            }

            if (e.getActionCommand().equals("Next_1"))
            {
                if (readyForNext)
                {
                    readyForNext = false;
                    if (step_counter == 2)
                    {
                        infoTextPanel.setText("<<Step 2>>\n\n1.Again, Select the column that"
                                + " contains your Number.\n\n"
                                + "2.Click \"Next\".");
                    }
                    if (step_counter == 3)
                    {
                        infoTextPanel.setText("<<Step 3>>\n\n1.Last time, Select the column that"
                                + " contains your Number.\n\n"
                                + "2.Click \"Reading your mind!\"");

                        button.setBounds(130, 600, 190, 40);
                        button.setText("Reading your mind!");

                    }
                    step_counter++;

                    chooseColumn.clearSelection();
                    removeCards();
                    repaint();
                    coolectAndDeploy_First();
                    showCards();
                }
            }
            if (e.getActionCommand().equals("Read mind"))
            {
                remove(button);
                removeAll_in_panel();
                repaint();
                mindReadNumberCard.setText(cardsFirstCollect_Chosen[3]);
                mindReadNumberCard.setBounds(rect);
                add(mindReadNumberCard);
                revalidate();
                repaint();
                move();
            }
            if (e.getActionCommand().equals("Again"))
            {
                readyForNext = false;
                chooseColumn.clearSelection();
                mindReadNumberCard.setText("");
                infoTextPanel.setText("");
                remove(mindReadNumberCard);
                fontSize = 6;
                mindReadNumberCard.setFont(new Font(Font.SANS_SERIF, 12, fontSize));
                fontIncrement = 1;
                step_counter = 2;
                p.x = 295;
                p.y = 280;
                d.width = 10;
                d.height = 11;
                rect.x = p.x;
                rect.y = p.y;
                rect.width = d.width;
                rect.height = d.height;

                mindReadNumberCard.setBounds(rect);
                revalidate();
                repaint();
                //
                button.setActionCommand("No Action");
                remove(exitButton);
                showCards();
                add(leftColumnRadio);
                add(midColumnRadio);
                add(rightColumnRadio);
                assignNumbersRandomlyToCards();
                CardsMagic.this.add(infoTextPanel);
                infoTextPanel.setText("<<Step 1>>\n\n1.Think about a Number.\n\n"
                        + "2.Select the column that contains your Number.\n\n"
                        + "3.Click \"Next\".");

                button.setBounds(130, 600, 220, 40);
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                button.setText("Waiting you to choose");
            }
            if (e.getActionCommand().equals("Exit"))
            {
                System.exit(0);
            }
        }
    }

    protected void paintComponent(Graphics g)
    {
        UIDefaults uid = UIManager.getDefaults();
        Graphics2D g2d = (Graphics2D) g;
        Dimension d = this.getSize();

        g2d.setPaint(new GradientPaint(0, 0, uid.getColor("ToolBar.light"),
                0, d.height, uid.getColor("ToolBar.shadow"), true));
        g2d.fillRect(0, 0, d.width, d.height);

        g2d.setColor(uid.getColor("ToolBar.light"));
        g2d.drawLine(0, d.height - 2, d.width, d.height - 2);

        g2d.setColor(uid.getColor("ToolBar.highlight"));
        g2d.drawLine(0, d.height - 1, d.width, d.height - 1);
        //   super.paintComponent(g);
    }

    private static void createAndShowGUI()
    {
        //Create and set up the window.
        frame = new JFrame("Mind Reader");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();
        int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
        int windowY = Math.max(0, ((screenSize.height - windowSize.height) / 2) - 20);
        frame.setLocation(windowX, windowY);
        //Create and set up the content pane.
        CardsMagic demo = new CardsMagic();
        demo.setOpaque(true);
        frame.add(demo);
        // frame.setContentPane(demo);

        //Display the window.
        //  frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        //Schedule a job for the event dispatch thread:
        //creating and showingError this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
