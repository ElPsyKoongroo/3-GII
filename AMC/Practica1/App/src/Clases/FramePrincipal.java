/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Clases;

/**
 *
 * @author usuario
 */
public class FramePrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rBG_TipoPuntos = new javax.swing.ButtonGroup();
        rBG_TipoAlgoritmo = new javax.swing.ButtonGroup();
        rB_Exh = new javax.swing.JRadioButton();
        rB_DyV = new javax.swing.JRadioButton();
        rB_PuntosAle = new javax.swing.JRadioButton();
        rB_PuntosFic = new javax.swing.JRadioButton();
        t_Fichero = new javax.swing.JTextField();
        b_Buscar = new javax.swing.JButton();
        s_NumPuntos = new javax.swing.JSlider();
        t_NumPuntos = new javax.swing.JTextField();
        b_Calcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        rB_Exh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_Exh.setText("Exhaustivo");
        rB_Exh.setActionCommand("rB_Exhaust_Comm");

        rB_DyV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_DyV.setText("Divide y Venceras");
        rB_DyV.setActionCommand("rB_DyV_Comm");

        rB_PuntosAle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_PuntosAle.setText("Puntos aleatorios");
        rB_PuntosAle.setActionCommand("rB_PuntosAle_Comm");

        rB_PuntosFic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_PuntosFic.setText("Puntos fichero");
        rB_PuntosFic.setActionCommand("rB_PuntosFic_Comm");

        t_Fichero.setEditable(false);

        b_Buscar.setText("Buscar");
        b_Buscar.setActionCommand("b_Buscar_Comm");

        s_NumPuntos.setMaximum(50000);
        s_NumPuntos.setMinimum(100);
        s_NumPuntos.setSnapToTicks(true);

        b_Calcular.setText("DesDes_Calcular");
        b_Calcular.setActionCommand("b_Calcular_Comm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(820, 820, 820)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rB_PuntosAle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(s_NumPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rB_PuntosFic)
                                .addGap(33, 33, 33)
                                .addComponent(t_Fichero, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t_NumPuntos)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rB_Exh)
                            .addComponent(rB_DyV))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(b_Calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rB_Exh)
                .addGap(18, 18, 18)
                .addComponent(rB_DyV)
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rB_PuntosAle)
                    .addComponent(s_NumPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_NumPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rB_PuntosFic)
                    .addComponent(t_Fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_Buscar))
                .addGap(18, 18, 18)
                .addComponent(b_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton b_Buscar;
    public javax.swing.JButton b_Calcular;
    public javax.swing.ButtonGroup rBG_TipoAlgoritmo;
    public javax.swing.ButtonGroup rBG_TipoPuntos;
    public javax.swing.JRadioButton rB_DyV;
    public javax.swing.JRadioButton rB_Exh;
    public javax.swing.JRadioButton rB_PuntosAle;
    public javax.swing.JRadioButton rB_PuntosFic;
    public javax.swing.JSlider s_NumPuntos;
    public javax.swing.JTextField t_Fichero;
    public javax.swing.JTextField t_NumPuntos;
    // End of variables declaration//GEN-END:variables
}
