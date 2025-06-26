package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("totalSeasons") Integer totalSeason,
                        @JsonAlias("imdbRating") String assessment,
                        @JsonAlias("Genre") String gender,
                        @JsonAlias("Actors") String actors,
                        @JsonAlias("Poster") String poster,
                        @JsonAlias("Plot") String synopsis) {
}
