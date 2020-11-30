package ru.lachesis.lesson1;

public class Wall implements Obstacle {
    private float size;

    public Wall(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    @Override
    public boolean overcomeObstacle(Movable somebody) {
        return somebody.jump(size);
    }
}
