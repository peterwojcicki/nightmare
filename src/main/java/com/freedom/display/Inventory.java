package com.freedom.display;

import com.freedom.model.Drawable;
import com.freedom.model.Player;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;

public class Inventory extends Drawable {

    private Player player;

    public Inventory(Player player) {
        super(Integer.MAX_VALUE);
        this.player = player;
    }

    @Override
    public void draw(Pencil pencil) {
        pencil.setForegroundColor(TextColor.ANSI.RED);
        for (int x = 0; x < player.getHealth() / 10; x++) {
            pencil.moveToAbsolute(pencil.getTerminalSize().getColumns() - 20 + x*2, 0);
            pencil.print(Symbols.HEART + " ");
        }


    }
}
