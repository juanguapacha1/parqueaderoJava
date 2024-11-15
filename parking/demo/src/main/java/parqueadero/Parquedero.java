package parqueadero;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Parquedero extends Ingreso {
    private ArrayList<Ingreso> registros;

    public Parquedero(String placa, String tipoVehiculo, String horaIngreso, String horaSalida) {
        super("", "", "", "");
        this.registros = new ArrayList<>();
    }

    public ArrayList<Ingreso> getRegistros() {
        return registros;
    }



    public void ingresarVehiculo(String placa, String tipoVehiculo, String horaIngreso, String horaSalida) {
        Ingreso nuevoIngreso = new Ingreso(placa, tipoVehiculo, horaIngreso, horaSalida);
        registros.add(nuevoIngreso);
    }

    public void mostarVehiculo(String placa) {
        boolean encontrado = false; // Variable para verificar si se encontró el vehículo

        for (Ingreso ingreso : registros) {
            if (ingreso.getPlaca().equals(placa)) {
                String mensaje = "Placa: " + ingreso.getPlaca() + "\n" +
                        "Tipo de vehículo: " + ingreso.getTipoVehiculo() + "\n" +
                        "Hora de ingreso: " + ingreso.getHoraIngreso();
                JOptionPane.showMessageDialog(null, mensaje, "Información del Vehículo",
                        JOptionPane.INFORMATION_MESSAGE);
                encontrado = true; // Se encontró el vehículo
                return;
            }
        }

        // Si no se encontró el vehículo, mostrar un mensaje de error
        JOptionPane.showMessageDialog(null, "No se encontraron registros para la placa " + placa, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void salidaVehiculo(String placa) {
        boolean encontrado = false; // Variable para verificar si se encontró el vehículo

        // Iterar sobre los registros en la clase Parquedero
        for (Ingreso ingreso : registros) {
            if (ingreso.getPlaca().equals(placa)) {
                registros.remove(ingreso);  // Elimina el vehículo de la lista
                JOptionPane.showMessageDialog(null, "Vehículo con placa " + placa + " ha sido retirado.", "Salida",
                        JOptionPane.INFORMATION_MESSAGE);
                encontrado = true; // Se encontró y eliminó el vehículo
                break;  // Salir del bucle luego de eliminar el vehículo
            }
        }

        // Si no encontramos el vehículo con la placa especificada
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró el vehículo con placa " + placa, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
