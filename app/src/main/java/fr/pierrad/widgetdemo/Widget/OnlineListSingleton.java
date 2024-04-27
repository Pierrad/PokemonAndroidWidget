package fr.pierrad.widgetdemo.Widget;

import java.util.ArrayList;
import java.util.List;

import fr.pierrad.widgetdemo.Pokemon.Pokemon;

public class OnlineListSingleton {
    private static OnlineListSingleton instance = null;
    private List<Pokemon> pokemons;

    private OnlineListSingleton() {
        pokemons = new ArrayList<>();
    }

    public static OnlineListSingleton getInstance() {
        if (instance == null) {
            instance = new OnlineListSingleton();
        }
        return instance;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

}
