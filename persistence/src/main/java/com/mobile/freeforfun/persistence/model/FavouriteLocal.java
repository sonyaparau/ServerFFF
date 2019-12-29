package com.mobile.freeforfun.persistence.model;

import com.mobile.freeforfun.persistence.enums.EVoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="FavouriteLocal")
@Table(name="favouritelocals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteLocal {

    @EmbeddedId
    private FavouriteLocalCompositeKey id;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_favouritelocals"),
            name = "id_user")
    private User user;

    @ManyToOne
    @MapsId("id_local")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_local_favouritelocals"),
            name = "id_local")
    private Local local;

    @Column(name = "vote")
    private EVoteType voteType;
}
