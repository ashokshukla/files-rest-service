package org.test.identity.e2e.model;

public class FileInfo {
	private String name;
	private String mimeType;
	private String size;
	private String extension;

	public FileInfo() {
	}

	public FileInfo(String name, String mimeType, String size, String extension) {
		this();
		this.name = name;
		this.mimeType = mimeType;
		this.size = size;
		this.extension = extension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}