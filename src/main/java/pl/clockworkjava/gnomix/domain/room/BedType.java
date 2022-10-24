package pl.clockworkjava.gnomix.domain.room;

public enum BedType {
    SINGLE(1), DOUBLE(2);

    private int size;

    BedType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
