# UTFPR - Especialização em Tecnologia Java
## Disciplina: Java Aplicado à Redes de Computadores 
### Módulo 02 - Atividade 01

---

#### Descrição:
Faça um programa baseado em Sockets Java capaz onde:

a) A máquina cliente enviará um CPF para o servidor;
b) O servidor receberá o CPF e verificará se o número é válido ou não; caso verdadeiro deve retornar TRUE, caso contrário, FALSE.

Sugestão link para método de validação de CPF: [Clique aqui](http://www.hardware.com.br/comunidade/tutorial-cpf/1261362/)

---

Nesta atividade foi solicitada a criação de um servidor e um cliente utilizando Sockets para validar um CPF digitado no 
servidor e a resposta sendo retornada no cliente. Foram criadas apenas duas classes (Servidor e Cliente) que farão todo 
o serviço. 

Para a validação do CPF utilizei inicialmente um REGEX para validar se a String de entrada contém o formato de CPF. 
Caso positivo, o programa faz a remoção da pontuação ("." e "-") caso tenha sido digitada e dará sequência no fluxo de 
validação da numeração em si, incluindo o dígido.
