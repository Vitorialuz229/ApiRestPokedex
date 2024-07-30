package ufg.inf.cs.ApiRestPokedex.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufg.inf.cs.ApiRestPokedex.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}