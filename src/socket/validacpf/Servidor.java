package socket.validacpf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servidor {
    private static Socket socket;
    private static ServerSocket server;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        try{
            // Porta que será utilizada pelo ServerSocket
            server = new ServerSocket(50000);
            socket = server.accept();

            // Fluxos de entrada/saída
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            // Recebimento do valor inteiro
            String cpf = entrada.readUTF();

            // processamento do valor
            System.out.println("CPF informado: " + cpf);
            if (isCpfValido(cpf)) {
                saida.writeUTF(String.valueOf(isCpfValido(cpf)));
            } else {
                saida.writeUTF("Formato invalido!\n\nMensagem: O numero de CPF deve conter 11 digitos (Ex: 111.222.333-00)");
            }
            // envio dos dados (resultado)
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isCpfValido(String cpf) {
        String CPFRegex = "^([0-9]{3}\\.?){3}-?[0-9]{2}$";
        Pattern p = Pattern.compile(CPFRegex);
        Matcher m = p.matcher(cpf);

        if (!m.matches()) {
            return false;
        } else {
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");

            int n1, n2;
            int dig1, dig2, resto, digCpf;
            String resultDigit;

            n1 = n2 = 0;
            dig1 = dig2 = resto = 0;

            for (int i = 1; i < cpf.length() - 1; i++) {
                digCpf = Integer.valueOf(cpf.substring(i-1, i)).intValue();
                n1 = n1 + (11 - i) * digCpf;
                n2 = n2 + (12 - i) * digCpf;
            };

            resto = (n1 % 11);
            dig1 = resto > 2 ? 11 - resto : 0;

            n2 = n2 + 2 * dig1;

            resto = (n2 % 11);
            dig2 = resto > 2 ? 11 - resto : 0;

            String verificador = cpf.substring(cpf.length() - 2, cpf.length());
            String resultVerificador = String.valueOf(dig1) + String.valueOf(dig2);

            return verificador.equals(resultVerificador);
        }
    }
}
