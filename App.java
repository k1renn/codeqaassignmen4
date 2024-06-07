public class App {
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
