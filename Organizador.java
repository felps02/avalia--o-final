public class Organizador extends Pessoa {
    private String email;
    private Notificacao notifica;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.notifica = new NotificarEmail(email);
    }

    public Notificacao getNotifica() {
        return notifica;
    }
}
