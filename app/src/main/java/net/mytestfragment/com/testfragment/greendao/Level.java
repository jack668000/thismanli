package net.mytestfragment.com.testfragment.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Level {
    @Id
    private long id;
    private String lev;
    @Generated(hash = 1580501559)
    public Level(long id, String lev) {
        this.id = id;
        this.lev = lev;
    }
    @Generated(hash = 723561372)
    public Level() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLev() {
        return this.lev;
    }
    public void setLev(String lev) {
        this.lev = lev;
    }
}
