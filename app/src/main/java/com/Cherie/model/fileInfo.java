package com.Cherie.model;

import java.io.Serializable;

/**
 * Created by liangxy on 2017/6/22.
 */
public class fileInfo  implements Serializable {

    private String path;//路径
    private String fileName;//文件名称
    private Integer state;//检测项目状态 1 合格、0 不合格

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        fileInfo fileInfo = (fileInfo) o;

        if (path != null ? !path.equals(fileInfo.path) : fileInfo.path != null) return false;
        return fileName != null ? fileName.equals(fileInfo.fileName) : fileInfo.fileName == null;

    }

    public fileInfo() {
    }

    public fileInfo(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
