package Proyecto;


import javax.swing.*;

import java.util.ArrayList;
import java.util.List;;


public class Mercado {

    private List<Producto> productos;
    private List<Comprar> compras;
    private List<Cliente> clientes;

    public Mercado() {
        this.productos = new ArrayList<>();
        this.compras = new ArrayList<>();
        this.clientes = new ArrayList<>();
        productos.add(new Producto(111, "tomate", 0.25f, 45));
        productos.add(new Producto(112, "avena", 2.25f, 67));
        productos.add(new Producto(113, "jugo de naranja", 1.05f, 100));
        productos.add(new Producto(114, "huevo", 0.15f, 80));
        productos.add(new Producto(115, "pan", 0.35f, 290));
    }

    public void ingresarProducto() {
        float precio;
        int stock;
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto: "));
        Producto producto = BuscarProducto(codigo);
        if (producto == null) {
            String producto1 = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
            do {
                precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del producto: "));
                stock = Integer.parseInt(JOptionPane.showInputDialog("la cantidad del stock del producto: "));
                if (precio < 0 || stock < 0) {
                    JOptionPane.showMessageDialog(null, "cantidad ingresada invalida, ingrese nuevamente");
                }
            } while (precio < 0 || stock < 0);
            productos.add(new Producto(codigo, producto1, precio, stock));
            JOptionPane.showMessageDialog(null, "El producto se ha ingresado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El producto ya existe");
        }
    }

    public void EliminarProducto() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto a eliminar: "));
        Producto producto = BuscarProducto(codigo);
        if (producto != null) {
            productos.remove(producto);
            JOptionPane.showMessageDialog(null, "ha sido elminiado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El producto no se encontro el producto");
        }
    }

    public void Stock() {
        int nuevoStock;
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto para renovar stock: "));
        Producto producto = BuscarProducto(codigo);
        if (producto != null) {
            do {
                nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto: "));
                if (nuevoStock < 0) {
                    JOptionPane.showMessageDialog(null, "cantidad ingresada invalida, ingrese nuevamente");
                }
            } while (nuevoStock < 0);

            producto.setStock(nuevoStock + producto.getStock());
            JOptionPane.showMessageDialog(null, "el Stock del producto " + producto.getNombre() + " es de :" + producto.getStock());
        } else {
            JOptionPane.showMessageDialog(null, "El producto no se encontro el producto");
        }
    }


    public void Factura() {
        float subTotal = 0;
        float total = 0;
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido ");
        String cedula = JOptionPane.showInputDialog("Ingrese la cedula del cliente: ");
        String telefono = JOptionPane.showInputDialog("Ingrese telefono del cliente ");
        String correo = JOptionPane.showInputDialog("Ingrese correo del cliente ");
        Cliente cliente = new Cliente(nombre, apellido, cedula, telefono, correo);
        clientes.add(cliente);
        StringBuilder factura = new StringBuilder("productos ingresados: ");
        JOptionPane.showMessageDialog(null, "los productos a comprar son:");
        MostrarProductos();
        ComprarProductos();
        factura.append("cedula: ").append(cedula)
                .append("\n")
                .append(", Nombre: ").append(nombre)
                .append("\n")
                .append(", Apellido: ").append(apellido)
                .append("\n")
                .append(", Cedula: ").append(cedula)
                .append("\n")
                .append(", Telefono: ").append(telefono)
                .append("\n")
                .append(", Correo: ").append(correo)
                .append("\n");
        for (Comprar compras : compras) {
            factura.append("Producto: ").append(compras.getNombre())
                    .append("\n")
                    .append(", Cantidad: ").append(compras.getCantidad())
                    .append("\n")
                    .append(", Precio: ").append(compras.getPrecio())
                    .append("\n")
                    .append(", Total: ").append(compras.getTotal())
                    .append("\n");
            subTotal += compras.getTotal();
        }
        total += (float) (subTotal * 1.14);
        factura.append("SubTotal:").append(subTotal)
                .append("total (IVA = 14% :)").append(total)
                .append("\n");
        JOptionPane.showMessageDialog(null, factura);
        compras.clear();
    }

    public Producto MostrarProductos() {
        StringBuilder articulo = new StringBuilder("productos disponibles: \n");
        for (Producto producto : productos) {
            articulo.append("Producto: ").append(producto.getNombre())
                    .append("\n")
                    .append("Codigo: ").append(producto.getCodigo())
                    .append("\n")
                    .append("Precio: ").append(producto.getPrecio())
                    .append("\n")
                    .append("Stock: ").append(producto.getStock())
                    .append("\n");
            JOptionPane.showMessageDialog(null, articulo);
            articulo.setLength(0);
        }
        return null;
    }

    public void ComprarProductos() {
        try {
            int opcion;
            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el 1 para ingresar un producto a su compra,2 para emitir su factura"));
                switch (opcion) {
                    case 1:
                        // ingrese nombre producto y la cantidad a comprar
                        String nombrePruducto = JOptionPane.showInputDialog("Ingrese nombre del producto ");
                        Producto producto = BuscarProductoNombre(nombrePruducto);
                        if (producto != null) {
                            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de " + producto.getNombre() + " que deseas comprar"));
                            if (0 <= (producto.getStock() - cantidad)) {
                                producto.setStock(producto.getStock() - cantidad);
                                float total = (float) cantidad * producto.getPrecio();
                                compras.add(new Comprar(nombrePruducto, producto.getPrecio(), cantidad, total));
                                JOptionPane.showMessageDialog(null, "El producto se ha ingresado correctamente en e carrito");
                            } else {
                                JOptionPane.showInputDialog(null, "no existe tanto stock");
                            }

                        } else {
                            JOptionPane.showInputDialog(null, "no existe este producto");
                        }
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "su factura esta siendo emitida");
                        break;

                    default:
                        JOptionPane.showInputDialog(null, "opcion no valida");
                        break;
                }
            } while (opcion != 2);
        } catch (Exception x) {
            JOptionPane.showInputDialog(null, "no existe esta opcion");
        }
    }

    public void Editar() {
        float precioNuevo;
        int stockNuevo;
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto a editar: "));
        Producto producto = BuscarProducto(codigo);
        if (producto != null) {
            int codigoNuevo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto: "));
            String productoNuevo = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
            do {
                precioNuevo = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del producto: "));
                stockNuevo = Integer.parseInt(JOptionPane.showInputDialog("la cantidad del stock del producto: "));
                if (precioNuevo < 0 || stockNuevo < 0) {
                    JOptionPane.showMessageDialog(null, "cantidad ingresada invalida, ingrese nuevamente");
                }
            } while (precioNuevo < 0 || stockNuevo < 0);
            producto.setCodigo(codigoNuevo);
            producto.setNombre(productoNuevo);
            producto.setStock(stockNuevo);
            producto.setPrecio(precioNuevo);
            JOptionPane.showMessageDialog(null, "El producto se ha editado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El producto no se encontro el producto");
        }
    }

    public void Compradores() {
        StringBuilder persona = new StringBuilder("Clientes registrados:\n ");
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "no hay clientes ingresados");
        } else {
            for (Cliente cliente : clientes) {
                persona.append("Cedula: ").append(cliente.getDni())
                        .append("\n")
                        .append("Nombre: ").append(cliente.getNombre())
                        .append("\n")
                        .append("Apellido: ").append(cliente.getApellido())
                        .append("\n")
                        .append("Telefono: ").append(cliente.getTelefono())
                        .append("\n")
                        .append("Correo: ").append(cliente.getEmail())
                        .append("\n");

            }
            JOptionPane.showMessageDialog(null, persona);
        }
    }

    private Producto BuscarProducto(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    private Producto BuscarProductoNombre(String nombre) {
        for (Producto producto : productos) {
            if (nombre.equals(producto.getNombre())) {

                return producto;
            }
        }
        return null;
    }
}
