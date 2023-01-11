/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.JOptionPane;

/**
 *
 * @author ElPsy
 */
public class VistaMensajes {

    public void ShowMessage(String text, int messageType) {
        String title;
        switch (messageType) {
            case JOptionPane.INFORMATION_MESSAGE: {
                title = "Informacion";
                break;
            }
            case JOptionPane.ERROR_MESSAGE: {
                title = "Error";
                break;
            }
            default: {
                title = "Default";
                break;
            }
        }

        JOptionPane.showMessageDialog(null, text, title, messageType);
    }
    
    public int ShowConfirm(String text, int messageType){
        return JOptionPane.showConfirmDialog(
                null, 
                "Confirmar", 
                "¿ Estás seguro ?", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
