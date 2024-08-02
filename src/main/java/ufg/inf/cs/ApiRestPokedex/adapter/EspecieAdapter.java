package ufg.inf.cs.ApiRestPokedex.adapter;

import lombok.Getter;
import lombok.Setter;
import ufg.inf.cs.ApiRestPokedex.adapter.apiAdapter.Abilities;
import ufg.inf.cs.ApiRestPokedex.adapter.apiAdapter.Moves;
import ufg.inf.cs.ApiRestPokedex.adapter.apiAdapter.Stats;
import ufg.inf.cs.ApiRestPokedex.adapter.apiAdapter.Type;

import java.util.List;

@Getter
@Setter
public class EspecieAdapter {
    private String name;
    private List<Type> types;
    private List<Abilities> abilities;
    private int height;
    private int weight;
    private int base_experience;
    private List<Moves> moves;
    private List<Stats> stats;
}
