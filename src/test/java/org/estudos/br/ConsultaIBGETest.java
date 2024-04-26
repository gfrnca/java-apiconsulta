package org.estudos.br;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsultaIBGETest {
    private static final String ESTADOS_API_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";


    @Test
    @DisplayName("Teste para consulta única de um estado")
    public void testConsultarEstado() throws IOException {
        // Arrange
        String uf = "SP"; // Define o estado a ser consultado

        // Act
        String resposta = ConsultaIBGE.consultarEstado(uf); // Chama o método a ser testado

        // Assert
        // Verifica se a resposta não está vazia
        assert !resposta.isEmpty();

        // Verifica se o status code é 200 (OK)
        HttpURLConnection connection = (HttpURLConnection) new URL(ESTADOS_API_URL + uf).openConnection();
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode, "O status code da resposta da API deve ser 200 (OK)");
    }

    @Test
    @DisplayName("Teste para opção inválida no menu")
    public void testInvalidOption() {
        InputStream input = new ByteArrayInputStream("5".getBytes());

        System.setIn(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        Main.main(new String[]{});

        String stringfiedOutput = output.toString();

        assertTrue(stringfiedOutput.contains("Opção inválida."));
    }

    @Test
    @DisplayName("Teste para consulta de distrito")
    public void testDistrict() throws IOException {
        int idToBeConsulted = 310010405;

        String consultedIdResponseJson = "[{\"id\":310010405,\"nome\":\"Abadia dos Dourados\",\"municipio\":{\"id\":3100104,\"nome\":\"Abadia dos Dourados\",\"microrregiao\":{\"id\":31019,\"nome\":\"Patrocínio\",\"mesorregiao\":{\"id\":3105,\"nome\":\"Triângulo Mineiro/Alto Paranaíba\",\"UF\":{\"id\":31,\"sigla\":\"MG\",\"nome\":\"Minas Gerais\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}}}},\"regiao-imediata\":{\"id\":310061,\"nome\":\"Monte Carmelo\",\"regiao-intermediaria\":{\"id\":3111,\"nome\":\"Uberlândia\",\"UF\":{\"id\":31,\"sigla\":\"MG\",\"nome\":\"Minas Gerais\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}}}}}}]";

        HttpURLConnection requestMockConnection = mock(HttpURLConnection.class);

        when(requestMockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(consultedIdResponseJson.getBytes()));

        when(requestMockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        String res = ConsultaIBGE.consultarDistrito(idToBeConsulted);

        assertEquals(res, consultedIdResponseJson);
    }

}