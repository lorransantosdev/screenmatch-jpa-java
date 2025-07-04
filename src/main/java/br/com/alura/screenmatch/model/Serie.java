package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.GroqTranslateService;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private Integer totalSeason;
    private Double assessment;

    @Enumerated(EnumType.STRING)
    private Category genders;
    private String actors;
    private String poster;
    private String synopsis;

    @Transient
    private List<Episode> episodes;

    public Serie() { }

    public Serie(SerieData serieData) {
        this.title = serieData.title();
        this.totalSeason = serieData.totalSeason();
        this.assessment = OptionalDouble.of(Double.valueOf(serieData.assessment())).orElse(0);
        this.genders = Category.fromString(serieData.gender().split(",")[0].trim());
        this.actors = serieData.actors();
        this.poster = serieData.poster();
        this.synopsis = GroqTranslateService.translatorText(serieData.synopsis()).trim();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeason() {
        return totalSeason;
    }

    public void setTotalSeason(Integer totalSeason) {
        this.totalSeason = totalSeason;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public Category getGenders() {
        return genders;
    }

    public void setGenders(Category genders) {
        this.genders = genders;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "genders=" + genders +
                ", title='" + title + '\'' +
                ", totalSeason=" + totalSeason +
                ", assessment=" + assessment +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", synopsis='" + synopsis + '\'';
    }
}
