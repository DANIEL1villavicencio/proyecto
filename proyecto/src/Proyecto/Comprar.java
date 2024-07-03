package Proyecto;

public class Comprar {
    private String nombre;
    private float precio;
    private int cantidad;
    private float total;

    public Comprar(String nombre, float precio, int cantidad, float total) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
