package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import maps.FilmesMap;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class FilmeSteps {

    @Dado("que tenha um payload da API de Filme")
    public void queTenhaUmPayloadDaAPIDeFilme() {
        FilmesMap.initAll();
    }
    @Quando("realizo uma requisicao do tipo POST de Filme")
    public void realizoUmaRequisicaoDoTipoPOSTDeFilme() {
        RestUtils.post(FilmesMap.getHeader(), FilmesMap.getFilme(), ContentType.JSON, "filmes");
    }
    @Entao("armazeno o id que recebo do response de Filme")
    public void armazenoOIdQueReceboDoResponseDeFilme() {
        FilmesMap.id = RestUtils.getResponse().jsonPath().get("id");
    }

    @Quando("realizo uma requisicao do tipo GET de Filme")
    public void realizoUmaRequisicaoDoTipoGETDeFilme() {
        Map<String, Object> param = new HashMap<>();
        String nome = FilmesMap.getFilme().get("nome").toString();
        param.put("nome", nome);
        RestUtils.get(FilmesMap.getHeader(), param, "filmes");

    }

    @Dado("altero o indice {int} da lista de categorias de filme com os valores")
    public void alteroOIndiceDaListaDeCategoriasDeFilmeComOsValores(int indice,
                                                                    Map<String, Object> map) {
        FilmesMap.getListCategoria().get(indice).putAll(map);
    }
    @Quando("realizo uma requisicao do tipo PUT de Filme")
    public void realizoUmaRequisicaoDoTipoPUTDeFilme() {
        RestUtils.put(FilmesMap.getHeader(), FilmesMap.getFilme(),
                ContentType.JSON, "filmes/"+FilmesMap.id);
    }
}
