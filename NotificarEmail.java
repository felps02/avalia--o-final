public class NotificarEmail implements Notificacao {
    private String email;

    public NotificarEmail(String email) {
        this.email = email;
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando email para " + email + ": " + mensagem);
    }
}
