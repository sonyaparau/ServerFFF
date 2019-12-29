package com.mobile.freeforfun.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * composite key for join columns id_user,id_local from table
 * m:m "favouritelocals"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FavoriteLocalsCompositeKey implements Serializable {
    @Column(name = "id_user")
    private Long userId;
    @Column(name = "id_local")
    private Long localId;
}
