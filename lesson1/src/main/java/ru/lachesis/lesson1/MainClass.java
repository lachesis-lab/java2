package ru.lachesis.lesson1;

public class MainClass {
    public static void main(String[] args) {

        Movable[] somebodies = new Movable[4];
        somebodies[0] = new Human(5000,1.5F);
        somebodies[1] = new Cat(500,2F);
        somebodies[2] = new Robot(100000,3F);
        somebodies[3] = new Robot(10000,1F);

        Obstacle[] obstacles = new Obstacle[5];
        obstacles[0] = new Treadmill(1000);
        obstacles[1] = new Wall(2F);
        obstacles[2] = new Treadmill(10500);
        obstacles[3] = new Wall(2.1F);
        obstacles[4] = new Wall(2.5F);

        for (int i=0;i<somebodies.length;i++)
        {
            System.out.println("______________________");
            for (int j=0;j<obstacles.length;j++)
            {
                System.out.println("Участник под номером "+(i+1) +" проходит препятсвие номер "+(j+1)+":");
                if(!obstacles[j].overcomeObstacle(somebodies[i])){
                    System.out.println("Участник под номером "+(i+1) +" вылетел на препятсвии номер "+(j+1));
                    break;
                }
            }
        }
    }
}
