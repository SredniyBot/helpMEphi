package ru.helpmephi.helpmephi.entity.doc;

import java.util.Locale;

public enum ContentType {

    UNKNOWN,
    IMAGE,
    DOC,
    PDF,
    PPT,
    ARCHIVE,
    AUDIO,
    PROGRAM,
    VIDEO;

    private final String subPath="mephi/";

    public static ContentType getTypeByFilename(String filename){
        if(filename.contains(".")){
            return getTypeByFormat(filename.substring(filename.lastIndexOf(".")));
        }
        return UNKNOWN;
    }

    public static ContentType getTypeByFormat(String format){
        format=format.toLowerCase(Locale.ROOT);
        switch (format){
            case ".jpg":
            case ".jpeg":
            case ".png":
            case ".gif":
            case ".webp":
                return IMAGE;
            case ".pdf":
                return PDF;
            case ".doc":
            case ".docx":
            case ".txt":
            case ".odt":
            case ".xls":
            case ".xlsx":
                return DOC;
            case ".ppt":
            case ".pptx":
            case ".pps":
            case ".ppsx":
            case ".key":
                return PPT;
            case ".mp3":
            case ".m4a":
            case ".ogg":
            case ".wav":
                return AUDIO;
            case ".mp4":
            case ".m4v":
            case ".mov":
            case ".avi":
            case ".mpg":
            case ".ogv":
            case ".3gp":
            case ".3g2":
                return VIDEO;
            case ".zip":
            case ".rar":
            case ".tar":
            case ".7-zip":
            case ".7z":
                return ARCHIVE;
            case ".c":
            case ".java":
            case ".cpp":
            case ".py":
            case ".html":
            case ".css":
            case ".js":
            case ".cs":
            case ".go":
            case ".swift":
                return PROGRAM;
            default:
                return UNKNOWN;
        }
    }

    public String getPrototypeImagePath(){
        switch (this){
            case DOC:return subPath+"doc.png";
            case ARCHIVE:return subPath+"folder.png";
            case AUDIO:return subPath+"audio.png";
            case IMAGE:return subPath+"photo.png";
            case PPT:return subPath+"ppt.png";
            case VIDEO:return subPath+"video.png";
            case PROGRAM:return subPath+"program.png";
            case PDF:return subPath+"pdf.png";
            case UNKNOWN:
            default:
                return subPath+"unknown.png";
        }
    }


}
