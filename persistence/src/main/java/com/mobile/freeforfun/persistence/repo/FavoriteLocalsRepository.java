package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.FavoriteLocalsCompositeKey;
import com.mobile.freeforfun.persistence.model.FavouriteLocals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteLocalsRepository extends JpaRepository<FavouriteLocals, FavoriteLocalsCompositeKey> {

}
