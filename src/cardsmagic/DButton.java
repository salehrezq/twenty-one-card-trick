/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsmagic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author S
 */
public class DButton extends JButton
{

    public DButton()
    {
    }

    public DButton(String s)
    {
        super(s);
    }

//    @Override
//    public void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        Color color1 = new Color(173, 108, 24);
//        Color color2 = color1.brighter();
//        int w = getWidth();
//        int h = getHeight();
//        GradientPaint gp = new GradientPaint(
//                0, 0, color1, 0, h, color2);
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
//        g2d.drawString(getText(), 20, 40);
//        g2d.dispose();
//        super.paintComponent(g);
//    }
//    
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(new Point(0, 0), Color.WHITE, new Point(0,
                getHeight()), Color.PINK.darker()));
        int w = getWidth();
        int h = getHeight();
        g2.fillRect(0, 0, w, h);
        g2.setPaint(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(getText(), 22, 28);

        g2.dispose();

        // super.paintComponent(g);
    }
}
