package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {

    static String token;

    @Test
    public void validarLogin(){
        RestUtils.setBaseURI("http://localhost:8080/");

        String json = "{" +
                "    \"email\": \"aluno@email.com\"," +
                "    \"senha\": \"123456\"" +
                "}";

        RestUtils.post(json, ContentType.JSON, "auth");

        assertEquals(200, RestUtils.getResponse().statusCode());
        String token = RestUtils.getResponse().jsonPath().get("token");

    }

    @BeforeAll
    public static void validarLoginMap(){
        RestUtils.setBaseURI("http://localhost:8080/");
        Map<String, String> map = new HashMap<>();
        map.put("email", "aluno@email.com");
        map.put("senha", "123456");

        Response response = RestUtils.post(map, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");

    }

    @Test
    public void validarConsultaCategoria(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+token);
        Response response = RestUtils.get(header, "categorias");
        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror", response.jsonPath().get("tipo[2]"));
        List<String> listTipo = response.jsonPath().get("tipo");
        assertTrue(listTipo.contains("Terror"));
    }

    @Test
    public void teste(){
        String[] vetorCompras = {"arroz", "feijao", "cerveja", "carne"};
        System.out.println(vetorCompras[1]);

        List<String> listaCompras = new ArrayList<>();
        listaCompras.add("arroz");
        listaCompras.add("feijao");
        listaCompras.add("cerveja");
        listaCompras.add("carne");
        listaCompras.add("whisky");

        System.out.println(listaCompras.get(4));
    }





}
