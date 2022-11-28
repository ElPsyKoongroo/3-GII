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

    public ControladorAddSocio(Frame parent, Conexion con) {
        this.sDAO = new SocioDAO(con);
        this.vaddSocio = new VistaAddSocio(parent, true);
        this.vAddSocioListener = (ActionEvent e) -> actionPerformedSocio(e);

        this.addListeners();

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

        Socio m = new Socio(
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
            this.sDAO.añadeSocio(m);
            v.ShowConectionMessage("Socio añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException error) {
            v.ShowConectionMessage("Error al añadir Socio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionPerformedSocio(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancelar": {
                System.out.println("Cerrando");
                this.vaddSocio.dispose();
                break;
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
}

