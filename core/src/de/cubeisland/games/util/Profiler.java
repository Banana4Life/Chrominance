package de.cubeisland.games.util;

import java.util.Stack;

public class Profiler {

    private static boolean enabled = true;
    private static final Stack<Entry> entries = new Stack<>();

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        Profiler.enabled = enabled;
    }

    public static void begin(String name) {
        if (!enabled) {
            return;
        }
        final Entry e = new Entry();
        e.name = name;
        e.start = time();
        entries.push(e);
    }

    public static void end() {
        if (!enabled) {
            return;
        }
        final Entry e = entries.peek();
        final long time = time() - e.start;
        entries.pop();

        System.out.println(indention(entries.size() - 1) + e.name + ": " + time + " ns (" + (time / 1000000d) + " ms)");
    }

    private static long time() {
        return System.nanoTime();
    }

    private static String indention(int level) {
        StringBuilder indention = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            indention.append("  ");
        }
        return indention.toString();
    }

    private static final class Entry {
        public String name;
        public long start;
    }
}
