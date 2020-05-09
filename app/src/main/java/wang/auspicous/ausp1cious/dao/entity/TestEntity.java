package wang.auspicous.ausp1cious.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import wang.auspicous.ausp1ciouslib.component.bean.BaseBean;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class TestEntity extends BaseBean {
    @Id
    private Long id;

    @Generated(hash = 2079041192)
    public TestEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 1020448049)
    public TestEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
