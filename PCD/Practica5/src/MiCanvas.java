/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author ElPsy
 * 
 */
public class MiCanvas extends Canvas {

    ArrayList<Integer> coches;
    ArrayList<Integer> furgos;
    int cont;
    Tunel.Estado tuneles[];
    int ids[];
    
    public MiCanvas()
    {
        coches = new ArrayList<>();
        furgos = new ArrayList<>();
        cont = 1;
        tuneles = new Tunel.Estado[3];
        ids = new int[3];
        
        for(int i = 0; i < 3; i++)
        {
            tuneles[i] = Tunel.Estado.VACIO;
        }
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public int AddCola(Tunel.Estado tipo)
    {
        int id = cont++;
        if(tipo == Tunel.Estado.COCHE){
            coches.add(id);
        }
        else
        {
            furgos.add(id);   
        }
        System.out.println("Metiendo: " + id);
        repaint();
        return id;
    }
    
    public void  AddTunel(Tunel.Estado tipo, int pos, int id)
    {
        System.out.println("Borrando: " + id);
        if(tipo == Tunel.Estado.COCHE)
        {
            coches.remove(Integer.valueOf(id));
        }
        else
        {
            furgos.remove(Integer.valueOf(id));
        }
        
        tuneles[pos] = tipo;
        ids[pos] = id;
        repaint();
    }
    
    public void RemoveTunel(int pos)
    {
        tuneles[pos] = Tunel.Estado.VACIO;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
        Font fuente = new Font("Helvetica", Font.BOLD, 30);
        og.setFont(fuente);
        
        og.setColor(Color.WHITE);
        og.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        og.setColor(Color.RED);
        og.fillOval(50, 0, 50, 50);
        og.drawString("Coches", 50, 75);
        
        og.setColor(Color.BLUE);
        og.fillOval(250, 0, 50, 50);
        og.drawString("Furgos", 250, 75);
        
        int pos = 150;
        for(int i = 0; i < 3; i++)
        {
            switch(i)
            {
                case 0:
                    og.setColor(Color.MAGENTA);
                    break;
                case 1:
                    og.setColor(Color.BLACK);
                    break;
                case 2:
                    og.setColor(Color.PINK);
                    break;
            }
            og.drawLine(50, pos, 100, pos);
            og.drawLine(50, pos+50, 100, pos+50);
            pos+=100;
        }
        og.setColor(Color.RED);
        
        int totalCoches = 0;
        for (int i : coches)
        {
            DrawVehicle(og, 200+totalCoches*50, 150, i, Tunel.Estado.COCHE);
            totalCoches++;
        }
        og.setColor(Color.BLUE);
        
        int totalFurgos = 0;
        
        for (int i : furgos)
        {
            DrawVehicle(og, 200+totalFurgos*50, 300, i, Tunel.Estado.FURGO);
            totalFurgos++;
        }
        
        pos = 150;
        for (int i = 0; i < 3; i++)
        {
            if(tuneles[i] == Tunel.Estado.COCHE)
            {
                DrawVehicle(og, 50, pos, ids[i], Tunel.Estado.COCHE);
            }
            else if(tuneles[i] == Tunel.Estado.FURGO)
            {
                DrawVehicle(og, 50, pos, ids[i], Tunel.Estado.FURGO);
            }
            pos += 100;
        }
        g.drawImage(img, 0, 0, null);
    }
    private void DrawVehicle(Graphics og, int x, int y, int id, Tunel.Estado tipo)
    {
        if(tipo == Tunel.Estado.COCHE) og.setColor(Color.RED);
        else og.setColor(Color.BLUE);
        
        og.fillOval(x, y, 50, 50);
        og.setColor(Color.BLACK);
        og.drawString("" + id, x+7, y+33);
    }
}
