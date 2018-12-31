/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cardsmagic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class DLabel extends JLabel
{

    Dimension size = new Dimension(70, 75);
    Font font = new Font(Font.SANS_SERIF, 12, 35);
   

    public DLabel(String t)
    {
        this.setPreferredSize(size);
        this.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.black));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setText(t);
        this.setFont(font);
    }
    
        public DLabel()
    {
        this.setPreferredSize(size);
        this.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.black));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(font);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(226, 218, 145);
        Color color2 = color1.brighter();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(
                0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        super.paintComponent(g);
    }
}
