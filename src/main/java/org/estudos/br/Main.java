package org.estudos.br;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à consulta de estados do IBGE!");
        System.out.println("Digite a sigla de um estado para consultar suas informações:");

        String uf = scanner.nextLine().toUpperCase();

        try {
            String resultado = ConsultaIBGE.consultarEstado(uf);
            System.out.println("Resultado da consulta:");
            System.out.println(resultado);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao realizar a consulta: " + e.getMessage());
        }

        scanner.close();
    }
}