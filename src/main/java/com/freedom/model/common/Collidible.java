package com.freedom.model.common;

import com.freedom.model.weapons.Projectile;

public interface Collidible {

    Point getUpperLeft();

    Point getLowerRight();

    Point getPosition();

    default boolean isTouchingVerticallyFromAbove(Collidible other) {
        return getLowerRight().getY() + 1 == other.getUpperLeft().getY();
    }

    default boolean isTouchingVerticallyFromBelow(Collidible other) {
        return other.getLowerRight().getY() + 1 == getUpperLeft().getY();
    }

    default boolean isTouchingHorizontallyFromLeft(Collidible other) {
        return getLowerRight().getX() + 1 == other.getUpperLeft().getX();
    }

    default boolean isTouchingHorizontallyFromRight(Collidible other) {
        return getLowerRight().getX() == other.getUpperLeft().getX() + 1;
    }

    default int getWidth() {
        return getLowerRight().getX() - getUpperLeft().getX() + 1;
    }

    default int getHeight() {
        return getLowerRight().getY() - getUpperLeft().getY() + 1;
    }

    default void hitByProjectile(Projectile projectile) {

    }
}
