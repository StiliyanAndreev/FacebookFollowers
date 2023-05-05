package W3Schools;

import java.util.*;

public class FacebookFollowers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer[]> followers = new HashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("Log out")) {
            String[] tokens = input.split(": ");
            String command = tokens[0];
            String username = tokens[1];

            switch (command) {
                case "New follower":
                    if (!followers.containsKey(username)) {
                        followers.put(username, new Integer[]{0, 0});
                    }
                    break;

                case "Like":
                    int likesCount = Integer.parseInt(tokens[2]);
                    if (!followers.containsKey(username)) {
                        followers.put(username, new Integer[]{likesCount, 0});
                    } else {
                        Integer[] counts = followers.get(username);
                        counts[0] += likesCount;
                    }
                    break;

                case "Comment":
                    if (!followers.containsKey(username)) {
                        followers.put(username, new Integer[]{0, 1});
                    } else {
                        Integer[] counts = followers.get(username);
                        counts[1]++;
                    }
                    break;

                case "Blocked":
                    if (followers.remove(username) == null) {
                        System.out.println(username + " doesn't exist.");
                    }
                    break;
            }

            input = scanner.nextLine();
        }

        System.out.println(followers.size() + " followers");
        followers.entrySet().stream()
                .sorted((a, b) -> {
                    int countA = a.getValue()[0] + a.getValue()[1];
                    int countB = b.getValue()[0] + b.getValue()[1];
                    return Integer.compare(countB, countA);
                })
                .forEach(entry -> {
                    String username = entry.getKey();
                    Integer[] counts = entry.getValue();
                    int total = counts[0] + counts[1];
                    System.out.printf("%s: %d%n", username, total);
                });
    }
}
