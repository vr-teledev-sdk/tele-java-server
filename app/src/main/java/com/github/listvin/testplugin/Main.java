package com.github.listvin.testplugin;

/**
 * @author korolyov
 *         14.08.16
 */
public class Main {
    public static void main(String[] args) {
        Server.start(new Commands() {
            int x = 0;
            @Override
            public void repaint() {
                System.out.println("ga!(" + (++x) + ")");
            }
        });
    }
}
