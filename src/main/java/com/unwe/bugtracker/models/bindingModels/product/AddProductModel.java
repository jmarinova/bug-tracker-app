package com.unwe.bugtracker.models.bindingModels.product;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class AddProductModel {

    @NotNull(message = "Name cannot be null!")
    @Size(min = 3, max = 30, message = "Invalid name!")
    private String name;

    @NotNull(message = "Version cannot be null!")
    @Size(min = 1, max = 10, message = "Invalid version!")
    private String version;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
