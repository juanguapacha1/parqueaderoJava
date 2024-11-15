package parqueadero;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int seleccion;
        String placa = "";
        String tipoVehiculo = "";
        String horaIngreso = "";
        String horaSalida = "";
        Parquedero parquedero = new Parquedero(placa, tipoVehiculo, horaIngreso, horaSalida);
        JOptionPane.showMessageDialog(null, "Bienvenido a Place Parking", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);




        do {
            seleccion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:\n1. Ingresar vehículo\n2. Retirar Vehiculo \n3. Mostrar vehiculo\n0. Salir"));

            switch (seleccion) {
                case 1:
                placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");
                tipoVehiculo = JOptionPane.showInputDialog("Ingrese el tipo de vehículo:");
                horaIngreso = JOptionPane.showInputDialog("Ingrese la hora de ingreso:");
                
                parquedero.ingresarVehiculo(placa, tipoVehiculo, horaIngreso, horaSalida);
                JOptionPane.showMessageDialog(null, "Vehículo ingresado correctamente.");
                break;

                case 2:
                placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");
                parquedero.salidaVehiculo(placa);

                    break;
                case 3:
                    placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");
                    parquedero.mostarVehiculo(placa);
                    break;
                case 0:
                JOptionPane.showMessageDialog(null, "Gracias por utilizar Place Parking");
                break;
            
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (seleccion != 0);



    }
}