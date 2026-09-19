package projeto.xablau.models;

import java.util.List;

public class Prontuario {
    String diagnostico, observacoes;
    List<String> sintomas, historicoMedico;

    Prontuario(String diagnostico, String observacoes, List<String> sintomas, List<String> historicoMedico){
        this.diagnostico = diagnostico;
        this.observacoes = observacoes;
        this.sintomas = sintomas;
        this.historicoMedico = historicoMedico;
    }

    // -- GETTERS & SETTERS ----
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public List<String> getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(List<String> historicoMedico) {
        this.historicoMedico = historicoMedico;
    }


    // -- METODOS ----
    public void registrarInformacoes(){} // QUE TIPO DE INFORMAÇÃO? | SALVAR NO BD ??? 

    public void atualizarProntuario(){ // ATUALIZAR BD?

    }

    public void consultarProntuario(){
        // PEGAR INFORMACOES DO BD
    }

    public void adicionarDiagnostico(){} // ??? ESSE METODO PROVAVELMENTE É ERRO DE MODELAGEM
}
