package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.EVoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="FavouriteLocals")
@Table(name = "favourite_locals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteLocals {
    @EmbeddedId
    private FavoriteLocalsCompositeKey id;
//    @ManyToOne
//    @MapsId("id_user")
//    @JoinColumn(name = "id_user")
//    User user;
//
//    @ManyToOne
//    @MapsId("id_local")
//    @JoinColumn(name = "id_local")
//    Local local;

    @Column(name = "vote")
    private EVoteType voteType;

//    public FavouriteLocals(User user, Local local){
//        this.local =local;
//        this.user = user;
//        this.id = new FavoriteLocalsCompositeKey(user.getId(),local.getId());
//    }
}
