/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
* @author ElPsy
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        rB_Exh = new javax.swing.JRadioButton();
        rB_DyV = new javax.swing.JRadioButton();
        rB_PuntosAle = new javax.swing.JRadioButton();
        rB_PuntosFic = new javax.swing.JRadioButton();
        t_Fichero = new javax.swing.JTextField();
        b_Buscar = new javax.swing.JButton();
        s_NumPuntos = new javax.swing.JSlider();
        t_NumPuntos = new javax.swing.JTextField();
        b_Calcular = new javax.swing.JButton();
        ZoomInButton = new javax.swing.JButton();
        ZoomOutButton = new javax.swing.JButton();
        l_Time = new javax.swing.JLabel();
        b_ResetMedia = new javax.swing.JButton();
        b_Repite = new javax.swing.JButton();
        rB_Dij = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        rB_Exh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_Exh.setText("Exhaustivo");
        rB_Exh.setActionCommand("rB_Exhaust_Comm");

        rB_DyV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_DyV.setText("Divide y Venceras");
        rB_DyV.setActionCommand("rB_DyV_Comm");
        rB_DyV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rB_DyVActionPerformed(evt);
            }
        });

        rB_PuntosAle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_PuntosAle.setText("Puntos aleatorios");
        rB_PuntosAle.setActionCommand("rB_PuntosAle_Comm");

        rB_PuntosFic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rB_PuntosFic.setText("Puntos fichero");
        rB_PuntosFic.setActionCommand("rB_PuntosFic_Comm");

        t_Fichero.setEditable(false);
        t_Fichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_FicheroActionPerformed(evt);
            }
        });

        b_Buscar.setText("Buscar");
        b_Buscar.setActionCommand("b_Buscar_Comm");

        s_NumPuntos.setMaximum(50000);
        s_NumPuntos.setMinimum(100);
        s_NumPuntos.setSnapToTicks(true);

        t_NumPuntos.setActionCommand("t_NumPuntos");

        b_Calcular.setText("DesDes_Calcular");
        b_Calcular.setActionCommand("b_Calcular_Comm");

        ZoomInButton.setText("Zoom In");
        ZoomInButton.setActionCommand("ZoomIn");
        ZoomInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInButtonActionPerformed(evt);
            }
        });

        ZoomOutButton.setText("Zoom Out");
        ZoomOutButton.setActionCommand("ZoomOut");

        l_Time.setText("Time: ");

        b_ResetMedia.setText("Reset Media");
        b_ResetMedia.setActionCommand("b_ResetMedia_Comm");

        b_Repite.setText("Repite");
        b_Repite.setActionCommand("b_Repite_comm");

        rB_Dij.setText("Dijkstra");
        rB_Dij.setActionCommand("rB_Dijkstra_Comm");
        rB_Dij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rB_DijActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(820, 820, 820)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_Calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(ZoomInButton)
                                .addGap(40, 40, 40)
                                .addComponent(ZoomOutButton)
                                .addGap(51, 51, 51)
                                .addComponent(l_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_Repite, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_ResetMedia)
                                    .addComponent(rB_DyV))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rB_Exh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rB_Dij)
                        .addGap(116, 116, 116))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rB_Exh)
                    .addComponent(rB_Dij))
                .addGap(18, 18, 18)
                .addComponent(rB_DyV)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ZoomInButton)
                    .addComponent(ZoomOutButton)
                    .addComponent(l_Time))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rB_PuntosAle)
                    .addComponent(s_NumPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_NumPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rB_PuntosFic)
                    .addComponent(t_Fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_Buscar))
                .addGap(44, 44, 44)
                .addComponent(b_ResetMedia)
                .addGap(34, 34, 34)
                .addComponent(b_Repite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                .addComponent(b_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ZoomInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomInButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ZoomInButtonActionPerformed

    private void t_FicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_FicheroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_FicheroActionPerformed

    private void rB_DyVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rB_DyVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rB_DyVActionPerformed

    private void rB_DijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rB_DijActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rB_DijActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ZoomInButton;
    public javax.swing.JButton ZoomOutButton;
    public javax.swing.JButton b_Buscar;
    public javax.swing.JButton b_Calcular;
    public javax.swing.JButton b_Repite;
    public javax.swing.JButton b_ResetMedia;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel l_Time;
    public javax.swing.ButtonGroup rBG_TipoAlgoritmo;
    public javax.swing.ButtonGroup rBG_TipoPuntos;
    public javax.swing.JRadioButton rB_Dij;
    public javax.swing.JRadioButton rB_DyV;
    public javax.swing.JRadioButton rB_Exh;
    public javax.swing.JRadioButton rB_PuntosAle;
    public javax.swing.JRadioButton rB_PuntosFic;
    public javax.swing.JSlider s_NumPuntos;
    public javax.swing.JTextField t_Fichero;
    public javax.swing.JTextField t_NumPuntos;
    // End of variables declaration//GEN-END:variables
}
