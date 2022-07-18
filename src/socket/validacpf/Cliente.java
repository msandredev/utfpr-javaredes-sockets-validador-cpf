package socket.validacpf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    private static Socket socket;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 50000);
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            // Solicitado a digitação do CPF
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Digite o CPF: ");
            String cpf = br.readLine();

            // Saída do CPF digitado
            saida.writeUTF(cpf);

            // Resultado do servidor
            String resultado = entrada.readUTF();

            if (resultado.equals("true")) {
                System.out.println("CPF valido (" + resultado + ")");
            } else {
                System.out.println("CPF invalido!\n\n" + resultado);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
