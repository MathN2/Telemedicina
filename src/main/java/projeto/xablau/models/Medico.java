package projeto.xablau.models;

public class Medico {
    String nome, crm, especialidade, telefone, email;

    public Medico(String nome, String crm, String especialidade, String telefone, String email) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    // -- GETTERS & SETTERS ----
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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


    // -- METODOS ----
    public void cadastrar(){} // CADASTRAR QUEM ???

    public void atualizarDados() {
        // LER E ATUALIZAR DB
    }

    public void consultarAgenda(){
        // LER DB
    }

    public void realizarConsulta(){} // ???

    public void registrarProntuario(){
        // SALVAR DB
    }

    public void emitirPrescricao(){
        // LER E IMPRIMIR PRESCRICAO
    }
}
