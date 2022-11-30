package Contralador;

import Modelo.Conexion;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.VistaAddSocio;
import Vista.VistaMensajes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.awt.Frame;


public class ControladorAddSocio {
    private ActionListener vAddSocioListener;
    private VistaAddSocio vaddSocio;
    private SocioDAO sDAO;
    private Socio placeHolder = null;

    public ControladorAddSocio(Frame parent, Conexion con) {
        this.sDAO = new SocioDAO(con);
        this.vaddSocio = new VistaAddSocio(parent, true);
        this.vAddSocioListener = (ActionEvent e) -> actionPerformedSocio(e);

        this.addListeners();

        this.vaddSocio.setVisible(true);
    }

    public ControladorAddSocio(Frame parent, Conexion con, Socio s) {
        this.sDAO = new SocioDAO(con);
        this.vaddSocio = new VistaAddSocio(parent, true);
        this.vAddSocioListener = (ActionEvent e) -> actionPerformedSocio(e);
        this.placeHolder = s;
        this.setPlaceHolder();
        this.addListeners();

        
        // Cuando se cambia el texto tambien se cambia el Action Command !!!
        this.vaddSocio.InsertButton.setText("Actualizar");

        this.vaddSocio.setVisible(true);
    }

    private void addListeners() {
        this.vaddSocio.CancelButton.addActionListener(vAddSocioListener);
        this.vaddSocio.InsertButton.addActionListener(vAddSocioListener);

    }

    private void getSocioData() {
        String args[] = new String[8];
        args[SocioDAO.NUMSOCIO - 1] = this.vaddSocio.NumSocioTextBox.getText();
        args[SocioDAO.NOMBRE - 1] = this.vaddSocio.NameTextBox.getText();
        args[SocioDAO.DNI - 1] = this.vaddSocio.DniTextBox.getText();
        args[SocioDAO.FNACIMIENTO -1] = this.vaddSocio.BirthDateTextBox.getText();
        args[SocioDAO.TEL - 1] = this.vaddSocio.TelTextBox.getText();
        args[SocioDAO.CORREO - 1] = this.vaddSocio.EmailTextBox.getText();
        args[SocioDAO.FENTRADA - 1] = this.vaddSocio.DateTextBox.getText();
        args[SocioDAO.CATEGORIA - 1] = this.vaddSocio.CategoriaTextBox.getText();

        for (String value : args) {
            if (value == null) {
                return;
            }
        }

        Socio s = new Socio(
                args[SocioDAO.NUMSOCIO - 1],
                args[SocioDAO.NOMBRE - 1],
                args[SocioDAO.DNI - 1],
                args[SocioDAO.FNACIMIENTO - 1],
                args[SocioDAO.TEL - 1],
                args[SocioDAO.CORREO - 1],
                args[SocioDAO.FENTRADA - 1],
                args[SocioDAO.CATEGORIA - 1]
        );
        
        VistaMensajes v = new VistaMensajes();

        try {
            if(placeHolder !=null)
                this.sDAO.updateSocio(s);
            else
                this.sDAO.añadeSocio(s);
            v.ShowMessage("Socio añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
            this.vaddSocio.dispose();
        } catch (SQLException error) {
            v.ShowMessage("Error al añadir Socio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionPerformedSocio(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancelar": {
                System.out.println("Cerrando");
                this.vaddSocio.dispose();
                break;
            }
            
            case "Actualizar":{
                System.out.println("Dropping !");
            }

            case "Insertar": {
                this.getSocioData();
                break;
            }

            default: {
                System.out.println("Que has hecho para llegar aqui viajero ?");
            }

        }
    }

    public void setPlaceHolder(){
        this.vaddSocio.NumSocioTextBox.setText(this.placeHolder.getNumeroSocio());
        this.vaddSocio.NumSocioTextBox.setEnabled(false);
        this.vaddSocio.NameTextBox.setText(this.placeHolder.getNombre());
        this.vaddSocio.DniTextBox.setText(this.placeHolder.getDni());
        this.vaddSocio.BirthDateTextBox.setText(this.placeHolder.getFechaNacimiento());
        this.vaddSocio.TelTextBox.setText(this.placeHolder.getTelefono());
        this.vaddSocio.EmailTextBox.setText(this.placeHolder.getCorreo());
        this.vaddSocio.DateTextBox.setText(this.placeHolder.getFechaEntrada());
        this.vaddSocio.CategoriaTextBox.setText(this.placeHolder.getCategoria());
        //No se si tengo que hacer algo mas..
    }
}
