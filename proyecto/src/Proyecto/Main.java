package Proyecto;

import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        int opcion;
        Mercado nuevo = new Mercado();
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Menu\n 1. ingresar un producto\n 2. renovar stock\n 3. editar producto\n 4. eliminar producto\n 5. realizar una factura\n 6. ver la lista de compradores\n 7. ver productos\n 8. salir"));
            switch (opcion) {
                case 1:
                    nuevo.ingresarProducto();
                    break;
                case 2:
                    nuevo.Stock();
                    break;
                case 3:
                    nuevo.Editar();
                    break;
                case 4:
                    nuevo.EliminarProducto();
                    break;
                case 5:
                    nuevo.Factura();
                    break;
                case 6:
                    nuevo.Compradores();
                    break;
                case 7:
                    nuevo.MostrarProductos();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "fin del programa");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "no existe esta opcion");
                    break;
            }
        } while (opcion != 8);

    }
}
