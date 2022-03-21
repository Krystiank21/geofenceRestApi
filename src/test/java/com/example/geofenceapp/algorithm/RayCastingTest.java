package com.example.geofenceapp.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RayCastingTest {
    // start ray casting from a fairly random start point to avoid vertex overlap
    // for trivial test cases. vertex overlap is much less likely in real geo cases,
    // and should occur infrequently enough to ignore.
    private static final RayCastingAlgorithm RAY_CASTING = new RayCastingAlgorithm(
            new AlgorithmPoint(-1.123456789, -1.987654321));

    @Test
    public void square() {
        final AlgorithmPolygon square = new AlgorithmPolygon(
                new AlgorithmPoint(1.0, 1.0),
                new AlgorithmPoint(2.0, 1.0),
                new AlgorithmPoint(2.0, 2.0),
                new AlgorithmPoint(1.0, 2.0));

        // center of square
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(1.5, 1.5), square));

        // other side of square
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(2.5, 1.5), square));
    }

    //TODO ZEPUSTE
    //For parsing to int and Round() check
    @Test
    public void square2() {
        final AlgorithmPolygon square = new AlgorithmPolygon(
                new AlgorithmPoint(-3.4, 1.8),
                new AlgorithmPoint(-3.4, -2.6),
                new AlgorithmPoint(1.7, 1.8),
                new AlgorithmPoint(1.7, -2.6));

        // center of square
        //assertTrue(RAY_CASTING.contains(new Point(-3.2, -2.55), square));

        // other side of square
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(-3.5, -2.6), square));
    }

    @Test
    public void weirdShape() {
        // the ultimate shape, 凹
        final AlgorithmPolygon 凹 = new AlgorithmPolygon(
                new AlgorithmPoint(1.0, 1.0),
                new AlgorithmPoint(4.0, 1.0),
                new AlgorithmPoint(4.0, 3.0),
                new AlgorithmPoint(3.0, 3.0),
                new AlgorithmPoint(3.0, 2.0),
                new AlgorithmPoint(2.0, 2.0),
                new AlgorithmPoint(2.0, 3.0),
                new AlgorithmPoint(1.0, 3.0));

        // inside 凹
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(1.5, 1.5), 凹));
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(1.5, 2.5), 凹));
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(2.0, 1.5), 凹));
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(2.5, 1.5), 凹));
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(3.5, 1.5), 凹));
        assertTrue(RAY_CASTING.contains(new AlgorithmPoint(3.5, 2.5), 凹));

        // outside of 凹
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(1.5, 3.5), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(2.5, 2.5), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(3.5, 3.5), 凹));

        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(4.5, 0.5), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(4.5, 1.0), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(4.5, 1.5), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(4.5, 2.0), 凹));
        assertFalse(RAY_CASTING.contains(new AlgorithmPoint(4.5, 2.5), 凹));
    }

}
