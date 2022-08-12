package lv.marmog.zone2.zone2.models;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;


@MappedSuperclass
public abstract class UniqueEntity {
    protected UUID uuid;

    public UniqueEntity(UUID uuid){this.uuid = uuid;}

    public int hashCode(){ return this.uuid == null ? 0 : this.uuid.hashCode(); }

    public boolean equals (Object o ){
        if(this == o){
            return true;
        } else if (o == null){
            return false;
        } else if (!UniqueEntity.class.isAssignableFrom(o.getClass())){
            return false;
        } else {
            UniqueEntity entity = (UniqueEntity) o;
            return this.uuid.equals(entity.uuid);
        }
    }

    @PrePersist
    protected void onCreate(){
        if(this.uuid == null){
            this.uuid = UUID.randomUUID();
        }
    }

    public UUID getUuid(){ return  this.uuid; }

    public UniqueEntity(){
    }
    public String toString(){ return "UniqueEntity(uuid=" + this.getUuid() + ")"; }

}
