package database;

import model.LikeStatus;

import java.util.Optional;
import java.util.UUID;

public interface InterfaceLikedPostsTable
{
    public boolean getPostStatusByUser(UUID postID, UUID userID);
    public boolean addLikedPost(UUID postID, UUID userID, LikeStatus status);
    public boolean removeLikedPost(UUID postID, UUID userID);
    public boolean updateLikedPostStatus(UUID postID, UUID userID, LikeStatus status);
}
