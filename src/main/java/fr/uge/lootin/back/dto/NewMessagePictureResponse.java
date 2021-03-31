package fr.uge.lootin.back.dto;


public class NewMessagePictureResponse {
    private Long id;
    private Long matchId;
    
    
    
	public NewMessagePictureResponse() {
	}
	
	public NewMessagePictureResponse(Long id, Long matchId) {
		this.id = id;
		this.matchId = matchId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}



}
