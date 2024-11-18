package parqueadero;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Iterator;

public class Parquedero extends Ingreso {
    private ArrayList<Ingreso> registros;
    private double tarifaPorHora;

    public Parquedero(double tarifaPorHora, String placa, String tipoVehiculo, String horaIngreso, String horaSalida) {
        super("", "", "", "");
        this.registros = new ArrayList<>();
        this.tarifaPorHora = 5000;
    }

    public ArrayList<Ingreso> getRegistros() {
        return registros;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
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




    public String salidaVehiculo(String placa, String horaSalida, double tarifaPorHora, String horaIngreso,String tipoVehiculo) {
        // Define el formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime horaSalidaFormato;
        LocalDateTime horaIngresoFormato;
        String mensaje = "";
        // Convertir horaSalida a LocalDateTime
        try {
            horaSalidaFormato = LocalDateTime.parse(horaSalida, formatter);
            horaIngresoFormato = LocalDateTime.parse(horaIngreso, formatter);
        } catch (DateTimeParseException e) {
            return "Formato de hora de salida no válido. Use el formato: yyyy-MM-dd HH:mm";
        }
    
        Iterator<Ingreso> iterator = registros.iterator();
        while (iterator.hasNext()) {
            Ingreso ingreso = iterator.next();
            if (ingreso.getPlaca().equals(placa)) {
                // Calcular el tiempo transcurrido
                long horasTranscurridas = ChronoUnit.HOURS.between(horaIngresoFormato, horaSalidaFormato);
    
                // Calcular el costo
                if (tipoVehiculo.equalsIgnoreCase("moto") || tipoVehiculo.equalsIgnoreCase("carro")) {
                    double costo = horasTranscurridas * tarifaPorHora;
                    mensaje = "Placa: " + placa + "\n" +
                    "Vehículo: " + ingreso.getTipoVehiculo() + "\n" +
                    "Fecha y hora de entrada: " + ingreso.getHoraIngreso() + "\n" +
                    "Fecha y hora de salida: " + horaSalida + "\n" +"horas transcurridas: " + horasTranscurridas + "\n" +horasTranscurridas+
                    "Valor por hora: $" + tarifaPorHora + "\n" +
                    "Valor total: $" + costo+ "\n" + "\n"+"Gracias por su visita";;
            JOptionPane.showMessageDialog(null, mensaje, "Salida del Vehículo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (tipoVehiculo.equalsIgnoreCase("hibrido")) {
                    double descuento = horasTranscurridas * tarifaPorHora * 0.1;
                    double costo = horasTranscurridas * tarifaPorHora - descuento;
                    mensaje = "Placa: " + placa + "\n" +
                    "Vehículo: " + ingreso.getTipoVehiculo() + "\n" +
                    "Fecha y hora de entrada: " + ingreso.getHoraIngreso() + "\n" +
                    "Fecha y hora de salida: " + horaSalida + "\n" +"horas transcurridas: " + horasTranscurridas + "\n" +horasTranscurridas+
                    "Valor por hora: $" + tarifaPorHora + "\n" +"descunete 10%: $" + descuento + "\n" +
                    "Valor total: $" + costo + "\n" + "\n"+"Gracias por ayudar al medio ambiente";
            JOptionPane.showMessageDialog(null, mensaje, "Salida del Vehículo", JOptionPane.INFORMATION_MESSAGE);
                }
    
                // Eliminar el registro del vehículo
                iterator.remove();
    
                // Mostrar información y devolver mensaje
                return mensaje;
            }
        }

        return "No se encontró el vehículo con placa " + placa + ".";
    }
}

