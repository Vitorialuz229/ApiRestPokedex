package ufg.inf.cs.ApiRestPokedex.adapter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonAdapter {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResult> results;

    @Getter
    @Setter
    public static class PokemonResult {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class PokemonDetails {
        private String name;
        private int weight;
        private int height;
        private List<TypeInfo> types;

        @Getter
        @Setter
        public static class TypeInfo {
            private int slot;
            private Type type;

            @Getter
            @Setter
            public static class Type {
                private String name;
                private String url;
            }
        }
    }
}