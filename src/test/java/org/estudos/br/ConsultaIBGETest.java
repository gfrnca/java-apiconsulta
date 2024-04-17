package org.estudos.br;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsultaIBGETest {


    @Test
    @DisplayName("Pegando o Status Code")
    public void testConsultarEstadoStatusCode() throws IOException {

        // Sigla do estado a ser consultado
        String estadoUf = "SP";

        // Cria uma conexão HTTP com a URL da API do IBGE para consultar informações sobre o estado "SP" vindo da variável {estadoUf}
        HttpURLConnection connection = (HttpURLConnection) new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + estadoUf).openConnection();
        // Define o método da requisição como GET
        connection.setRequestMethod("GET");
        // Obtém o status code da resposta da API
        int statusCode = connection.getResponseCode();

        // Verifica se o status code retornado é igual a 200 (OK) e dá uma msg de erro se não for
        assertEquals(200, statusCode, "O status code da resposta não é 200.");
    }
}