package org.example;

import java.util.ArrayList;

public class SolveUser {
    QuadraticEquation quadraticEquation;

    SolveUser(QuadraticEquation quadraticEquation) {
        if (quadraticEquation == null) {
            throw new IllegalArgumentException();
        }
        this.quadraticEquation = quadraticEquation;
    }

    public double getLarger() throws NoRootsException {
        ArrayList<Double> roots = quadraticEquation.solve();
        if (roots.size() == 1) {
            return roots.get(0);
        } else if (roots.size() == 2) {
            if (Math.abs(roots.get(0) - roots.get(1)) > 0.001) {
                return roots.get(0);
            } else {
                return roots.get(1);
            }
        } else {
            throw new NoRootsException("No Roots");
        }
    }
}
