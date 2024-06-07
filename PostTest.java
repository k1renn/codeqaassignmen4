import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {
    // Tests for the addPost() method
    @Test
    public void testAddingValidPost() {
        // Create a valid post
        String validBodyContent = "This is a sample post body. ".repeat(26);
        Post post = new Post(1, "Example Post Title", validBodyContent, new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertTrue(post.addPost(), "A valid post should be successfully added");
    }

    @Test
    public void testAddingAnotherValidPost() {
        // Create another valid post
        String validBodyContent = "This is another sample post body. ".repeat(26);
        Post post = new Post(7, "Another Valid Post Title", validBodyContent, new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertTrue(post.addPost(), "Another valid post should be successfully added");
    }

    @Test
    public void testAddingPostWithShortTitle() {
        // Create a post with an invalid short title
        Post post = new Post(2, "Short", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post.addPost(), "A post with a short title should not be added");
    }

    @Test
    public void testAddingPostWithAnotherShortTitle() {
        // Create another post with an invalid short title
        Post post = new Post(8, "Tiny", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertFalse(post.addPost(), "Another post with a short title should not be added");
    }

    @Test
    public void testAddingPostWithShortBody() {
        // Create a post with an invalid short body
        Post post = new Post(3, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post.addPost(), "A post with a short body should not be added");
    }

    @Test
    public void testAddingPostWithAnotherShortBody() {
        // Create another post with an invalid short body
        Post post = new Post(9, "Another Example Post Title", "Short body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertFalse(post.addPost(), "Another post with a short body should not be added");
    }

    @Test
    public void testAddingPostWithTooManyTags() {
        // Create a post with an invalid number of tags
        String validBodyContent = "This is a sample post body. ".repeat(26);
        Post post = new Post(4, "Example Post Title", validBodyContent, new String[]{"tag1", "tag2", "tag3", "tag4", "tag5", "tag6"}, "Difficult", "Highly Needed");
        assertFalse(post.addPost(), "A post with too many tags should not be added");
    }

    @Test
    public void testAddingPostWithAnotherTooManyTags() {
        // Create another post with an invalid number of tags
        String validBodyContent = "This is another sample post body. ".repeat(26);
        Post post = new Post(10, "Another Example Post Title", validBodyContent, new String[]{"tag1", "tag2", "tag3", "tag4", "tag5", "tag6", "tag7"}, "Moderate", "Ordinary");
        assertFalse(post.addPost(), "Another post with too many tags should not be added");
    }

    @Test
    public void testAddingEasyPostWithTooManyTags() {
        // Create an "Easy" post with too many tags
        Post post = new Post(5, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2", "tag3", "tag4"}, "Easy", "Highly Needed");
        assertFalse(post.addPost(), "An 'Easy' post with too many tags should not be added");
    }

    @Test
    public void testAddingAnotherEasyPostWithTooManyTags() {
        // Create another "Easy" post with too many tags
        Post post = new Post(11, "Another Example Post Title", "This is another sample post body.", new String[]{"tag5", "tag6", "tag7", "tag8"}, "Easy", "Ordinary");
        assertFalse(post.addPost(), "Another 'Easy' post with too many tags should not be added");
    }

    @Test
    public void testAddingPostWithIncompatibleTypeAndEmergency() {
        // Create a post with incompatible type and emergency level
        Post post = new Post(6, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Easy", "Immediately Needed");
        assertFalse(post.addPost(), "A post with incompatible type and emergency level should not be added");
    }

    @Test
    public void testAddingAnotherPostWithIncompatibleTypeAndEmergency() {
        // Create another post with incompatible type and emergency level
        Post post = new Post(12, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Immediately Needed");
        assertFalse(post.addPost(), "Another post with incompatible type and emergency level should not be added");
    }

// Tests for the addComment() method

    @Test
    public void testAddingValidComment() {
        // Add a valid comment to a post
        Post post = new Post(1, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertTrue(post.addComment("This is a valid comment."), "A valid comment should be successfully added");
    }

    @Test
    public void testAddingAnotherValidComment() {
        // Add another valid comment to a post
        Post post = new Post(13, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertTrue(post.addComment("This is another valid comment."), "Another valid comment should be successfully added");
    }

    @Test
    public void testAddingCommentWithFewWords() {
        // Attempt to add a comment with too few words
        Post post = new Post(2, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post.addComment("Short comment."), "A comment with too few words should not be added");
    }

    @Test
    public void testAddingAnotherCommentWithFewWords() {
        // Attempt to add another comment with too few words
        Post post = new Post(14, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertFalse(post.addComment("Too short."), "Another comment with too few words should not be added");
    }

    @Test
    public void testAddingCommentWithInvalidFormat() {
        // Attempt to add a comment with improper formatting
        Post post = new Post(3, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post.addComment("this is not Properly Formatted."), "A comment with improper formatting should not be added");
    }

    @Test
    public void testAddingAnotherCommentWithInvalidFormat() {
        // Attempt to add another comment with improper formatting
        Post post = new Post(15, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        assertFalse(post.addComment("another invalid format."), "Another comment with improper formatting should not be added");
    }

    @Test
    public void testAddingCommentWhenMaxLimitReached() {
        // Add comments to reach the maximum limit and attempt to add another
        Post post = new Post(4, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        post.addComment("This is Comment 1");
        post.addComment("This is Comment 2");
        post.addComment("This is Comment 3");
        post.addComment("This is Comment 4");
        post.addComment("This is Comment 5");
        assertFalse(post.addComment("This comment exceeds the maximum limit."), "A comment should not be added when the maximum limit is reached");
    }

    @Test
    public void testAddingAnotherCommentWhenMaxLimitReached() {
        // Add comments to reach the maximum limit and attempt to add another
        Post post = new Post(16, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        post.addComment("Comment 1");
        post.addComment("Comment 2");
        post.addComment("Comment 3");
        post.addComment("Comment 4");
        post.addComment("Comment 5");
        assertFalse(post.addComment("This comment exceeds the maximum limit."), "Another comment should not be added when the maximum limit is reached");
    }

    @Test
    public void testAddingCommentToEasyPostWhenLimitReached() {
        // Add comments to an "Easy" post to reach the limit and attempt to add another
        Post post = new Post(5, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Easy", "Highly Needed");
        post.addComment("This is Comment 1");
        post.addComment("This is Comment 2");
        post.addComment("This is Comment 3");
        assertFalse(post.addComment("This comment exceeds the limit for an 'Easy' post."), "A comment should not be added when the limit for an 'Easy' post is reached");
    }

    @Test
    public void testAddingAnotherCommentToEasyPostWhenLimitReached() {
        // Add comments to another "Easy" post to reach the limit and attempt to add another
        Post post = new Post(17, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Easy", "Ordinary");
        post.addComment("Comment 1");
        post.addComment("Comment 2");
        post.addComment("Comment 3");
        assertFalse(post.addComment("This comment exceeds the limit for another 'Easy' post."), "Another comment should not be added when the limit for another 'Easy' post is reached");
    }

    @Test
    public void testAddingCommentToOrdinaryEmergencyPostWhenLimitReached() {
        // Add comments to a post with "Ordinary" emergency level to reach the limit and attempt to add another
        Post post = new Post(6, "Example Post Title", "This is a sample post body.", new String[]{"tag1", "tag2"}, "Difficult", "Ordinary");
        post.addComment("Comment 1");
        post.addComment("Comment 2");
        post.addComment("Comment 3");
        assertFalse(post.addComment("This comment exceeds the limit for an 'Ordinary' emergency level post."), "A comment should not be added when the limit for an 'Ordinary' emergency level post is reached");
    }

    @Test
    public void testAddingAnotherCommentToOrdinaryEmergencyPostWhenLimitReached() {
        // Add comments to another post with "Ordinary" emergency level to reach the limit and attempt to add another
        Post post = new Post(18, "Another Example Post Title", "This is another sample post body.", new String[]{"tag3", "tag4"}, "Moderate", "Ordinary");
        post.addComment("Comment 1");
        post.addComment("Comment 2");
        post.addComment("Comment 3");
        assertFalse(post.addComment("This comment exceeds the limit for another 'Ordinary' emergency level post."), "Another comment should not be added when the limit for another 'Ordinary' emergency level post is reached");
    }
}

