/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ElPsy
 */
public class Socio {

    private String numeroSocio;
    private String nombre;
    private String dni;
    private String fechaNacimiento;
    private String telefono;
    private String correo;
    private String fechaEntrada;
    private String categoria;

    public Socio() {
        this.numeroSocio = null;
        this.nombre = null;
        this.dni = null;
        this.fechaNacimiento = null;
        this.telefono = null;
        this.correo = null;
        this.fechaEntrada = null;
        this.categoria = null;
    }

    public Socio(String _numeroSocio, String _nombre, String _dni, String _fechaNacimiento, String _telefono, String _correo, String _fechaEntrada, String _categoria) {
        this.numeroSocio = _numeroSocio;
        this.nombre = _nombre;
        this.dni = _dni;
        this.fechaNacimiento = _fechaNacimiento;
        this.telefono = _telefono;
        this.correo = _correo;
        this.fechaEntrada = _fechaEntrada;
        this.categoria = _categoria;

    }

    // Getters
    public String getNumeroSocio() {
        return numeroSocio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getFechaEntrada() {
        return this.fechaEntrada;
    }

    public String getCategoria() {
        return this.categoria;
    }

    // Setters
    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
