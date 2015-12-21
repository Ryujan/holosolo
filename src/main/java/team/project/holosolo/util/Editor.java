package team.project.holosolo.util;

import org.springframework.web.multipart.MultipartFile;

public class Editor {
	private MultipartFile fileData;

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
}
