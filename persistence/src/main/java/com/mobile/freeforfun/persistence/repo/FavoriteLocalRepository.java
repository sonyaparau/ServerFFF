package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.FavouriteLocalCompositeKey;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteLocalRepository extends JpaRepository<FavouriteLocal, FavouriteLocalCompositeKey> {
}
