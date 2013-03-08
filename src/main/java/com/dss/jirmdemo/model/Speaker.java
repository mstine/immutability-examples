package com.dss.jirmdemo.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "speakers")
public final class Speaker {

    @Id
    private final String id;
    private final String name;
    private final String title;

    @JsonCreator
    public Speaker(@JsonProperty("id") String id,
                   @JsonProperty("name") String name,
                   @JsonProperty("title") String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speaker speaker = (Speaker) o;

        if (!id.equals(speaker.id)) return false;
        if (!name.equals(speaker.name)) return false;
        if (!title.equals(speaker.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
