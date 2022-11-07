/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Clases.Algoritmo;
import Clases.AlgoritmoVisual;
import Clases.ExtraCanvas;
import Clases.Punto;
import Clases.QuickSort;
import Clases.Reader;
import Constants.Algorithms;
import Constants.Values;
import Vista.FramePrincipal;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal {

    private int times_calculed;
    private long total_time;

    private FramePrincipal frame_principal;
    private ActionListener al_radio_buttons;
    private ActionListener al_all;
    private ExtraCanvas canvas;

    private ArrayList<Punto> input_points;

    Algorithms.Types algo_method;
    boolean random_points;

    public ControladorPrincipal() {
        this.frame_principal = new FramePrincipal();
        this.canvas = new ExtraCanvas(
                new Dimension(Values.DEFAULT_WIDTH, Values.DEFAULT_HEIGHT)
        );

        this.frame_principal.add(this.canvas);
        this.times_calculed = 0;
        this.total_time = 0;

        this.al_radio_buttons = (ActionEvent e) -> {
            actionPerformedRadioButtons(e);
        };

        this.al_all = (ActionEvent e) -> {
            actionPerformedAll(e);
        };

        ConfigureFrame();

        this.input_points = new ArrayList<>();
    }

    private void addListeners() {
        frame_principal.rB_Exh.addActionListener(this.al_radio_buttons);
        frame_principal.rB_DyV.addActionListener(this.al_radio_buttons);

        frame_principal.rB_PuntosAle.addActionListener(this.al_radio_buttons);
        frame_principal.rB_PuntosFic.addActionListener(this.al_radio_buttons);

        frame_principal.b_Calcular.addActionListener(this.al_all);
        frame_principal.ZoomOutButton.addActionListener(this.al_all);
        frame_principal.ZoomInButton.addActionListener(this.al_all);

        frame_principal.b_ResetMedia.addActionListener(this.al_all);
        frame_principal.b_Repite.addActionListener(this.al_all);
    }

    private void ConfigureFrame() {
        random_points = true;
        frame_principal.rB_PuntosAle.setSelected(true);

        algo_method = Algorithms.Types.DyV;
        frame_principal.rB_DyV.setSelected(true);

        frame_principal.rBG_TipoAlgoritmo.add(frame_principal.rB_DyV);
        frame_principal.rBG_TipoAlgoritmo.add(frame_principal.rB_Exh);

        frame_principal.rBG_TipoPuntos.add(frame_principal.rB_PuntosFic);
        frame_principal.rBG_TipoPuntos.add(frame_principal.rB_PuntosAle);

        frame_principal.setLocationRelativeTo(null);
        frame_principal.setVisible(true);

        addListeners();
    }

    public void actionPerformedRadioButtons(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "rB_Exhaust_Comm": {
                this.algo_method = Algorithms.Types.Exhaustivo;
                break;
            }
            case "rB_DyV_Comm": {
                this.algo_method = Algorithms.Types.DyV;
                break;
            }
            case "rB_PuntosAle_Comm": {
                this.random_points = true;
                break;
            }
            case "rB_PuntosFic_Comm": {
                this.random_points = false;
                break;
            }
        }
    }

    public void actionPerformedAll(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "b_Calcular_Comm": {
                this.canvas.resetCanvas();
                //this.canvas.resetZoom();
                if (this.random_points) {
                    this.GeneraPuntos(this.getNPoints());
                } else {
                    String path = frame_principal.t_Fichero.getText();
                    this.LoadFile(path);
                }
                this.OrdenaPuntos();
                CalcularPuntos();
                break;
            }
            case "b_Repite_comm": {
                this.canvas.resetCanvas();
                reCalculaSolucion();
                break;
            }
            case "ZoomIn": {
                this.canvas.zoomIn(1);
                break;
            }
            case "ZoomOut": {
                this.canvas.zoomOut(1);
                break;
            }
            case "b_ResetMedia_Comm": {
                this.total_time = 0;
                this.times_calculed = 0;
                this.frame_principal.l_Time.setText("Time: 0ms Mean: 0ms");
                break;
            }
        }
    }

    private int getNPoints() {
        String n = this.frame_principal.t_NumPuntos.getText();
        try {
            return Integer.parseInt(n);
        } catch (NumberFormatException e) {
            return Values.N_DEFAULT_POINTS;
        }
    }

    private void GeneraPuntos(int n_points) {
        this.input_points = Algoritmo.GeneraPuntos(n_points, Values.MAX_RANGE, Values.MIN_RANGE);
    }

    private void OrdenaPuntos() {
        QuickSort.Ordena(this.input_points, 0, this.input_points.size() - 1);
    }

    private void LoadFile(String path) {
        // TODO! Change fixed file for path
        try {
            Reader r = new Reader("10p.tsp");
            this.input_points = r.getPuntos();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    private void CalcularPuntos() {
        this.getPointsRange();
        long start;
        long end;
        ArrayList<Punto> solucion = new ArrayList<Punto>();
        this.canvas.addPuntos(this.input_points);

        AlgoritmoVisual a = new AlgoritmoVisual(this.canvas);
        if (this.algo_method == Algorithms.Types.DyV) {
            if (this.input_points.size() <= 20) {
                this.canvas.setPointSize(5);
            }

            start = System.currentTimeMillis();
            solucion = Algoritmo.DivideVenceras(this.input_points);
            end = System.currentTimeMillis();

        } else {
            start = System.currentTimeMillis();
            solucion = Algoritmo.SolucionExhaustiva(this.input_points);
            end = System.currentTimeMillis();
        }
        this.times_calculed++;
        this.total_time += end - start;
        this.frame_principal.l_Time.setText(
                ("Time: " + (end - start) + "ms" + " Mean: " + (this.total_time / this.times_calculed) + "ms")
        );

        this.canvas.addSolucion(solucion);
        this.canvas.paint();
    }

    private void getPointsRange() {
        double min_y = Double.MAX_VALUE;
        double max_y = Double.MIN_VALUE;

        for (Punto punto : this.input_points) {
            if (punto.y < min_y) {
                min_y = punto.y;
            }
            if (punto.y > max_y) {
                max_y = punto.y;
            }
        }

        double rango_x = this.input_points.get(this.input_points.size() - 1).x - this.input_points.get(0).x;
        double rango_y = max_y - min_y;

        this.canvas.setRange(rango_x, rango_y, this.input_points.get(0).x, min_y);
    }

    private void reCalculaSolucion() {
        long start;
        long end;
        ArrayList<Punto> solucion = new ArrayList<Punto>();
        this.canvas.addPuntos(this.input_points);

        AlgoritmoVisual a = new AlgoritmoVisual(this.canvas);
        if (this.algo_method == Algorithms.Types.DyV) {
            if (this.input_points.size() <= 20) {
                this.canvas.setPointSize(5);
            }

            start = System.currentTimeMillis();
            solucion = a.DivideVenceras(this.input_points);
            end = System.currentTimeMillis();

        } else {
            start = System.currentTimeMillis();
            solucion = Algoritmo.SolucionExhaustiva(this.input_points);
            end = System.currentTimeMillis();
        }
        this.times_calculed++;
        this.total_time += end - start;
        this.frame_principal.l_Time.setText(
                ("Time: " + (end - start) + "ms" + " Mean: " + (this.total_time / this.times_calculed) + "ms")
        );

        this.canvas.addSolucion(solucion);
        this.canvas.paint();
    }
}
