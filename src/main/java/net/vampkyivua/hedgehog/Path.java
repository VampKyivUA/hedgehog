package net.vampkyivua.hedgehog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: Mykhailo Drozdov
 * Created: 19.08.19 18:19
 */
public class Path {

    private final List<Grid.Node> path = new ArrayList<>();

    public void add(Grid.Node node) {
        path.add(Objects.requireNonNull(node));
    }

    public void reverse() {
        Collections.reverse(path);
    }

    public int calculateCost() {
        return path.stream().mapToInt(n -> n.cost).sum();
    }

    @Override
    public String toString() {
        return "Cost: " + calculateCost()
                + "\nPath:\n" + path.stream()
                .map(Grid.Node::toString)
                .collect(Collectors.joining("\n"));
    }
}


