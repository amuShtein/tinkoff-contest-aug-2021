    import java.util.*;

    public class Main {
        private static final int MOD = 998244353;
        static int[][] table;
        static long[][] buf;

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            int n = in.nextInt();
            int m = in.nextInt();

            table = new int[n+1][];
            for(int i = 0; i <= n; i++) {
                table[i] = new int[i+1];
                table[i][0] = 1;

                for(int j = 1; j < i; j++) {
                    table[i][j] = (table[i-1][j-1] + table[i-1][j]) % MOD;
                }

                table[i][i] = 1;
            }

//            for(int i = 0; i <= n; i++) {
//                for(int j = 0; j <= i; j++) {
//                    System.out.print(table[i][j] + " ");
//                }
//
//                System.out.println();
//            }

            Map<Integer, Integer> cur= new HashMap<>();
            Map<Integer, Integer> toNext= new HashMap<>();

            for (int i = 0; i <= n; i += 2) {
                toNext.put(i/2,table[n][i]);
            }

            m = m >> 1;

            while (m != 0) {
                int b = m & 1;
//                System.out.println(b);

                    cur.clear();

                for (Map.Entry<Integer, Integer> line : toNext.entrySet()) {
                    if (line.getKey() % 2 == b) {
                        for (int i = 0; i <= n; i += 2) {
                            int old = cur.get(i + line.getKey()) == null? 0 : cur.get(i + line.getKey());
                            cur.put(i + line.getKey(), (old + table[n][i] * line.getValue()) % MOD );
                        }
                    }
                }

                m = m >> 1;

                toNext.clear();

                for (Map.Entry<Integer, Integer> line : cur.entrySet()) {
//                    System.out.println(line.getKey() + "   " + line.getValue());
                    toNext.put(line.getKey()/2, line.getValue());

                }
//                System.out.println("-----------");
            }

            System.out.println(toNext.get(0));
        }
    }
