package org.estudos.br;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Classe para consulta de informações de estados brasileiros na API do IBGE.
 */
public class ConsultaIBGE {

    // URL base da API do IBGE
    private static final String API_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/";

    /**
     * Método para consultar informações de um estado específico.
     *
     * @param uf A sigla do estado a ser consultado.
     * @return Uma string contendo a resposta da API.
     * @throws IOException Se houver algum erro de conexão ou leitura.
     */
    public static String consultarEstado(String uf) throws IOException {
        // Monta a URL completa para consulta do estado específico
        URL url = new URL(API_URL + "estados/" + uf);

        // Abre uma conexão HTTP com a URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define o método da requisição como GET
        connection.setRequestMethod("GET");

        // Prepara para ler a resposta da conexão
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        // Lê cada linha da resposta e a adiciona ao StringBuffer
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Retorna a resposta da API como uma string
        return response.toString();
    }
}
