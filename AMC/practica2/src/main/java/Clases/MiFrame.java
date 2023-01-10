/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Clases;

import guru.nidi.graphviz.attribute.*;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.model.*;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;

/**
 *
 * @author Adrian
 */
public class MiFrame extends javax.swing.JFrame {

    Proceso actual = null;
    boolean canvadAdded = false;
    MiCanvas canvas;
    public MiFrame() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_BuscarArchivo = new javax.swing.JButton();
        b_PasoAPaso = new javax.swing.JButton();
        txt_Archivo = new javax.swing.JTextField();
        b_GenerarAFND = new javax.swing.JButton();
        txt_Regex = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        b_Todo1 = new javax.swing.JButton();
        b_GenerarAFD = new javax.swing.JButton();
        lbl_Estado = new javax.swing.JLabel();
        lbl_Info = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        b_BuscarArchivo.setText("Abrir...");
        b_BuscarArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_BuscarArchivoMouseClicked(evt);
            }
        });

        b_PasoAPaso.setText("Paso a paso");
        b_PasoAPaso.setEnabled(false);
        b_PasoAPaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_PasoAPasoMouseClicked(evt);
            }
        });

        b_GenerarAFND.setText("Generar AFND");
        b_GenerarAFND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_GenerarAFNDMouseClicked(evt);
            }
        });

        jLabel1.setText("Archivo");

        jLabel2.setText("Regex");

        b_Todo1.setText("Todo");
        b_Todo1.setEnabled(false);
        b_Todo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_Todo1MouseClicked(evt);
            }
        });

        b_GenerarAFD.setText("Generar AFD");
        b_GenerarAFD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_GenerarAFDMouseClicked(evt);
            }
        });

        lbl_Estado.setText(" ");

        lbl_Info.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(639, 639, 639)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_Archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b_BuscarArchivo))
                            .addComponent(txt_Regex))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_Todo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b_GenerarAFD, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_GenerarAFND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_PasoAPaso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addComponent(lbl_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_Estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_BuscarArchivo)
                    .addComponent(txt_Archivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Regex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Estado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Info)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_GenerarAFD, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_GenerarAFND, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_PasoAPaso, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_Todo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(370, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_BuscarArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_BuscarArchivoMouseClicked
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Elige un archivo");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            txt_Archivo.setText(chooser.getSelectedFile().getAbsolutePath());
        }
        
    }//GEN-LAST:event_b_BuscarArchivoMouseClicked

    private void b_GenerarAFNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_GenerarAFNDMouseClicked
         /*Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link(
                                to(node("c"))
                                        .with(attr("label", "rgrggrgrHola")),
                                to(node("D"))
                        )
                );*/
        if(!canvadAdded){
            canvas = new MiCanvas(new Dimension(627, 700));
            this.add(canvas);
            canvadAdded = true;
        }
         
        File file = new File(txt_Archivo.getText());
        if(!file.exists() || file.isDirectory()) return;
        

        AFND afnd = AFND.LeerAFND(txt_Archivo.getText());
        actual = afnd;

        var totalEstados = afnd.estados.size();

        MutableGraph g = graph("AFND")
                .directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .toMutable();
         
        for(int i = 0; i < totalEstados; ++i)
        {
            ArrayList<Integer> destinos = new ArrayList<>();
            ArrayList<Character> simbolos = new ArrayList<>();
            
            var nodo = node(afnd.estados.get(i));
            
            for(int j = 0; j < afnd.transiciones.size(); ++j)
            {
                if(afnd.transiciones.get(j).E1 == i)
                {
                    destinos.add(afnd.transiciones.get(j).E2);
                    simbolos.add(afnd.transiciones.get(j).Simb);
                }
            }
            
            for(int j = 0; j < afnd.transicionesL.size(); ++j)
            {
                if(afnd.transicionesL.get(j).E1 == i)
                {
                    destinos.add(afnd.transicionesL.get(j).E2);
                    simbolos.add('λ');
                }
            }
            
            for(int j = 0; j < destinos.size(); ++j)
            {
                nodo = nodo.link(to(node(afnd.estados.get(destinos.get(j))))
                    .with(attr("label", simbolos.get(j).toString())));
            }
            if(i == 0)
            {
                nodo = nodo.with(Color.RED);
            }
            else
            {
                final int i2 = i;
                if(Arrays.stream(afnd.estadosFinales).anyMatch(v-> v == i2))
                {
                    nodo = nodo.with(Color.BLUE);
                }
            }
            nodo.addTo(g);
        }
        
        var img = Graphviz.fromGraph(g).width(627).render(Format.PNG).toImage();
        
        b_PasoAPaso.setEnabled(true);
        b_Todo1.setEnabled(true);
        
        canvas.ChangeImg(img);
        
    }//GEN-LAST:event_b_GenerarAFNDMouseClicked

    private void b_GenerarAFDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_GenerarAFDMouseClicked
        if(!canvadAdded){
            canvas = new MiCanvas(new Dimension(627, 700));
            this.add(canvas);
            canvadAdded = true;
        }
         
        File file = new File(txt_Archivo.getText());
        if(!file.exists() || file.isDirectory()) return;
        

        AFD afd = AFD.LeerAFD(txt_Archivo.getText());
        actual = afd;

        var totalEstados = afd.estados.size();

        MutableGraph g = graph("AFD")
                .directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .toMutable();
         
        for(int i = 0; i < totalEstados; ++i)
        {
            ArrayList<Integer> destinos = new ArrayList<>();
            ArrayList<Character> simbolos = new ArrayList<>();
            
            var nodo = node(afd.estados.get(i));
            
            for(int j = 0; j < afd.transiciones.size(); ++j)
            {
                if(afd.transiciones.get(j).E1 == i)
                {
                    destinos.add(afd.transiciones.get(j).E2);
                    simbolos.add(afd.transiciones.get(j).Simb);
                }
            }
            
            for(int j = 0; j < destinos.size(); ++j)
            {
                nodo = nodo.link(to(node(afd.estados.get(destinos.get(j))))
                    .with(attr("label", simbolos.get(j).toString())));
            }
            if(i == 0)
            {
                nodo = nodo.with(Color.RED);
            }
            else
            {
                final int i2 = i;
                if(Arrays.stream(afd.estadosFinales).anyMatch(v-> v == i2))
                {
                    nodo = nodo.with(Color.BLUE);
                }
            }
            nodo.addTo(g);
        }
        
        var img = Graphviz.fromGraph(g).width(627).render(Format.PNG).toImage();
        
        b_PasoAPaso.setEnabled(true);
        b_Todo1.setEnabled(true);
        
        canvas.ChangeImg(img);
    }//GEN-LAST:event_b_GenerarAFDMouseClicked

    private void b_Todo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_Todo1MouseClicked
        boolean bien = actual.Match(txt_Regex.getText());
        
        if(bien)
        {
            lbl_Estado.setText("ACEPTAR");
        }
        else
        {
            lbl_Estado.setText("RECHAZAR");
        }
    }//GEN-LAST:event_b_Todo1MouseClicked

    private void b_PasoAPasoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_PasoAPasoMouseClicked
        String[] info = actual.StepByStep(txt_Regex.getText()).split(" ");
        
        int pos = Integer.parseInt(info[0]);
        
        String regex = txt_Regex.getText();
        
        String inf = regex.substring(0, pos) + "|" + regex.substring(pos);
        
        String estado = info[1];
        
        lbl_Estado.setText(estado);
        lbl_Info.setText(inf);
    }//GEN-LAST:event_b_PasoAPasoMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton b_BuscarArchivo;
    private javax.swing.JButton b_GenerarAFD;
    private javax.swing.JButton b_GenerarAFND;
    private javax.swing.JButton b_PasoAPaso;
    private javax.swing.JButton b_Todo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbl_Estado;
    private javax.swing.JLabel lbl_Info;
    private javax.swing.JTextField txt_Archivo;
    private javax.swing.JTextField txt_Regex;
    // End of variables declaration//GEN-END:variables
}
