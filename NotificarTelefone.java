public class NotificarTelefone implements Notificacao {
    private String telefone;

    public NotificarTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS para " + telefone + ": " + mensagem);
    }
}
