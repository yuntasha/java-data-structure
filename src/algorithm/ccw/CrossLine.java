package algorithm.ccw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CrossLine {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            Line[] lines = new Line[2];

            for (int j = 0; j < 2; j++) {
                StringTokenizer input = new StringTokenizer(bf.readLine());
                lines[j] = new Line(new Point(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())), new Point(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())));
            }

            System.out.println(lines[0].isCross(lines[1]));
        }
    }

    static class Line {
        Point a;
        Point b;

        public Line(Point a, Point b) {
            if (a.compareTo(b) < 0) {
                this.a = a;
                this.b = b;
            } else {
                this.b = a;
                this.a = b;
            }
        }

        public boolean isCross(Line l) {
            long abc = ccw(l.a);
            long abd = ccw(l.b);
            long cda = l.ccw(a);
            long cdb = l.ccw(b);

            if (abc == 0 && abd == 0 && cda == 0 && cdb == 0) {
                return a.compareTo(l.b) <= 0 && l.a.compareTo(b) <= 0;
            }

            return abc * abd <= 0 && cda * cdb <= 0;
        }

        private long ccw(Point p) {
            return a.x * b.y + b.x * p.y + p.x * a.y - (a.x * p.y + b.x * a.y + p.x * b.y);
        }
    }

    static class Point implements Comparable<Point> {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            if (x == o.x) return Long.compare(y, o.y);
            return Long.compare(x, o.x);
        }
    }
}
