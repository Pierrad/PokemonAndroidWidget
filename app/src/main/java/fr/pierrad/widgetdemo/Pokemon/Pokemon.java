package fr.pierrad.widgetdemo.Pokemon;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("pokedexId")
    @Expose
    private int pokedexId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    public Pokemon(int pokedexId, String name, String image) {
        this.pokedexId = pokedexId;
        this.name = name;
        this.image = image;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    public Uri getImageUri() {
        return Uri.parse(image);
    }

}
