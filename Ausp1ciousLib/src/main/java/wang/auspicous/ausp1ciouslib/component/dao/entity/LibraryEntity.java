package wang.auspicous.ausp1ciouslib.component.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class LibraryEntity {
    @Id
    private Long id;

    @Generated(hash = 1104730624)
    public LibraryEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 1246794706)
    public LibraryEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
