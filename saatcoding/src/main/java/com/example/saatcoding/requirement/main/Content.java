package com.example.saatcoding.requirement.main;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SequenceGenerator(
            name = "content_sequence",
            sequenceName = "content_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contentSequence"
    )
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String posterUrl;
    private String videoUrl;

    @ManyToMany(targetEntity = License.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "content_licenses",
            joinColumns = @JoinColumn(name = "content_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "license_id", nullable = false, updatable = false)
    )
    private List<License> licenses = new ArrayList<>();

    public Content() {

    }

    public Content(Long id,
                   List<License> licenses,
                   String name,
                   Status status,
                   String posterUrl,
                   String videoUrl) {
        this.id = id;
        this.licenses = licenses;
        this.name = name;
        this.status = status;
        this.posterUrl = posterUrl;
        this.videoUrl = videoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", licenses=" + licenses +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", posterUrl='" + posterUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content content)) return false;

        if (getId() != null ? !getId().equals(content.getId()) : content.getId() != null) return false;
        if (getLicenses() != null ? !getLicenses().equals(content.getLicenses()) : content.getLicenses() != null)
            return false;
        if (getName() != null ? !getName().equals(content.getName()) : content.getName() != null) return false;
        if (getStatus() != content.getStatus()) return false;
        if (getPosterUrl() != null ? !getPosterUrl().equals(content.getPosterUrl()) : content.getPosterUrl() != null)
            return false;
        return getVideoUrl() != null ? getVideoUrl().equals(content.getVideoUrl()) : content.getVideoUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLicenses() != null ? getLicenses().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getPosterUrl() != null ? getPosterUrl().hashCode() : 0);
        result = 31 * result + (getVideoUrl() != null ? getVideoUrl().hashCode() : 0);
        return result;
    }
}
