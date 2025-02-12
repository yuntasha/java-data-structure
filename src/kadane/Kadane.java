package kadane;

public class Kadane {
    private int N;
    private int[] arr;

    public Kadane(int[] arr) {
        this.N = arr.length;
        this.arr = arr;
    }

    public int bruteForce() {
        int result = Integer.MIN_VALUE;

        for (int start = 0; start < N; start++) {
            for (int end = start + 1; end <= N; end++) {
                int now = 0;
                for (int idx = start; idx < end; idx++) {
                    now += arr[idx];
                }
                result = Math.max(result, now);
            }
        }

        return result;
    }

    public int prefixSum() {
        int result = Integer.MIN_VALUE;

        int[] sum = new int[N + 1];

        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        for (int start = 0; start < N; start++) {
            for (int end = start + 1; end <= N; end++) {
                result = Math.max(result, sum[end] - sum[start]);
            }
        }

        return result;
    }

    public int kadane() {
        int result = Integer.MIN_VALUE;

        int[] dp = new int[N];
        dp[0] = Math.max(0, arr[0]);

        for (int idx = 1; idx < N; idx++) {
            dp[idx] = Math.max(arr[idx], arr[idx] + dp[idx - 1]);
            result = Math.max(result, dp[idx]);
        }

        return result;
    }

    public int kadaneNoMemory() {
        int result = Integer.MIN_VALUE;

        int now = 0;

        for (int idx = 0; idx < N; idx++) {
            now = Math.max(arr[idx], arr[idx] + now);
            result = Math.max(result, now);
        }

        return result;
    }
}
