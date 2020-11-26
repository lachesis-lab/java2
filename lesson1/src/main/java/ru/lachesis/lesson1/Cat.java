package ru.lachesis.lesson1;

public class Cat implements Movable {
    private static final String TYPE="Кот";
    private int maxRunningLength;
    private float maxJumpingHeight;

    public Cat(){};
    public Cat(int maxRunningLength,float maxJumpingHeight)
    {
        this.maxRunningLength=maxRunningLength;
        this.maxJumpingHeight=maxJumpingHeight;
    }
            ;
    @Override
    public boolean run(int length){
        if (length<0|| maxRunningLength <0){
            System.out.println("Неправильные данные");
            return false;
        }
        if (length <= maxRunningLength) {
            System.out.println(TYPE + " пробежал " + length + " м.");
            return true;
        }
        else {
            System.out.println(TYPE + " не смог пробежать " + length + " м.");
            return false;
        }
    }

    @Override
    public boolean jump(float height){
        if (height<0||maxJumpingHeight<0){
            System.out.println("Неправильные данные");
            return false;
        }
        if (height <= maxJumpingHeight) {
            System.out.println(TYPE + " перепрыгнул препятствие " + height + " м.");
            return true;
        }
        else {
            System.out.println(TYPE + " не смог перепрыгнуть препятствие " + height + " м.");
            return false;
        }

    }
}
