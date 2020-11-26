package ru.lachesis.lesson1;

public class Treadmill implements Obstacle {
    private int size;
    public Treadmill( int size) {
        this.size=size;
    }
    public float getSize() {
        return (int)size;
    }

    @Override
    public boolean overcomeObstacle(Movable somebody) {
        return somebody.run((int)size);
    }
}
