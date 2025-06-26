package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double assessment;
    private LocalDate releaseDate;

    public Episode(Integer numeroTemporada, EpisodeData episodeData) {
        this.season = numeroTemporada;
        this.title = episodeData.title();
        this.episodeNumber = episodeData.number();

        try {
            this.assessment = Double.valueOf(episodeData.assessment());
        } catch (NumberFormatException ex) {
            this.assessment = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(episodeData.releaseDate());
        } catch (DateTimeParseException ex) {
            this.releaseDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "temporada=" + season +
                ", titulo='" + title + '\'' +
                ", numeroEpisodio=" + episodeNumber +
                ", avaliacao=" + assessment +
                ", dataLancamento=" + releaseDate;
    }
}
