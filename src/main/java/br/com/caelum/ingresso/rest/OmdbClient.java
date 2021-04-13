package br.com.caelum.ingresso.rest;

import org.springframework.stereotype.Component;

import java.util.Optional;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.DetalhesDoFilme;

import org.springframework.web.client.RestClientException;

import org.springframework.web.client.RestTemplate;

@Component
public class OmdbClient {
    public Optional<DetalhesDoFilme> request(Filme filme){
        RestTemplate client = new RestTemplate();
        String titulo = filme.getNome().replace(" ", "+");
        String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
        try {
            DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
            return Optional.ofNullable(detalhesDoFilme);
        }catch (RestClientException e){
            return Optional.empty();
        }
    }
}