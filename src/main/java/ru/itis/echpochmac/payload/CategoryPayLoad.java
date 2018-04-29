package ru.itis.echpochmac.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryPayLoad {
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
