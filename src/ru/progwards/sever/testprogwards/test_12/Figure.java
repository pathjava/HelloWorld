package ru.progwards.sever.testprogwards.test_12;

public class Figure {
    public String figDetect(Figure fig) {
        if (fig == null)
            return "Неизвестная фигура";
        if (fig.getClass() == Square.class)
            return "Сторона квадрата " + ((Square) fig).getSide();
        if (fig.getClass() == Round.class)
            return "Диаметр круга " + ((Round) fig).getDiameter();
        return "Неизвестная фигура";
    }
}

class Square extends Figure {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }
}

class Round extends Figure {
    private double diameter;

    public Round(double diameter) {
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }
}