/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pcd_practica2;

/**
 *
 * @author ElPsy
 */
public interface ICola {
    int GetNum();
    void Acola(Object elemento) throws Exception;
    Object Desacola()  throws Exception;
    Object Primero()  throws Exception;
}