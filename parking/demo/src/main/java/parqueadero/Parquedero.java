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
                String mensaje = "Placa: " + placa.toUpperCase() + "\n" +
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




    public String salidaVehiculo(String placa, String horaSalida, double tarifaPorHora, String horaIngreso, String tipoVehiculo) {
        // Define el formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime horaSalidaFormato;
        LocalDateTime horaIngresoFormato;
        String mensajeNoEncontrado = "No se encontró el vehículo.";
    
        // Convertir horaSalida y horaIngreso a LocalDateTime
        try {
            horaSalidaFormato = LocalDateTime.parse(horaSalida, formatter);
            horaIngresoFormato = LocalDateTime.parse(horaIngreso, formatter);
        } catch (DateTimeParseException e) {
            return "Formato de hora no válido. Use el formato: yyyy-MM-dd HH:mm";
        }
    
        Iterator<Ingreso> iterator = registros.iterator();
        while (iterator.hasNext()) {
            Ingreso ingreso = iterator.next();
            if (ingreso.getPlaca().equalsIgnoreCase(placa)) {
                // Calcular horas transcurridas
                long horasTranscurridas = ChronoUnit.HOURS.between(horaIngresoFormato, horaSalidaFormato);
    
                // Calcular el costo basado en el tipo de vehículo
                double costo = 0;
                String mensajeAdicional = "";
    
                switch (tipoVehiculo.toLowerCase()) {
                    case "carro":
                        costo = horasTranscurridas * tarifaPorHora;
                        break;
                    case "moto":
                        double tarifaMoto = tarifaPorHora - 2000;
                        costo = horasTranscurridas * tarifaMoto;
                        break;
                    case "hibrido":
                        double descuento = horasTranscurridas * tarifaPorHora * 0.1;
                        costo = horasTranscurridas * tarifaPorHora - descuento;
                        mensajeAdicional = "Descuento 10%: $" + descuento + "\n";
                        break;
                    default:
                        return "Tipo de vehículo no válido.";
                }
    
                // Construir mensaje
                String mensaje = "Placa: " + placa.toUpperCase() + "\n" +
                        "Vehículo: " + tipoVehiculo + "\n" +
                        "Fecha y hora de entrada: " + ingreso.getHoraIngreso() + "\n" +
                        "Fecha y hora de salida: " + horaSalida + "\n" +
                        "Horas transcurridas: " + horasTranscurridas + "\n" +
                        "Valor por hora: $" + tarifaPorHora + "\n" +
                        mensajeAdicional +
                        "Valor total: $" + costo + "\n" +
                        "Gracias por su visita.";
    
                // Eliminar el registro y retornar mensaje
                iterator.remove();
                return mensaje;
            }
        }
    
        return mensajeNoEncontrado;
    }
}

