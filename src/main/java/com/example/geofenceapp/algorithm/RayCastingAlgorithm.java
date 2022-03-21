package com.example.geofenceapp.algorithm;

import lombok.AllArgsConstructor;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 * The even-odd rule
 * odd = in polygon
 * even = outside polygon
 */
@AllArgsConstructor
public class RayCastingAlgorithm {

    private final AlgorithmPoint rayStartPoint;

    public boolean contains(final AlgorithmPoint point, final AlgorithmPolygon algorithmPolygon) {
        if (algorithmPolygon.algorithmPoints.size() < 3) {
            throw new IllegalStateException("Polygons must have at least 3 points.");
        }
        if (!contains(algorithmPolygon.bounds, point)) {
            return false;
        }

        int intersections = 0;
        // walk all vertices of algorithmPolygon
        for (int i = 0; i < algorithmPolygon.algorithmPoints.size() - 1; i++) {
            if (isIntersects(point, algorithmPolygon.algorithmPoints.get(i), algorithmPolygon.algorithmPoints.get(i + 1))) {
                intersections++;
            }
        }

        if (isIntersects(point,
                algorithmPolygon.algorithmPoints.get(algorithmPolygon.algorithmPoints.size() - 1),
                algorithmPolygon.algorithmPoints.get(0))) {
            intersections++;
        }
        return intersections % 2 != 0; // odd-in / even-out
    }

    private boolean isIntersects(final AlgorithmPoint testPoint, final AlgorithmPoint p1, final AlgorithmPoint p2) {
        return Line2D.linesIntersect(
                rayStartPoint.getX(), rayStartPoint.getY(),
                testPoint.getX(), testPoint.getY(),
                p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private boolean contains(final Rectangle rectangle, final AlgorithmPoint point) {
        return point.getX() >= rectangle.getX() &&
                point.getY() >= rectangle.getY() &&
                point.getX() <= rectangle.getX() + rectangle.getWidth() &&
                point.getY() <= rectangle.getY() + rectangle.getHeight();
    }
}