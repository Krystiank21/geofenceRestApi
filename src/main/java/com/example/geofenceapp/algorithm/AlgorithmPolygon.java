package com.example.geofenceapp.algorithm;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

public class AlgorithmPolygon {

    public final List<AlgorithmPoint> algorithmPoints;

    // outer rectangular bounds of polygon
    public final Rectangle bounds;

    public AlgorithmPolygon(final AlgorithmPoint... algorithmPoints) {
        this.algorithmPoints = Arrays.asList(algorithmPoints);
        this.bounds = calculateMinMaxBounds(this.algorithmPoints);
    }

    public AlgorithmPolygon(final List<AlgorithmPoint> algorithmPoints) {
        this.algorithmPoints = algorithmPoints;
        this.bounds = calculateMinMaxBounds(this.algorithmPoints);
    }

    private static Rectangle calculateMinMaxBounds(final List<AlgorithmPoint> algorithmPoints) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (final AlgorithmPoint algorithmPoint : algorithmPoints) {
            if (algorithmPoint.getX() < minX) {
                minX = algorithmPoint.getX();
            }
            if (algorithmPoint.getY() < minY) {
                minY = algorithmPoint.getY();
            }
            if (algorithmPoint.getX() > maxX) {
                maxX = algorithmPoint.getX();
            }
            if (algorithmPoint.getY() > maxY) {
                maxY = algorithmPoint.getY();
            }
        }

        ///TODO fix roundings

        return new Rectangle((int) Math.round(minX), (int) Math.round(minY), (int) Math.round(maxX - minX), (int) Math.round(maxY - minY));
    }
}