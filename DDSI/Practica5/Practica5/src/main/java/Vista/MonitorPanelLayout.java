/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

/**
 *
 * @author Usuario
 */
public class MonitorPanelLayout extends javax.swing.JPanel {

    /**
     * Creates new form MonitorPanelLayout
     */
    public MonitorPanelLayout() {
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

        jButtonNewMonitor = new javax.swing.JButton();
        jButtonUpdateMonitor = new javax.swing.JButton();
        jButtonDeleteMonitor = new javax.swing.JButton();
        ButtonCerrar = new javax.swing.JButton();
        jGestionLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMonitores = new javax.swing.JTable();

        jButtonNewMonitor.setText("Nuevo Monitor");
        jButtonNewMonitor.setActionCommand("ButtonNewMonitor");

        jButtonUpdateMonitor.setText("Actualiza Monitor");
        jButtonUpdateMonitor.setActionCommand("ButtonUpdateMonitor");

        jButtonDeleteMonitor.setText("Elimina Monitor");
        jButtonDeleteMonitor.setToolTipText("");
        jButtonDeleteMonitor.setActionCommand("ButtonRemoveMonitor");

        ButtonCerrar.setText("Salir");
        ButtonCerrar.setActionCommand("CerrarCommand");

        jGestionLabel2.setText("Gestion De Monitores");

        jTableMonitores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane3.setViewportView(jTableMonitores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonNewMonitor)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonUpdateMonitor)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonDeleteMonitor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 475, Short.MAX_VALUE)
                            .addComponent(ButtonCerrar)
                            .addGap(16, 16, 16))
                        .addComponent(jScrollPane3)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jGestionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(9, 9, 9)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jGestionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNewMonitor)
                        .addComponent(jButtonUpdateMonitor)
                        .addComponent(jButtonDeleteMonitor)
                        .addComponent(ButtonCerrar))
                    .addGap(20, 20, 20)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ButtonCerrar;
    public javax.swing.JButton jButtonDeleteMonitor;
    public javax.swing.JButton jButtonNewMonitor;
    public javax.swing.JButton jButtonUpdateMonitor;
    public javax.swing.JLabel jGestionLabel2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTable jTableMonitores;
    // End of variables declaration//GEN-END:variables
}
