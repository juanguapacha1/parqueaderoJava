package parqueadero;


public class Ingreso {
    private String placa;
    private String tipoVehiculo;
    private String horaIngreso;
    private String horaSalida;


    public Ingreso(String placa, String tipoVehiculo, String horaIngreso, String horaSalida) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }




    
}
