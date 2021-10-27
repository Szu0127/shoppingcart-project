package tw.knowlife.model;

public class VideoBean {
	private int id;
	private String videoName;
	private String videoFileName;
	private String imgFileName;
	
	
	public VideoBean() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getVideoName() {
		return videoName;
	}


	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}


	public String getVideoFileName() {
		return videoFileName;
	}


	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}


	public String getImgFileName() {
		return imgFileName;
	}


	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

}
