package br.unifor.probex.dto;


public class VoteDTO {

    private Long postId;
    private String username;

    public VoteDTO() {

    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
