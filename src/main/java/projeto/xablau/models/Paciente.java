package projeto.xablau.models;

import java.time.LocalDate;

public class Paciente {
    private String nome, cpf, telefone, email;
    private LocalDate nascimento;

    Paciente(String nome, String cpf, String telefone, String email, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.nascimento = nascimento;
    }

    // -- GETTERS & SETTERS ----
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    // -- METODOS ----
    public void cadastrar() {

    }

    public void atualizarDados(){

    }

    public void agendarConsulta() {

    }

    public void cancelarConsulta(Consulta consulta) {

    }

    public void consultarHistorico() {
        
    }
    
}
