package projeto.xablau.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    LocalDate data;
    LocalTime horario;
    String status, motivo;

    Consulta(LocalDate data, LocalTime horario, String status, String motivo) {
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.motivo = motivo;
    }

    // -- GETTERS & SETTERS ----
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    

    // -- METODOS ----
    public void agendar(){

    }

    public void cancelar(){

    }

    public void reagendar(){

    }

    public void realizar(){} // ???

    public void alterarStatus(String novoStatus){
        setStatus(novoStatus);
    }
}
