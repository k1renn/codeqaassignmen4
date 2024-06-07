import java.io.*;
import java.util.*;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private static final String[] VALID_POST_TYPES = {"Very Difficult", "Difficult", "Easy"};
    private static final String[] VALID_POST_EMERGENCY_LEVELS = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments = new ArrayList<>();

    // Constructor to initialize a post
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    // Method to add the post to txt
    public boolean addPost() {
        if (!validatePost()) {
            System.out.println("Invalid Post");
            return false;
        }

        File postsFile = new File("test.txt");
        if (!postsFile.exists()) {
            try {
                System.out.println("file not present, new file creating ");
                postsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Post writing to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(postsFile, true))) {
            writer.write(formatPost());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    // Method to add a comment to the post
    public boolean addComment(String comment) {
        // Comment Validation
        if (!validateComment(comment)) {
            System.out.println("Invalid comment");
            return false;
        }

        postComments.add(comment);

        File postsFile = new File("test.txt");
        if (!postsFile.exists()) {
            System.out.println("txt file does not exist.");
            return false;
        }

        List<String> fileContent = new ArrayList<>();
        boolean isPostFound = false;
        boolean isCommentSection = false;

        // Read the file and prepare the new content with the comment added
        try (BufferedReader reader = new BufferedReader(new FileReader(postsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
                if (line.startsWith("Post ID: " + postID)) {
                    isPostFound = true;
                }
                if (isPostFound && line.equals("----")) {
                    fileContent.add("Comment: " + comment);
                    isPostFound = false;
                    isCommentSection = true;
                }
                if (isCommentSection && line.startsWith("Post ID:")) {
                    isCommentSection = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(postsFile))) {
            for (String contentLine : fileContent) {
                writer.write(contentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean validatePost() {
        if (postTitle.length() < 10 || postTitle.length() > 250 || !postTitle.matches("^[A-Za-z].*$")) {
            System.out.println("The post: " + postID + " title must have a minimun of 10 characters and a max. of 250 characters and start with a letter.");
            return false;
        }

        if (postBody.length() < 250) {
            System.out.println("The post: " + postID + " body must have a min. of 250 characters long.");
            return false;
        }

        if (postTags.length < 2 || postTags.length > 5) {
            System.out.println("The post: " + postID + " must have tags numbered between 2 and 5.");
            return false;
        }

        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                System.out.println("The post: " + postID + " 's tags must be between 2 and 10 characters long and be in lowercase.");
                return false;
            }
        }

        if (!Arrays.asList(VALID_POST_TYPES).contains(postType)) {
            System.out.println("The post: " + postID + " is Invalid. Valid types: Very Difficult, Difficult, Easy.");
            return false;
        }

        if (postType.equals("Easy") && postTags.length > 3) {
            System.out.println("The post: " + postID + ", Easy posts only have a maximum of 3 tags.");
            return false;
        }

        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            System.out.println("The post: " + postID + ", Very Difficult, Difficult posts must be at least 300 characters long in the body.");
            return false;
        }

        if (!Arrays.asList(VALID_POST_EMERGENCY_LEVELS).contains(postEmergency)) {
            System.out.println("The post: " + postID + ", Invalid emergency level. Applicable levels: Immediately Needed, Highly Needed, Ordinary.");
            return false;
        }

        if (postType.equals("Easy") && Arrays.asList("Immediately Needed", "Highly Needed").contains(postEmergency)) {
            System.out.println("The post: " + postID + ", Easy posts cannot be classfied immediately or highly needed.");
            return false;
        }

        if (Arrays.asList("Very Difficult", "Difficult").contains(postType) && postEmergency.equals("Ordinary")) {
            System.out.println("The post: " + postID + ", Ordinary posts cannot be classified as very difficult or difficult.");
            return false;
        }

        return true;
    }

    private boolean validateComment(String comment) {
        // Split comment string using spaces into an array per word
        String[] words = comment.split("\\s+");

        // Validate comment length and format
        if (words.length < 4 || words.length > 10) {
            System.out.println(postID + ": Comments should have 4 to 10 words.");
            return false;
        }

        // Validate if starts with an uppercase letter
        if (!Character.isUpperCase(words[0].charAt(0))) {
            System.out.println(postID + ": Start with an uppercase letter.");
            return false;
        }

        // Validate maximum number of comments based on post type and emergency level
        if (postComments.size() >= 5 || (postType.equals("Easy") && postComments.size() >= 3) || (postEmergency.equals("Ordinary") && postComments.size() >= 3)) {
            System.out.println(postID + ": Maximum comment limit reached for this post.");
            return false;
        }

        return true;
    }

    // Method to format the post for output
    private String formatPost() {
        StringBuilder sb = new StringBuilder();
        sb.append("Post ID: ").append(postID)
          .append("\nTitle: ").append(postTitle)
          .append("\nBody: ").append(postBody)
          .append("\nTags: ").append(String.join(", ", postTags))
          .append("\nType: ").append(postType)
          .append("\nEmergency: ").append(postEmergency)
          .append("\n----");

        // Add comments to the formatted post
        for (String comment : postComments) {
            sb.append("\nComment: ").append(comment);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        int postID = 1;
        String postTitle = "A Sample Post Title";
        String postBody = "This is the content of the demo post. It needs to be sufficiently long to satisfy the minimum length requirement. Here is additional text to ensure the body has at least 250 characters. Random words to fill in the rest: apple, banana, cat, dog, elephant, frog, grape, house, igloo, jelly, kite, lemon, monkey, notebook, orange, piano, queen, rabbit, snake, table, umbrella, violin, watermelon, xylophone, yacht, zebra.";
        String[] postTags = {"example", "demo"};
        String postType = "Difficult";
        String postEmergency = "Highly Needed";

        // Instantiate a Post object with the example data
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        Post post2 = new Post(2, postTitle, postBody, postTags, postType, postEmergency);

        post.addPost();
        post.addComment("This is a demo comment for post 1.");
        post.addComment("This is also a demo comment.");

        post2.addPost();
        post2.addComment("This is a demo comment for post 2.");
    }
}
