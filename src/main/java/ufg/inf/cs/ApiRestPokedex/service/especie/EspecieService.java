package ufg.inf.cs.ApiRestPokedex.service.especie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.inf.cs.ApiRestPokedex.repository.especie.EspecieRepository;

@Service
public class EspecieService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    @Autowired
    private EspecieRepository especieRepository;

 /*   public List<Especie> listAllEspecieNamesAndTypes() throws IOException {
        String url = BASE_URL + "?limit=100";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            List<Especie> especies = new ArrayList<>();
            for (JsonElement element : results) {
                JsonObject especieJson = element.getAsJsonObject();
                String name = especieJson.get("name").getAsString();

                Especie especie = fetchEspecieDetails(name);
                especies.add(especie);
                System.out.println("Detalhes da espécie obtidos: " + especie.toString());
            }

            return especies;
        } finally {
            httpClient.close();
        }
    }

  /*  private Especie fetchEspecieDetails(String name) throws IOException {
        String url = BASE_URL + "/" + name;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
            Especie especie = new Especie();
            especie.setName(jsonObject.get("name").getAsString());

            if (jsonObject.has("types")) {
                List<String> typesList = new ArrayList<>();
                JsonArray typesArray = jsonObject.getAsJsonArray("types");
                for (JsonElement typeElement : typesArray) {
                    typesList.add(typeElement.getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString());
                }
                especie.setTypes(String.join(", ", typesList));
            }

            if (jsonObject.has("moves")) {
                List<String> movesList = new ArrayList<>();
                JsonArray movesArray = jsonObject.getAsJsonArray("moves");
                for (JsonElement moveElement : movesArray) {
                    movesList.add(moveElement.getAsJsonObject().get("move").getAsJsonObject().get("name").getAsString());
                }
                especie.setAttack(String.join(", ", movesList));
            }

            if (jsonObject.has("height")) {
                especie.setHeight(jsonObject.get("height").getAsInt());
            }

            if (jsonObject.has("weight")) {
                especie.setWeight(jsonObject.get("weight").getAsInt());
            }

            if (jsonObject.has("base_experience")) {
                especie.setBaseExperience(jsonObject.get("base_experience").getAsInt());
            }

            return especie;
        } finally {
            httpClient.close();
        }
    }


    public void addEspecieByName(String name) throws IOException {
        Especie especie = new Especie();
        especie.setName(name);

        especieRepository.save(especie);
    }

    public Especie getEspecieDetailsByName(String name) throws IOException {
        return fetchEspecieDetails(name);
    }*/
}
