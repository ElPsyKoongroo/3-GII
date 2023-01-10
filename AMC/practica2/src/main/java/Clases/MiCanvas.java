package Clases;

import java.awt.*;

public class MiCanvas extends Canvas
{
    public Image img;
    public MiCanvas(Dimension size)
    {
        this.setSize(size);
    }

    public void ChangeImg(Image img2)
    {
        img = img2;
        repaint();
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(img, 0, 0, null);
    }
}
