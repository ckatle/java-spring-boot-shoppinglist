package de.ckle.shoppinglist.repository;

import de.ckle.shoppinglist.domain.ShoppinglistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppinglistEntry, String> {
}