package com.ooredoo.report_builder.entity;

public enum FileCategory {
    PDF("PDF Document"),
    IMAGE("Image File"),
    VIDEO("Video File");
    
    private final String displayName;
    
    FileCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static FileCategory fromFileType(String fileType) {
        if (fileType == null) {
            return null;
        }
        
        String lowerFileType = fileType.toLowerCase();
        
        if (lowerFileType.equals("application/pdf")) {
            return PDF;
        } else if (lowerFileType.startsWith("image/")) {
            return IMAGE;
        } else if (lowerFileType.startsWith("video/")) {
            return VIDEO;
        }
        
        return null;
    }
    
    public static boolean isSupportedFileType(String fileType) {
        return fromFileType(fileType) != null;
    }
}
