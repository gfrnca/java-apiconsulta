package org.estudos.br;

import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principal para interação com o usuário e consulta de informações de estados brasileiros.
 */
public class Main {

    /**
     * Método principal para execução do programa.
     *
     * @param args Argumentos da linha de comando (não utilizados neste programa).
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler entrada do usuário a partir do console
        Scanner scanner = new Scanner(System.in);

        // Imprime uma mensagem de boas-vindas e instruções para o usuário
        System.out.println("Bem-vindo à consulta de estados do IBGE!");
        System.out.println("Digite a sigla de um (ou mais) estado(s) para consultar suas informações, separados por pipe '|': ");

        // Lê a entrada do usuário e converte para letras maiúsculas
        String uf = scanner.nextLine().toUpperCase();

        try {
            // Chama o método consultarEstado da classe ConsultaIBGE para obter informações do estado(s)
            String resultado = ConsultaIBGE.consultarEstado(uf);

            // Imprime o resultado da consulta
            System.out.println("Resultado da consulta: ");
            System.out.println(resultado);
        } catch (IOException e) {
            // Trata exceção caso ocorra um erro durante a consulta
            System.err.println("Ocorreu um erro ao realizar a consulta: " + e.getMessage());
        }

        // Fecha o objeto Scanner para liberar recursos
        scanner.close();
    }
}