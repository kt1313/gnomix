package pl.clockworkjava.gnomix.domain.room;

public enum BedType {
    SINGLE(1), DOUBLE(2);

    private final Integer size;

    BedType(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public String toString(){
        return this.size.toString();
    }
}
