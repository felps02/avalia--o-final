public class Participante extends Pessoa {
    private String telefone;
    private Notificacao notifica;

    // Getters e Setters
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        this.notifica = new NotificarTelefone(telefone);
    }

    public Notificacao getNotifica() {
        return notifica;
    }
}
