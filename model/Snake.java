package com.mycompany.snakegame.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Snake extends GameObject implements Resettable {

    private final Deque<Cell> body = new LinkedList<>();
    private final Cell start;
    private final int startLength;
    private Direction direction = Direction.RIGHT;

    public Snake(Cell start, int startLength) {
        super(start);
        this.start = start;
        this.startLength = startLength;
        placeAtStart();
    }

    @Override
    public final void reset() {
        placeAtStart();
    }

    private void placeAtStart() {
        body.clear();
        direction = Direction.RIGHT;
        for (int i = 0; i < startLength; i++) {         
            body.addLast(new Cell(start.col() - i, start.row()));
        }
        setPosition(body.peekFirst());
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if (newDirection == null || newDirection == direction.opposite()) {
            return;
        }
        direction = newDirection;
    }

    public Cell peekNextHead() {
        return getPosition().step(direction);
    }

    public void advance(boolean grow) {
        Cell next = peekNextHead();
        body.addFirst(next);
        if (!grow) {
            body.pollLast();
        }
        setPosition(next);
    }

    public boolean hitsSelf() {
        Iterator<Cell> it = body.iterator();          
        Cell head = it.next();
        while (it.hasNext()) {                          
            if (it.next().equals(head)) {
                return true;
            }
        }
        return false;
    }

    public Collection<Cell> getCells() {
        return Collections.unmodifiableCollection(body);
    }

    public int length() {
        return body.size();
    }

    @Override
    public void draw(Graphics2D g, int cell) {
        Iterator<Cell> it = body.descendingIterator(); 
        int index = body.size() - 1;
        int size = cell - 4;
        while (it.hasNext()) {
            Cell c = it.next();
            int x = c.col() * cell + 2;
            int y = c.row() * cell + 2;
            g.setColor(new Color(0, 0, 0, 35));
            g.fillRoundRect(x + 2, y + 3, size, size, 12, 12);
            g.setColor(index == 0 ? new Color(25, 125, 45) : new Color(55, 170, 65));
            g.fillRoundRect(x, y, size, size, 12, 12);
            g.setColor(new Color(125, 220, 100, 150));
            g.fillOval(x + 5, y + 4, 5, 5);
            index--;
        }
        drawFace(g, cell);
    }

    private void drawFace(Graphics2D g, int cell) {
        int hx = getPosition().col() * cell;
        int hy = getPosition().row() * cell;
        int cx = hx + cell / 2;
        int cy = hy + cell / 2;
        
        int[][] eyes = switch (direction) {
            case UP -> new int[][]{{hx + 6, hy + 6}, {hx + 15, hy + 6}};
            case DOWN -> new int[][]{{hx + 6, hy + 15}, {hx + 15, hy + 15}};
            case LEFT -> new int[][]{{hx + 6, hy + 6}, {hx + 6, hy + 15}};
            case RIGHT -> new int[][]{{hx + 15, hy + 6}, {hx + 15, hy + 15}};
        };
        for (int[] eye : eyes) {
            g.setColor(Color.WHITE);
            g.fillOval(eye[0], eye[1], 4, 4);
            g.setColor(Color.BLACK);
            g.fillOval(eye[0] + 1, eye[1] + 1, 2, 2);
        }

        int dx = direction.dx();
        int dy = direction.dy();
        int bx = cx + dx * (cell / 2);
        int by = cy + dy * (cell / 2);
        int mx = cx + dx * (cell / 2 + 5);
        int my = cy + dy * (cell / 2 + 5);
        g.setColor(new Color(220, 35, 80));
        g.setStroke(new BasicStroke(2));
        g.drawLine(bx, by, mx, my);
        g.drawLine(mx, my, mx + dx * 4 - dy * 3, my + dy * 4 + dx * 3);
        g.drawLine(mx, my, mx + dx * 4 + dy * 3, my + dy * 4 - dx * 3);
    }
}
