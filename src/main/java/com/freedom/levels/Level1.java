package com.freedom.levels;

import com.freedom.display.Sky;
import com.freedom.model.*;
import com.freedom.sound.Audio;

public class Level1 extends Level {
    public Level1() {
        super("Undead City", new Point(30, 5));
    }

    @Override
    void init() {

        new Thread(() -> new Audio().playIndefinitely("sounds/forest.wav")).start();

        add(new Flat2(new Point(29, -18)));
        add(new Tree1(new Point(8, -13)));
        add(new Sky());
//        add(new Grass());
        add(new Ground(new Point(-2, 1), 20, 1));
        add(new Ground(new Point(15, 5), 40, 1));
        for (int i = 0; i < 10; i++) {
            add(new Clouds(new Point(-100 + 80 * i, -20 + 2 * (i % 2))));
        }
    }
}